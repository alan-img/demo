#!/bin/bash

# 获取输入参数个数，如果没有参数，直接退出
pcount=$#
if((pcount==0)); then
echo function:
echo "	distributed files or folders to other server"
echo example:
echo "	syn  /opt/mod/"
echo "	syn  ./"
exit;
fi

# 获取文件名称
p1=$1
fname=`basename $p1`

# 获取上级目录到绝对路径
pdir=`cd -P $(dirname $p1); pwd`

# 获取当前用户名称
user=`whoami`

# hostname
localhost=`hostname`

# 循环
for((i = 101; i <= 103; i++)); do
        echo -e "\033[40;36m------------------- hadoop$i -------------------\033[0m"
        rsync -rl $pdir/$fname $user@hadoop$i:$pdir
		echo synchronized hadoop$i successfully
done
