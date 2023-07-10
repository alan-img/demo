#!/bin/bash

function stat() {
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 16010 > /dev/null 2>&1"
        if [ $? -eq 0 ]; then
          echo hbase is running
        else
          ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 16030 > /dev/null 2>&1"
          if [ $? -eq 0 ]; then
            echo hbase is running
          else
            echo hbase is not running
          fi
        fi
    done
}

case $1 in
"start")
    /opt/mod/hbase-2.4.11/bin/start-hbase.sh
    ;;
"stop")
    /opt/mod/hbase-2.4.11/bin/stop-hbase.sh
    ;;
"restart")
    /opt/mod/hbase-2.4.11/bin/stop-hbase.sh
    /opt/mod/hbase-2.4.11/bin/start-hbase.sh
    ;;
"stat")
    stat
    ;;
*)
    echo function:
    echo "  start | stop | restart | stat hbase cluster"
    ;;
esac