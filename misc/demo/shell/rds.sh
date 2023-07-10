#!/bin/bash

case $1 in "start")
    # 启动三台机器上的redis服务
    for((i = 101; i <= 103; i++));
    do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        ssh hadoop$i "source /etc/profile; /opt/mod/redis-6.2.3/src/redis-server /opt/mod/redis-6.2.3/redis.conf"
    done
    # 启动哨兵
    ssh hadoop102 "source /etc/profile; /opt/mod/redis-6.2.3/src/redis-sentinel /opt/mod/redis-6.2.3/sentinel.conf"
    ;;
"stat")
    echo -e "\033[40;36m------------------- redis -------------------\033[0m"
    st 6379
    echo -e "\033[40;36m------------------- sentinel -------------------\033[0m"
    st 26379
    ;;
*)
    echo "Usage: rds start | stat redis and sentinel"
esac
