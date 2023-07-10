#!/bin/bash

function start() {
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; cd /opt/mod/zookeeper-3.4.10; zkServer.sh start"
    done
}

function stop() {
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; zkServer.sh stop"
    done
}

function stat() {
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 2181 > /dev/null 2>&1"
        if [ $? -eq 0 ]; then
          echo zookeeper is running
        else
          echo zookeeper is not running
        fi
    done
}

case $1 in
"start")
    start
    ;;
"stop")
    stop
    ;;
"restart")
    stop
    start
    ;;
"stat")
    stat
    ;;
*)
    echo function:
    echo "  start | stop | restart | stat zk cluster"
    ;;
esac