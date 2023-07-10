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
    echo Starting metastore
}

function hiveServer2IsOrNotEnabled() {
    flag=1
    while [ $flag -ne 0 ]
    do
      sleep 0.5
      netstat -nltp | grep 10000 > /dev/null 2>&1
      flag=$?
    done
    echo Starting hiveServer2
}

function start() {
    nohup hive --service metastore > /dev/null 2>&1 &
    metastoreIsOrNotEnabled

    nohup hive --service hiveserver2 > /dev/null 2>&1 &
    hiveServer2IsOrNotEnabled
}

function stop() {
    kill -9 $(netstat -nlpt | grep 9083 | awk '{print $7}' | awk -F "/" '{print $1}')
    echo Stopping metastore
    kill -9 $(netstat -nlpt | grep 10000 | awk '{print $7}' | awk -F "/" '{print $1}')
    echo Stopping hiveServer2
}

function startSparkHistory() {
    start-history-server.sh
    echo Starting sparkhisotry
}

function stopSparkHistory() {
    stop-history-server.sh
    echo Stopping sparkhisotry
}

case $1 in
"start")
    echo -e "\033[40;36m------------------- starting HDFS --------------------\033[0m"
    ssh hadoop101 "start-dfs.sh"

    echo -e "\033[40;36m------------------- starting YARN --------------------\033[0m"
    ssh hadoop102 "start-yarn.sh"

    echo -e "\033[40;36m------------------- starting HIST --------------------\033[0m"
    ssh hadoop103 "mapred --daemon start historyserver"

    echo -e "\033[40;36m------------------- starting HIVE --------------------\033[0m"
    start

    echo -e "\033[40;36m------------------- starting SPK --------------------\033[0m"
    startSparkHistory
    ;;
"stop")
    echo -e "\033[40;36m------------------- stopping HDFS --------------------\033[0m"
    ssh hadoop101 "stop-dfs.sh"

    echo -e "\033[40;36m------------------- stopping YARN --------------------\033[0m"
    ssh hadoop102 "stop-yarn.sh"

    echo -e "\033[40;36m------------------- stopping HIST --------------------\033[0m"
    ssh hadoop103 "mapred --daemon stop historyserver"

    echo -e "\033[40;36m------------------- stopping HIVE --------------------\033[0m"
    stop

    echo -e "\033[40;36m------------------- stopping SPK --------------------\033[0m"
    stopSparkHistory
    ;;
"restart")
    echo -e "\033[40;36m------------------- stopping hadoop cluster --------------------\033[0m"
    ssh hadoop101 "start-dfs.sh"
    ssh hadoop102 "start-yarn.sh"
    ssh hadoop103 "mapred --daemon start historyserver"
    start
    startSparkHistory

    echo -e "\033[40;36m------------------- starting hadoop cluster --------------------\033[0m"
    ssh hadoop101 "stop-dfs.sh"
    ssh hadoop102 "stop-yarn.sh"
    ssh hadoop103 "mapred --daemon stop historyserver"
    stop
    stopSparkHistory
    ;;
"stat")
    # check NN
    ssh hadoop101 "source /etc/profile; netstat -nlpt | grep 9870 > /dev/null 2>&1"
    if [ $? -eq 0 ]; then
      echo HDFS is running
    else
      echo HDFS is not running
    fi

    # check YARN
    ssh hadoop102 "source /etc/profile; netstat -nlpt | grep 8088 > /dev/null 2>&1"
    if [ $? -eq 0 ]; then
      echo YARN is running
    else
      echo YARN is not running
    fi
    ;;
*)
echo function:
    echo " start | stop | restart | stat cluster all related service"
esac