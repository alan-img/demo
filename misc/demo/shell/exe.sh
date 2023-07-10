#!/bin/bash

# 获取命令行参数数量
pcount=$#
if (( pcount == 0 )); then
        echo function:
        echo "  execute commands on cluster"
        echo example:
        echo "  exe pwd"
        echo "  exe \"ls -l /\""
        exit;
fi

# 获取当前脚本执行的路径
dir=$(pwd)

# 循环执行
for((i = 101;i <= 103; i++));
do
    echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
    ssh hadoop$i "source /etc/profile; cd $dir; $1"
done