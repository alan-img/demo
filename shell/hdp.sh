#!/bin/bash

source /etc/profile.d/hadoop-env.sh

function metastoreIsOrNotEnabled() {
    flag=1
    while [ $flag -ne 0 ]
      do
        sleep 0.5
    	netstat -nltp | grep 9083 > /dev/null 2>&1
    	flag=$?
    done
    echo metastore is running
}

function hiveServer2IsOrNotEnabled() {
    flag=1
    while [ $flag -ne 0 ]
      do
        sleep 0.5
    	netstat -nltp | grep 10000 > /dev/null 2>&1
    	flag=$?
    done
    echo hiveServer2 is running
}

function start() {
    nohup hive --service metastore > /opt/mod/apache-hive-2.3.9-bin/logs/metastore.log 2>&1 &
    metastoreIsOrNotEnabled

    nohup hive --service hiveserver2 > /opt/mod/apache-hive-2.3.9-bin/logs/hiveserver2.log 2>&1 &
    hiveServer2IsOrNotEnabled
}

function stop() {
    kill -9 $(netstat -nlpt | grep 9083 | awk '{print $7}' | awk -F "/" '{print $1}')
    echo stopping metastore
    kill -9 $(netstat -nlpt | grep 10000 | awk '{print $7}' | awk -F "/" '{print $1}')
    echo stopping hiveServer2
}

case $1 in
"start")
        echo -e "\033[40;36m------------------- starting HDFS --------------------\033[0m"
        start-dfs.sh

        echo -e "\033[40;36m------------------- starting YARN --------------------\033[0m"
        start-yarn.sh

        echo -e "\033[40;36m------------------- starting HIST --------------------\033[0m"
        mr-jobhistory-daemon.sh start historyserver

        echo -e "\033[40;36m------------------- starting HIVE --------------------\033[0m"
        start

        echo -e "\033[40;36m------------------- starting SPK --------------------\033[0m"
        start-history-server.sh
        ;;
"stop")
        echo -e "\033[40;36m------------------- stopping HDFS --------------------\033[0m"
        stop-dfs.sh

        echo -e "\033[40;36m------------------- stopping YARN --------------------\033[0m"
        stop-yarn.sh

        echo -e "\033[40;36m------------------- stopping HIST --------------------\033[0m"
        mr-jobhistory-daemon.sh stop historyserver

        echo -e "\033[40;36m------------------- stopping HIVE --------------------\033[0m"
        stop

        echo -e "\033[40;36m------------------- stopping SPK --------------------\033[0m"
        stop-history-server.sh
        ;;
"restart")
        echo -e "\033[40;36m------------------- stopping hadoop cluster --------------------\033[0m"
        stop-dfs.sh; stop-yarn.sh; mr-jobhistory-daemon.sh stop historyserver; stop-history-server.sh; stop

        echo -e "\033[40;36m------------------- starting hadoop cluster --------------------\033[0m"
        start-dfs.sh; start-yarn.sh; mr-jobhistory-daemon.sh start historyserver; start-history-server.sh; start
        ;;
*)
        echo function:
        echo "  start | stop | start cluster all related service"
        echo example:
        echo "  hdp start"
        echo "  hdp stop"
        echo "  hdp restart"
esac