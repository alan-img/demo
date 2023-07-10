#!/bin/bash

if [ $# == 0 ];then
    echo -e "\033[40;36mmust be pass port\033[0m"
    echo -e "\033[40;36m  example: st 2181 \033[0m"
    exit 1
fi

function stat() {
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; netstat -nlpt | grep $1 > /dev/null 2>&1"
        if [ $? -eq 0 ]; then
          echo process is running
        else
          echo process is not running
        fi
    done
}

stat $1
