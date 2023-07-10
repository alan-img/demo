#!/bin/bash

function start() {
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 9092 > /dev/null 2>&1"
        if [ $? -eq 0 ]; then
          echo kafka is already running
        else
          ssh hadoop$i "source /etc/profile; /opt/mod/kafka_2.12-3.0.0/bin/kafka-server-start.sh -daemon /opt/mod/kafka_2.12-3.0.0/config/server.properties"
          ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 9092 > /dev/null 2>&1"
          flag=$?
          while [ $flag -ne 0 ]
          do
            sleep 0.5
            ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 9092 > /dev/null 2>&1"
            flag=$?
          done
          echo Starting kafka ... STARTED
        fi
    done
}

function stop() {
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; /opt/mod/kafka_2.12-3.0.0/bin/kafka-server-stop.sh"
        ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 9092 > /dev/null 2>&1"
        flag=$?
        while [ $flag -eq 0 ]
        do
          sleep 0.5
          ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 9092 > /dev/null 2>&1"
          flag=$?
        done
        echo Stopping kafka ... STOPPED
    done
}

function stat(){
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; netstat -nlpt | grep 9092 > /dev/null 2>&1"
        if [ $? -eq 0 ]; then
          echo kafka is running
        else
          echo kafka is not running
        fi
    done
}

case $1 in
"start")
    # kafka依赖于zk 先启动zk
    ssh hadoop101 "source /etc/profile; netstat -nlpt | grep 2181 > /dev/null 2>&1"
    if [ $? -ne 0 ]; then
      zk start
    fi
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
    echo "  start or stop kafka cluster"
    echo example:
    echo "  kk start"
    echo "  kk stop"
    echo "  kk restart"
    echo "  kk stat"
    ;;
esac