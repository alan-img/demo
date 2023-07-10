###################################################################################
##################################### 常用密码 ####################################
###################################################################################
# 服务器通用密码:
root/_rljl1Q2W3.@

# 服务器重装密码
root/32dfadQ_

# Harbor个人密码:
336105/Abc156558

# 智能运维密码:
1q2w3e4r@_

# 容器云密码:
32433/Lyi@2021

# Redis连接授权密码:
auth dahua@b0HM1G3X2l

# 大数据基础平台密码
admin/fc@20210707

# 计算平台admin出事密码
admin/dahuacloud

# 常用软件目录
\\10.30.6.185\microsoft$\Micorosoft.ISO\Fan\soft
通用软件路径: \\PVS2788501713\software
研发软件路径: \\10.30.6.185\microsoft$\Micorosoft.ISO\Fan\soft

# 域名构成模板
hdp-hive-hdp-hive-server2-0.hdp-hive-hdp-hive-server2.cloudspace.svc.cluster.local

###################################################################################
#################################### 常用命令备忘 ##################################
###################################################################################
# 在线端口开放检测
http://duankou.wlphp.com/
#

###################################################################################
#################################### JVM内存调优 ##################################
###################################################################################
内存分区情况: 虚拟机栈/本地方法栈/程序计数器/堆内存/元空间/直接内存

# 支持远程调试JVM参数
-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005

# shell脚本中整数加1的方式 推荐使用第一种
let a++
let a+=1
((a++))
a=$(($a+1))
a=$[$a+1]
a=`expr $a + 1`

# window查看指定应用程序监听的端口
netstat -anob | grep --context=1 openvpn
netstat -anob -p tcp | grep --context=1 openvpn
netstat -anob -p udp | grep --context=1 openvpn

# 查看当前有多少进程
tasklist | grep chrome | wc -l

# while循环读取一个文件
while read line
do
  echo "line = ${line}"
done < filename.txt

# 将多行按照指定分隔符分成一行
cat filename.txt | xargs -i echo -n {},

# 将一行按照指定分隔符分隔为多行
echo "apple,banana,orange,grape" | tr ',' '\n'

# 统计一行或者一个文件中指定单词或分隔符的数量
grep -o ',' file.txt | wc -l

# 取某一行第7列后的所有列
awk '{print substr($0, index($0,$7))}' dossierIds.txt
# 取某一行第7列前的所有列
awk '{ for(i=1;i<=7;i++) printf("%s ", $i); print "" }' dossierIds.txt
# cut -d也能实现，但是它对分隔符有强要求，通用性较差
cut -d ' ' -f 7- dossierIds.txt
# 取某一列前的所有列
cut -d ' ' -f -7 dossierIds.txt

# shell命令去重 不sort进行uniq无法去重
sort -t' ' -uk7 dossierIds.txt

# JVM参数: -Dlog4j.debug 查看log4j.properties是从哪里加载的加载的详细信息
java -Dlog4j.debug -cp cluster-1.0-SNAPSHOT-jar-with-dependencies.jar com.fc.service.cluster.WasteService

# JVM最全配置
nohup java -Xmx2048m -Xms2048m -XX:MaxDirectMemorySize=512m -jar
-Djava.security.auth.login.config=/cloud/dahua/DataCompute/jaas.conf
-Djava.security.krb5.conf=/cloud/dahua/DataCompute/krb5.conf
-verbose:gc -Xloggc:/log/DataCompute/dossier-fix/Log/gc.log
-XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps
-XX:+PrintGCDetails -XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=102400k
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/log/DataCompute/dossier-fix/Log/dump_OOME.hprof
/cloud/dahua/DataCompute/dossier-fix/dossierfix.jar
-Dfile.encoding=utf-8 --spring.config.location=/cloud/dahua/DataCompute/dossier-fix/application-prod.properties
--spring.profiles.active=prod >> /log/DataCompute/dossier-fix/Log/start.log 2>&1

# jmx支持远程监控jvm运行情况配置 在启动jvm时添加
-Dcom.sun.management.jmxremote.port=9015 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false

# 通过jstatd监控 可以监控物理机上的所有java程序
1.添加配置文件
grant codebase "file:/opt/mod/jdk1.8.0_202/lib/tools.jar" {
    permission java.security.AllPermission;
};
2.启动命令 默认监听1099端口
nohup jstatd -J-Djava.security.policy=jstatd-all.policy > nohup.log 2>&1 &

# 使用JNA申请内存释放内存代码块
# 其实申请的堆外内存可以自动释放，在堆中的memory对象被gc掉时会自动调用finalize方法释放掉堆外内存，
# 但这种方式容易产生进程内存峰值，因为堆内如果设置的很大，堆内对象不会GC，一致申请堆外内存就容易产生进程内存峰值
// 申请
int num = 1024 * 1024 * Integer.parseInt(args[0]);
Memory memory = new Memory(num);
// 使用 注意JNA向内存写入数据才占内存 不写入即使new Memory()也不占堆外内存
memory.write(0, new byte[num], 0, num);
// 释放
Native.free(Pointer.nativeValue(memory));
Pointer.nativeValue(memory, 0L);
# 使用直接内存申请堆外内存释放内存代码，堆内对象byteBuffer被GC掉时会自动释放堆外内存，但是如果堆内不及时GC和JNA一样容易产生堆外内存峰值
// 申请
var byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * Integer.parseInt(args(0)))
// 释放
byteBuffer.asInstanceOf[DirectBuffer].cleaner().clean()
// 将对象置空（非释放必要动作 但建议这样做 内不能都释放了 置空是合理的）
byteBuffer = null

# 监控内存
top -p $(jps | grep Demo | cut -d ' ' -f 1)
jmap -heap $(jps | grep Demo | cut -d ' ' -f 1)

# 查看每个区域的比例
jstat -gc pid

# 查看新生代和老年呆的比例
jinfo -flag NewRatio 26544

# 查看新生代中eden和survivor的比例
jinfo -flag SurvivorRatio 26544

# 配置GC线程数
-XX:ParallelGCThreads

# 查看加载的类的数量 每秒打印一次 一共打印10次 每3行打印一次表头
jstat -class -t -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000 10
# 查看编译的信息
jstat -compiler $(jps | grep Demo | cut -d ' ' -f 1)
# 显示jit编译的方法
jstat -printcompilation $(jps | grep Demo | cut -d ' ' -f 1)
# 显示GC相关信息
jstat -gc -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000
jstat -gcutil -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000
jstat -gcapacity -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000
jstat -gccause -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000
jstat -gcnew -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000
jstat -gcnewcapacity -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000
jstat -gcold -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000
jstat -gcoldcapacity -h 3 $(jps | grep Demo | cut -d ' ' -f 1) 1000

# 显示系统的属性信息 和代码中写System.getProperties结果相同
jinfo -sysprops $(jps | grep Demo | cut -d ' ' -f 1)
# 查看曾经赋过值的一些参数 注意这里是flags
[root@hadoop104 ~]# jinfo -flags  $(jps | grep Demo | cut -d ' ' -f 1)
# 查看JVM启动时参数的初始值
[root@hadoop104 ~]# java -XX:+PrintFlagsInitial > initial.conf
# 查看JVM启参数的最终值 因为可能会别设置
[root@hadoop104 ~]# java -XX:+PrintFlagsFinal > final.conf
# 用户曾经更改多的值
[root@hadoop104 ~]# java -XX:+PrintCommandLineFlags > user_update.conf
# 主动dump堆内存映像
[root@hadoop104 ~]# jmap -dump:format=b,file=./dump.hprof $(jps | grep Demo | cut -d ' ' -f 1)
# 仅dump存活对象
[root@hadoop104 ~]# jmap -dump:live,format=b,file=./dump_live.hprof $(jps | grep Demo | cut -d ' ' -f 1)
# 整合常见的各种命令 显示支持的命令 然后即可直接使用
jcmd $(jps | grep Demo | cut -d ' ' -f 1) help

# 设置参数模板
java -Xms300m -Xmx300m -XX:SurvivorRatio=8 \
-XX:MaxMetaspaceSize=1024m -XX:MetaspaceSize=1024m \
-XX:MaxDirectMemorySize=10m \
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./dump.hprof \
-verbose:gc -Xloggc:./gc.log \
-XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps \
-XX:+PrintGCDetails -XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=102400k \
-Dfile.encoding=utf-8 \
-cp test-jar-with-dependencies.jar com.dahuatech.test.demo.Demo 9

# 常用虚拟机参数
-Xss虚拟机栈大小
-Xms最小堆内存
-Xmn设置堆中新生代内存大小 一般设置新生代和老年代比例即可 这个参数一般不用
新生代和老年代内存比例默认1:2
新生代中eden/s0/s1内存比例默认是8:1:1，如果要生效需要强制设置-XX:SurvivorRatio=8
-Xmx最大堆内存 一般来说最小堆和最大堆大小相同，这样避免扩容导致性能问题
设置元空间内存大小-XX:MaxMetaspaceSize=256m
    注: 元空间大小默认值依赖于平台window最先默认21M左右
    可以用这个命令查看: jinfo -flag MetaspaceSize <pid>
    最大默认值是非常大的整数18446744073709486080，表示无限制
    可以用这个命令查看: jinfo -flag MaxMetaspaceSize <pid>
直接内存大小设置可以通过-XX:MaxDirectMemorySize=10m这只，如果不指定则默认最堆的最大值-Xmx参数相同

# 启动Java进程设置JVM模板
java -Xms300m -Xmx300m -XX:SurvivorRatio=8 -cp test-jar-with-dependencies.jar com.dahuatech.test.demo.Demo
java -Xms300m -Xmx300m -XX:SurvivorRatio=8 -XX:MaxMetaspaceSize=20m -XX:MaxDirectMemorySize=10m -cp test-jar-with-dependencies.jar com.dahuatech.test.demo.Demo
# JVM新生代中的eden区和s0,s1区不是8:1:1，这是因为存在自适应机制，可以设置参数 XX:-UseAdaptiveSizePolicy来关闭自适应
# 但是这个参数已经不生效了，因此如果非要它们是8:1:1，则需要显示指定JVM参数-XX:SurvivorRatio=8
# YGC或者叫MinorGC动作发生的位置是年轻代，幸存者区或者叫from/to区总有一个是空的，
# 即如果Eden区满了将不是垃圾的对象转移到s0区，此时s1区是空的，第二次eden区满了会将将eden区和s0区中不是垃圾的对象转移到s1区
# 如果此时eden区又满了，则s1区的对象如果回收15次（默认值，可以通过参数-XX:MaxTenuringThreshold=15设置）都没有被回收则进入老年代，小于15次则进入s0区
# nohup命令模板
nohup java -jar xxx.jar > log 2>&1 &

# 指定log4j日志配置文件加载
java -Dlog4j.configuration=file:/root/alan/log4j.properties -cp cluster-1.0-SNAPSHOT-jar-with-dependencies.jar com.fc.service.cluster.WasteService

# 脚本输入密码模板
/usr/bin/expect <<-EOF
spawn scp -P2222 ${BACKUP_DIR}/instance_bak.tar.gz root@${FTP_HOST}:/downloadCenter/
expect {
"*password:" { send "$FTP_PASSWD\r" }
}
expect eof
EOF

# 宿主机监控容器内存
#!/bin/bash
for i in {1..3000000}
do
  echo "" >> /tmp/docker/mem.log
  echo "$(date "+%Y-%m-%d %H:%M:%S")" >> /tmp/docker/mem.log
  echo "$(docker stats --no-stream 6cc265cc7618)" >> /tmp/docker/mem.log
  sleep 2
done

# linux按2000000行分割为一个文件 前缀使用split 两位数字编号 eg: split01
split -l 2000000 face_img_url.txt -d split -a 2

# 无论删除软链接文件还是软链接文件夹统一使用rm -f 不使用rm -rf 不然会删除软链接文件夹下的所有文件
# 注意文件夹后不能加"/"
# 如 “rm -f dirname/” 这是错误的用法
# 正确的用法是"rm -f dirname"
rm -f 软链接文件或软连接文件夹

# 限制资源启动容器
docker run --memory=32g --cpus 16 --name gabyscluster -it --gpus 2 -v /dahuacloud/user/data/data0001/home/workspace/images_data:/root/gab_test/images nelivacn/fat_gpu:v2.4 bash

# 批量重命名
rename 111.txt .txt ./*

# 查询文件大小大于100k的文件有多少个
find images_set -type f -size +100k | wc -l

# 查询大于50k小于100k的文件
find images_set -type f -size +50k -size -100k | wc -l

# 过滤出一个文件件中所有大于1k的文件并将其复制到另一个目录
find /path/to/source/directory -type f -size +1k -exec cp {} /path/to/destination/directory \;

# netcat检查端口是否放开 ncat和nc都是同一个命令
ncat -zv www.baidu.com 443
nc -zv www.baidu.com 443

# nc和telnet结合使用 实现双向通信
服务端: nc -lk 9999
客户端: telnet localhost 9999

# 扫描172.13.130.2机器tcp端口1-100的开放情况
nc -v -z -w2 172.13.130.2 1-1024

# 扫描172.13.130.2机器udp端口1-100的开放情况
nc -u -z -w2 192.168.0.1 1-1024

# 按照大小查找删除
find . -type f -size -15k -delete
find . -type f -size +50k -delete

# 原目录下将每个文件翻倍 文件名不同
find . -name "face_records*" | awk -F'./' '{print $2}' | xargs -i cp -af {} ./1_{}

# 统计监控日志的最大内存占用量
grep root /root/gab_test/505w_mem.log | awk '{print $6}' | sort -hr | head -1

# 从指定行截取文件并写入文件
sed -n '10,20p' file.txt
sed -n '10,20p' file.txt > truncate.txt

# 设置主机免密登录
ssh-keygen -t rsa
ssh-copy-id 10.38.21.45
_rljl1Q2W3.@

# 拉文件
rsync -rl root@172.13.130.2:/clear_data_container_cloud.tar.gz ./
scp -r root@172.13.130.2:/clear_data_container_cloud.tar.gz ./

# 推文件
rsync -rl ./dossier-fix.tar root@172.13.130.2:/
scp -r ./dossier-fix.tar root@172.13.130.2:/

# 列出tar包中的文件
tar -tf xxx.tar.gz

# 不解压tar包查看文件内容
tar -zxvf face_body_region.tar.gz body_region_info.csv -O | head -10

###################################################################################
################################## 常用工具及杂项 #################################
###################################################################################
# 时间戳转换比较好用 可选择秒或者毫秒
https://tools.fun/timestamp.html

# json格式化比较好用
https://www.sojson.com/

# json和yaml相互转化比较好用
https://www.json2yaml.com/

# json -> yaml 这个可作为功能备用站 目前没发现比较好用的功能个
http://www.wetools.com/

# 禁用selinux
vim /etc/selinux/config
SELINUX=disabled

# 复制文件
cp face_region_info.csv test/

# 强制覆盖
\cp -nar face_region_info.csv test/

# 递归复制
\cp -nar face_region_info.csv test/

# Linux新增用户
useradd alan
# 修改密码
passwd alan
# 删除用户-不会删除家目录
userdel alan
# 删除用户-删除家目录
userdel -r alan

# 查看放行的端口和协议
firewall-cmd --list-all

# 修改文件或文件夹所有者
chown root test
# 修改文件或文件夹所在组
chgrp root test
# 修改文件或文件夹所有者和所在组
chown root:root test
# 递归修改文件或文件夹的所有者和所在组
chown -R root:root test

# 压缩tar.gz包
### 注意这里通过API遍历每个压缩后的文件 文件名是"./face_associated_region_info.csv" 也就是前面带"./" ###
tar -zcvf demo.tar.gz ./face_associated_region_info.csv ./face_region_info.csv
# 压缩为tgz包
tar -zcvf demo.tgz ./face_associated_region_info.csv face_region_info.csv
# 压缩zip包
zip -r demo.zip ./face_associated_region_info.csv ./face_region_info.csv
# 解压zip包
unzip -d ./demo demo.zip
# 查看CPU信息发
cat /proc/cpuinfo
# 查看内存信息
cat /proc/meminfo
# 查看物理CPU个数
[root@hadoop101 alan]# cat /proc/cpuinfo | grep "physical id" | sort -u | wc -l
# 查看物理CPU核数
[root@hadoop101 alan]# cat /proc/cpuinfo | grep "core id" | sort -u | wc -l
# 查看逻辑CPU核数 = 一般每个物理CPU超线程出两个逻辑核心 即每个物理核虚拟出两个逻辑核
[root@hadoop101 alan]# cat /proc/cpuinfo | grep "processor" | sort -u | wc -l
# 监控指定进程的内存占用情况 -d 执行间隔 -b 以批处理模式操作 -n 更新多少次 -p 进程ID
top -b -d 3 -n 9999999 -p <PID> >> mem.log
# 查看非空行
grep -v '^$' face_img_url.txt
# 查看空行
grep '^$' face_img_url.txt
# 删除文件空行
sed -i '/^$/d' filename.txt
# 统计空行数
grep '^$' face_img_url.txt | wc -l
# 取第二行到结束的所有内容
tail -n +2
# 内存占用监控脚本
#!/usr/bin/env bash
for i in $(seq 1 $1) ; do
    echo " " >> log
    echo "$(date "+%Y-%m-%d %H:%M:%S")" >> log
    echo "$(docker stats --no-stream f1128185dd34)" >> log
    sleep 1
done
# 主动生成heapdump文件
容器内dump jvm内存方法:
进入 /usr/local/java/openlogic-openjdk-8u342-b07-linux-x64/bin
./jps 找到main class为空白的pid, jpype拉起的jvm
执行
./jmap -dump:format=b,file=/root/gab_test/images/xxx.hprof <PID>
# shell输出日期
echo $(date "+%Y-%m-%d %H:%M:%S")
# 排序 -n按数字排序 -r倒序 -u去重
sort -nr
# docker直接根据关键字进入容器
#!/bin/bash
all=`docker ps | grep -v pause | grep -v sidecar | grep $1 | head -n 1`
ret=($all)
docker exec -it ${ret[0]} /bin/bash
# python脚本文件头声明
# !/usr/bin/env python
# -*- coding:utf-8 -*-
# 将python脚本的标准输出重定向到文件 必须加-u 否则无法重定向到文件
nohup python -u test.py > log 2>&1 &
# 查看某个端口是否被监听以及监听进程的pid
1.netstat -nlpt | grep 2222 | awk '{print $7}' | awk -F "/" '{print $1}'
2.netstat -nlpt | grep 2222 | awk '{print $7}' | cut -d '/' -f 1
# 查看上一条命令是否正常执行 如果正常执行则返回0 否则返回1
echo $?
# 查看被封的IP
fail2ban-client status sshd
# 解禁被封的IP
fail2ban-client set sshd unbanip 10.33.114.78
# 查看磁盘信息
fdisk -l | grep GB
# 查看每块盘的分区
lsblk
# Java启动进程命令 包含各种参数
java -Xms10240m -Xmx10240m -XX:MaxDirectMemorySize=8192m -jar -verbose:gc -Xloggc:/log/DataCompute/OC-retrieval/Log/gc.log -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=102400k -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/log/DataCompute/OC-retrieval/Log/dump_OOME.hprof -Djava.security.auth.login.config=/cloud/dahua/DataCompute/jaas.conf -Djava.security.krb5.conf=/cloud/dahua/DataCompute/krb5.conf -XX:+UseG1GC /cloud/dahua/DataCompute/OC-retrieval/clustercompare.jar -Dfile.encoding=utf-8
# Kill监听指定端口的进程
kill -9 $(netstat -nlpt | grep 9092 | awk '{print $7}' | awk -F "/" '{print $1}')
或
kill -9 $(netstat -nlpt | grep 9092 | awk '{print $7}' | cut -d '/' -f 1)
# 查看监听指定端口的进程
ps -ef | grep $(netstat -nlpt | grep 9090 | awk '{print $7}' | awk -F "/" '{print $1}')
# 查看PCIE设备信息
lspci | grep T4
# 查看当前进程有多少线程正在运行
ps -T 842956 # 会显示具体的执行命令
ps -T -p 842956 # 执行程序类型， 是java还是python还是其它
# 监视系统输入输出设备和CPU的使用情况
iostat -x 1
# 监控cpu使用情况
vmstat 1
# 启动crontab守护线程
systemctl start crond
systemctl stop crond
systemctl status crond
systemctl restart crond
systemctl reload crond
# curl以原文件名下载文件
curl -O http://10.38.24.2/images/face_img/1289541187203497989.jpg
# curl下载文件并重命令
curl http://10.38.24.2/images/face_img/1289541187203497989.jpg -o demo.jpg
# curl下载文件并重命令且指定下载路径
curl http://10.38.24.2/images/face_img/1289541187203497989.jpg -o /a/b/demo.jpg
curl是一个非常常用的命令行工具,它主要用于发送HTTP请求, 以下是curl最常用的用法:
发送GET请求: 使用curl加上URL即可发送GET请求,例如: curl https://www.example.com
发送POST请求: 使用curl加上-d参数即可发送POST请求,例如: curl -d "param1=value1&param2=value2" https://www.example.com
发送PUT请求: 使用curl加上-X参数指定请求方法为PUT,例如: curl -X PUT -d "param1=value1&param2=value2" https://www.example.com
下载文件: 使用curl加上-O参数即可下载文件,例如: curl -O https://www.example.com/file.zip
上传文件: 使用curl加上-F参数即可上传文件,例如: curl -F "file=@/path/to/file" https://www.example.com/upload
设置请求头: 使用curl加上-H参数即可设置请求头,例如: curl -H "Content-Type: application/json" https://www.example.com
这些是curl最常用的用法,当然还有很多其他的用法,可以通过curl --help命令查看更多用法.
# wget以原文件名下载文件
wget http://10.38.24.2/images/face_img/1289541187203497989.jpg
# 下载多个文件:
wget http://example.com/file1.zip http://example.com/file2.zip
# 下载并保存文件到指定目录:
wget -P /path/to/directory http://example.com/file.zip
# 下载并重命名文件:
wget -O newname.zip http://example.com/file.zip
# 后台下载文件:
wget -b http://example.com/file.zip
# 断点续传下载:
wget -c http://example.com/file.zip
# 下载文件并限制下载速度:
wget --limit-rate=200k http://example.com/file.zip
# 手动替换dossier-fix的jar包
kubectl cp ./dossierfix.jar cloudspace/pc-offline-dossier-fix-0:/cloud/dahua/DataCompute/dossier-fix/
docker restart 4a79aae5036b
# 手动清理fc_face_dossier
create table if not exists fc_face_dossier_like like fc_face_dossier;
select * from fc_face_dossier_like;
drop table if exists fc_face_dossier;
alter table stu fc_face_dossier_like to fc_face_dossier;
# 查看SO库有哪些接口方法
nm -D libXXX.so
# 大OC统计类簇关键字
total increment size
# JNA结构体笔记
1.结构体对象能不能toArray跟new这个对象时加不加ByReference没有关系 只取决于你需不需要
2.结构体对象new时加了ByReference在赋值时需不需要toArray跟这个对象是数据还是单个对象有关系
    2.1.如果有多个元素必须toArray
    2.2.如果是一个元素toArray不toArray均可
###################################################################################
#################################### log4j配置文件 ################################
###################################################################################
# log4j日志输出格式模板
[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n

# log4j.properties日志文件模板(更改过日志输出格式的版本)
# FATAL/ERROR/WARN/INFO/DEBUG/TRACE/OFF/ALL
log4j.rootLogger=INFO,consoleAppender
#log4j.rootLogger=INFO,fileAppender,consoleAppender

# ConsoleAppender
log4j.appender.consoleAppender=org.apache.log4j.ConsoleAppender
log4j.appender.consoleAppender.layout=org.apache.log4j.EnhancedPatternLayout
#未开启多线程ConversionPattern设置
#log4j.appender.consoleAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n
#开启多线程ConversionPattern设置
log4j.appender.consoleAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n

# FileAppender
log4j.appender.fileAppender=org.apache.log4j.FileAppender
log4j.appender.fileAppender.file=./log/common.log
log4j.appender.fileAppender.Append=true
log4j.appender.fileAppender.encoding=UTF-8
log4j.appender.fileAppender.layout=org.apache.log4j.EnhancedPatternLayout
#未开启多线程ConversionPattern设置
#log4j.appender.fileAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n
#开启多线程ConversionPattern设置
log4j.appender.fileAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n

# RollingFileAppender
log4j.appender.rollingFileAppender=org.apache.log4j.RollingFileAppender
log4j.appender.rollingFileAppender.File=./log/common.log
log4j.appender.rollingFileAppender.Encoding=UTF-8
log4j.appender.rollingFileAppender.MaxFileSize=256KB
log4j.appender.rollingFileAppender.Append=true
log4j.appender.rollingFileAppender.MaxBackupIndex=10
log4j.appender.rollingFileAppender.layout=org.apache.log4j.EnhancedPatternLayout
#未开启多线程ConversionPattern设置
#log4j.appender.rollingFileAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n
#开启多线程ConversionPattern设置
log4j.appender.rollingFileAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n

# DailyRollingFileAppender
log4j.appender.dailyRollingFileAppender=org.apache.log4j.DailyRollingFileAppender
log4j.appender.dailyRollingFileAppender.File=./log/common.log
log4j.appender.dailyRollingFileAppender.DatePattern=yyyy-MM-dd
log4j.appender.dailyRollingFileAppender.layout=org.apache.log4j.EnhancedPatternLayout
#未开启多线程ConversionPattern设置
#log4j.appender.dailyRollingFileAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n
#开启多线程ConversionPattern设置
log4j.appender.dailyRollingFileAppender.layout.ConversionPattern=[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%t] [%c{1}:%L]: %m%n

###################################################################################
################################### 常用域名及模板 ################################
###################################################################################
# NameNode域名
hdp-hadoop-hdp-namenode-0.hdp-hadoop-hdp-namenode.cloudspace.svc.cluster.local
# ResourceManager域名
hdp-hadoop-hdp-resourcemanager-1.hdp-hadoop-hdp-resourcemanager.cloudspace.svc.cluster.local
# dossier-fix域名
pc-offline-dossier-fix-0.pc-offline-dossier-fix.cloudspace.svc.cluster.local
# Hive域名
hdp-hive-hdp-hive-server2-0.hdp-hive-hdp-hive-server2.cloudspace.svc.cluster.local
# 域名构成模板
hdp-hive-hdp-hive-server2-0.hdp-hive-hdp-hive-server2.cloudspace.svc.cluster.local
# zk域名
hdp-zookeeper-hdp-zookeeper-0.hdp-zookeeper-hdp-zookeeper.cloudspace.svc.cluster.local
# kafka域名
hdp-kafka-hdp-kafka-0.hdp-kafka-hdp-kafka.cloudspace.svc.cluster.local
###################################################################################
################################### Kafka常用命令 #################################
###################################################################################
# 查看所有topic
kafka-topics.sh --zookeeper hdp-zookeeper-hdp-zookeeper-0.hdp-zookeeper-hdp-zookeeper.cloudspace.svc.cluster.local:2181/kafka --list

# 创建topic
kafka-topics.sh --zookeeper hdp-zookeeper-hdp-zookeeper-0.hdp-zookeeper-hdp-zookeeper.cloudspace.svc.cluster.local:2181/kafka --create --partitions 3 --replication-factor 3 --topic first

# 描述topic
kafka-topics.sh --zookeeper hdp-zookeeper-hdp-zookeeper-0.hdp-zookeeper-hdp-zookeeper.cloudspace.svc.cluster.local:2181/kafka --describe --topic first

# 修改topic: 增加分区 分区不能减少 只能增加 命名行不能增加副本数
kafka-topics.sh --zookeeper hdp-zookeeper-hdp-zookeeper-0.hdp-zookeeper-hdp-zookeeper.cloudspace.svc.cluster.local:2181/kafka --alter --partitions 4 --topic first

# 删除topic
kafka-topics.sh --zookeeper hdp-zookeeper-hdp-zookeeper-0.hdp-zookeeper-hdp-zookeeper.cloudspace.svc.cluster.local:2181/kafka --delete --topic first

# 消费消息
kafka-console-consumer.sh --bootstrap-server $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092 --topic first

# 从头消费消息
kafka-console-consumer.sh --bootstrap-server $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092 --from-beginning --group kafka-consumer-group --topic first

# 生产消息 非必要不要用
kafka-console-producer.sh --broker-list $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092 --topic first

# 查看所有消费者组
kafka-consumer-groups.sh --bootstrap-server $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092 --list

# 描述消费者组
kafka-consumer-groups.sh --bootstrap-server $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092 --describe --group kafka-consumer-group

# 删除消费者组
kafka-consumer-groups.sh --bootstrap-server $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092 --delete --group kafka-consumer-group

# 将kafka的偏移量置于最新
kafka-consumer-groups.sh --bootstrap-server $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092  --reset-offsets --to-latest --all-topics --execute --group kafka-consumer-group

# 将kafka的偏移量置于最老
kafka-consumer-groups.sh --bootstrap-server $(ifconfig | head -n +2 | grep netmask | awk '{print $2}'):9092  --reset-offsets --to-earliest --execute --all-topics --group fc_face_body_valid_record_group
###################################################################################
################################### Hbase常用命令 #################################
###################################################################################
# 统计数量
count 'p_face_dossier'
count 'p_face_relation'
count 'p_face_cluster'
# 查询第一条数据
scan 'p_face_dossier', {LIMIT =>1}
scan 'p_face_relation', {LIMIT =>1}
scan 'p_face_cluster', {LIMIT =>1}
# 查询指定rowkey的数据
get 'p_face_dossier', '00000006946499106833363534'
get 'p_face_relation', '00000006946499106833363534'
get 'p_face_cluster', '00000006946499106833363534'
# 解析p_face_dossier表字节二进制数据
get 'p_face_dossier', '00000006946499106833363534', {COLUMN => ['info:ageGroup:toInt', 'info:dossierId:toString','info:gender:toInt']}
# 清空hbase表数据 保留表和表结构
truncate_preserve 'p_face_dossier'
truncate_preserve 'p_face_cluster'
truncate_preserve 'p_face_relation'
###################################################################################
################################### Yarn常用命令 ##################################
###################################################################################
# Kerberos鉴权
kinit hadoop/1q2w3e4r_
# 列出所有正在运行的任务
yarn application -list
# 根据Application状态过滤: yarn application -list -appStates （所有状态: ALL、NEW、NEW_SAVING、SUBMITTED、ACCEPTED、RUNNING、FINISHED、FAILED、KILLED）
yarn application -list -appStates FINISHED
# kill掉Application
yarn application -kill application_1612577921195_0001
# 查看Application日志
yarn logs -applicationId application_1612577921195_0001
# 查询Container日志
yarn logs -applicationId application_1612577921195_0001 -containerId container_1612577921195_0001_01_000001
# 列出所有Application尝试的列表
yarn applicationattempt -list application_1612577921195_0001
# 打印ApplicationAttemp状态
yarn applicationattempt -status appattempt_1612577921195_0001_000001
############# 只有在任务跑的途中才能看到container的状态 #############
# 列出所有Container
yarn container -list appattempt_1612577921195_0001_000001
# 打印Container状态
yarn container -status container_1612577921195_0001_01_000001
# 列出所有节点
yarn node -list -all
# 加载队列配置
yarn rmadmin -refreshQueues
# 打印队列信息
yarn queue -status default
###################################################################################
################################## docker常用命令 #################################
###################################################################################
# 列出所有本地镜像
docker images
# 列出所有本地镜像只显示镜像ID 包含虚悬镜像
docker images -qa
# 删除镜像
docker rmi 7bb40dd4663c
# 强制删除镜像
docker rmi -f 7bb40dd4663c
# 批量删除镜像 慎用
docker rmi -f $(docker images -qa)
# 列出所有正在运行的容器
docker ps
# 列出所有正在运行的容器 只显示容器ID
docker ps -q
# 列出所有容器 包含已经退出的容器
docker ps -a
# 列出所有容器 包含已经退出的容器 只显示容器ID
docker ps -qa
# 查看容器日志
docker logs 510b32612280
# 查看容器内运行的进程
docker top 510b32612280
# 查看容器内部细节
docker inspect 510b32612280
# 从容器中复制文件
docker cp 510b32612280:/Solve_core_hs.sh
# 复制文件到容器
docker cp Solve_core_hs.sh 510b32612280:/home
# 展示一个镜像形成的历史
docker history 9ccd7446e201
# 查看docker的版本
docker version
# 在docker hub中搜索镜像 没有TAG就是最新版latest
docker search nginx:[TAG]
# 登录docker registry
docker login registry.dahuatech.com
# 登出docker registry
docker logout registry.dahuatech.com
# 查看镜像/容器/数据卷所占的空间
docker system df
# 拉取镜像命令
docker pull registry.dahuatech.com/person-cluster-harbor/dossier-fix:V1.000.0000000.0.R.230330.fa971e0d-261405
# 将镜像打成tar包 便于复制到其他环境中
docker save -o dossier-fix-V1.000.0000000.0.R.230403.421167bc-262508.tar registry.dahuatech.com/person-cluster-harbor/dossier-fix:V1.000.0000000.0.R.230403.421167bc-262508
# 加载打成tar包的镜像
docker load -i dossier-fix-V1.000.0000000.0.R.230403.421167bc-262508.tar
# 指定列查看
docker ps -a --format "table {{.ID}}\t{{.Image}}\t{{.Names}}\t{{.Status}}"
# 将容器打成tar包
docker export -o demo.tar 510b32612280docker
# 加载打成tar包的容器镜像 注意: 这里加载的镜像REPOSITORY和TAG都为none 需要借助tag命令改名
docker import demo.tar
# 镜像改名
docker tag 86ac7df7f2b2 dossier-fix:V1.000.0000000.0.R.230403.421167bc-262508
# 停止容器
docker stop aa0883fd0001
# 重启容器
docker restart aa0883fd0001
# 强制停止容器
docker kill aa0883fd0001
# 删除单个容器
docker rm aa0883fd0001
# 批量删除容器
docker rm -f $(docker ps -qa)
# dockerfile生成镜像
FROM nelivacn/fat_gpu:v2.4
RUN mkdir -p /home/workspace/gabyscluster /root/gab_test
COPY ./entrypoint.sh /home/workspace/gabyscluster/
ENTRYPOINT sh /home/workspace/gabyscluster/entrypoint.sh
###################################################################################
################################### k8s常用命令 ###################################
###################################################################################
# 编辑容器镜像地址 直接重新拉起容器
kubectl edit sts pc-offline-dossier-fix -n cloudspace
# 查看Pod日志
kubectl logs pc-offline-dossier-fix-0 -f -n cloudspace
# 监控pod的状态
kubectl get pods -w -o wide -n cloudspace | grep fix
# 描述Pod
kubectl describe pod pc-offline-dossier-fix-0 -n cloudspace
# 通过k8s进入容器
kubectl exec -it pc-offline-dossier-fix-0 -n cloudspace -- /bin/bash
# 使用kubectl cp复制文件到容器
kubectl cp ./dossierfix.jar cloudspace/pc-offline-dossier-fix-0:/cloud/dahua/DataCompute/dossier-fix
# 从容器中复制文件都本地
kubectl cp cloudspace/pc-offline-dossier-fix-0:/cloud/dahua/DataCompute/dossier-fix/application-prod.properties ./application-prod.properties
###################################################################################
################################# Spark&Hive常用命令 ##############################
###################################################################################
# 使用beeline连接hive:
kinit hadoop/1q2w3e4r_
beeline -u "jdbc:hive2://localhost:10000/rljl;principal=hadoop/hdp-hive-hdp-hive-server2-0.hdp-hive-hdp-hive-server2.cloudspace.svc.cluster.local@DAHUA.COM"
beeline -u jdbc:hive2://hadoop101:10000/default -n root
# spark-shell支持(hive-site.xml必须在spark安装目录的conf下）
spark-shell --master yarn --conf spark.yarn.queue=fcqueue --executor-memory 1G --executor-cores 2 --num-executors 2 --driver-memory 1G --driver-cores 1
spark-shell --master yarn --conf spark.yarn.queue=default --executor-memory 1G --executor-cores 2 --num-executors 2 --driver-memory 1G --driver-cores 1
# spark-sql支持(hive-site.xml必须在spark安装目录的conf下）
spark-sql --master yarn --queue fcqueue --database rljl --executor-cores 2 --num-executors 2 --executor-memory 1G --conf "spark.hadoop.hive.cli.print.header=true"
spark-sql --master yarn --queue default --database default --executor-cores 2 --num-executors 2 --executor-memory 1G --conf "spark.hadoop.hive.cli.print.header=true"
# thriftserver(hive-site.xml必须在spark安装目录的conf下）
start-thriftserver.sh --master yarn --conf spark.yarn.queue=default --executor-memory 1G --executor-cores 2 --num-executors 2 --driver-memory 1G --driver-cores 1
beeline -u jdbc:hive2://hadoop101:10001/default -n root
###################################################################################
##################################### HDFS常用命令 ################################
###################################################################################
# 鉴权
kinit hadoop/1q2w3e4r_
# ls文件
hadoop fs -ls /
# 上传文件或文件夹
hadoop fs -put demo.txt /
hadoop fs -put application_1679561312870_2097/ /
# 下载文件或文件夹
hadoop fs -get /demo.txt ./
hadoop fs -get /application_1679561312870_2097 ./
# 文件或文件夹改名
hadoop fs -mv /a.txt /demo.txt
hadoop fs -mv /application_1679561312870_2097 /demo
# 删除文件
hadoop fs -rm -f /demo.txt
# 删除文件夹
hadoop fs -rm -r /demo
###################################################################################
##################################### Hive常用命令 ################################
###################################################################################
# 连接Hive常用设置
use rljl;
set spark.yarn.queue=fcqueue;
set hive.cli.print.header=true;
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
set spark.driver.cores=1;
set spark.driver.memory=1G;
set spark.executor.instances=1;
set spark.executor.cores=2;
set spark.executor.memory=2G;
set hive.compute.query.using.stats=false;
# 库相关
create database if not exists rljl comment 'stu_comment';
show databases ;
show databases like 'rljl';
desc database rljl;
desc database extended rljl;
show create database rljl;
###### 删除库一定要慎重 ######
drop database if exists rljl cascade;
# 表相关
create table if not exists stu(name string, age bigint) comment 'stu_comment' partitioned by (dt string) stored as parquet;
show tables ;
show tables like 'stu';
desc stu;
desc extended stu;
show create table stu;
drop table if exists stu;
desc formatted stu;
# 清空表 spark sql不支持if exists操作 注意清空表不会删除表分区字段 如需删除需要使用drop partition删除分区
truncate table if exists stu;
truncate table if exists stu;
# 修改表名
alter table stu rename to stu_rename;
alter table stu_rename rename to stu;
# 复制表结构 如果是分区表同样会复制为分区表
create table if not exists copy_stu_structure like stu;
# 连同数据复制表 这种方式即使stu是分区表 复制的表也不是分区表 就是普通表 这要注意
create table if not exists copy_stu_with_data as select * from stu;
# 向分区表插入数据
insert into table stu partition (dt = '2022') values ('alan', 10);
# 使用select向分区表插入数据 推荐使用
insert into table stu partition (dt = '2022') select null, 20;
# select覆盖插入数据 注意overwrite是针对分区的overwrite 对于不涉及的分区数据不overwrite
insert overwrite table stu partition (dt = '2022') select 'adam', 23;
# 查看表分区
show partitions stu;
# 查看分区表详细信息
desc formatted stu;
# 增加表分区
alter table stu add partition (dt = '2023') partition (dt = '2024');
# 删除表分区
alter table stu drop partition (dt = '2023'), partition (dt = '2024');
# 分组后过滤
select a, avg(b) as avg from t3 group by a having avg > 7;
# 显示所有内置函数
show functions ;
# 显示内置简单用法
desc function upper;
# 显示内置函数详细用法
desc function extended upper;
# 常用内置函数
select nvl(null, 10);
select concat('alan', 'jack');
select concat_ws('-', 'alan', 'jack');
# explode炸开数组 附测试数据
create table if not exists fun(arr array<struct<name: string, age: int>>, mapCol map<string, int>, arr1 array<string>);
insert into fun values(
    `array`(named_struct('name', 'alan', 'age', 23), named_struct('name', 'jack', 'age', 24), named_struct('name', 'jack', 'age', 30)),
    `map`('address', 10, 'name1', 22, 'name2', 23),
    `array`('str1', 'str2', 'str3')
);
select * from fun;
select arr[0], size(arr), mapcol['address'], size(mapCol), arr[0].name from fun;
select mapCol, mapC from fun lateral view explode(arr) funTableAlias as mapC;
select collect_list(mapCol) from fun lateral view explode(arr) funTableAlias as mapC;
select collect_set(mapCol) from fun lateral view explode(arr) funTableAlias as mapC;
select split('alan-jack', '-');
select explode(split('alan-jack', '-'));
select explode(mapCol) from fun;
select explode(arr1) from fun;
# get_json_object函数
create table if not exists stu(name string, age bigint) partitioned by (dt string) stored as parquet ;
insert into stu select '[{\"name\":\"alan\",\"age\":23},{\"name\":\"adam\",\"age\":25},{\"name\":\"jack\",\"age\":30}]', 20, 2022;
select get_json_object(name, '$[0].name') from stu;
# json_tuple
insert into stu select '{\"name\":\"jack\",\"age\":30}', 20, 2022;
select json_tuple(name, 'name', 'age') from stu;
# 将json数组炸开 输出参数示例: [{"name": "alan", "age": 15},{"name": "jack", "age": 26}] (元素之间无空格)
select explode(split(regexp_replace(regexp_replace('[{"name": "alan", "age": 15},{"name": "jack", "age": 26}]', '\\[|\\]',''),'\\}\\,\\{','\\}\\;\\{'),'\\;'));
# 将字符串数组炸开 输出参数示例: ["1001","1002","1003"] (元素之间无空格)
select explode(split(regexp_replace(regexp_extract('["1001","1002","1003"]', '^\\[(.+)\\]$', 1), '"',''), ','));
# 测试窗口函数
insert into t3 values(1, 2), (1, 3), (1, 4), (2, 5), (2, 6), (2, 7), (3, 8), (3, 9);
select
sum(b) over(partition by a order by b rows between current row and unbounded following),
rank() over (partition by a order by b),
dense_rank() over (partition by a order by b),
row_number() over (partition by a order by b),
count() over(partition by a),
max(b) over(partition by a order by b),
lag(b, 1) over(partition by a order by b),
lead(b, 1) over(partition by a order by b)
from t3;
###################################################################################
#################################### Redis常用命令 ################################
###################################################################################
# 选中指定库
select 11
# Redis连接授权密码:
auth dahua@b0HM1G3X2l
# 查看List类型Key的全部数据
lrange FC:PARTITION_INFO_LIST 0 -1
###################################################################################
##################################### Git常用命令 #################################
###################################################################################
# 总结三种对提交的操作: 撤销提交、删除提交、还原提交
撤销提交: 不会暴露撤销记录/不会对修改的内容进行还原,撤销后就是修改后未提交后的状态
删除提交: 不会暴露删除记录/会对修改的内容进行还原
还原提交: 会暴露还原记录/会对修改的内容进行还原,不推荐使用,因为会多两条提交记录且必须要提交到远程才能生效
# 新建Git项目步骤
直接在IDE中创建项目,勾选创建git仓库,在远程gitlab上创建 同名 仓库,最后在提交时关联起来,
之所以怎么做,不采用在gitlab创建后,在git clone下来,主要是我们的项目大多数是采用的Maven构建,
采用git clone的方式不会默认给我们构建后项目目录结构
###################################################################################
################################# 常用MarkDown语法 ################################
###################################################################################
# MarkDown开头模板
**作者**: 秦加伟
**日期**: 2022-12-10
***
# 生成目录
[TOC]
# 自定义自验颜色和大小
<font face="微软雅黑" color="red" size="4">demo</font>
# 加粗
**加粗**
# 斜体
*斜体*
# 下划线
<u>下划线</u>
# 删除线
~~删除线~~
# 注释
<!--注释-->
# 文本高亮
<mark>文本高亮</mark>
# 超链接
[知乎](https://www.zhihu.com/)
# 水平分隔符
***
# 插入图片
![壁纸](./img/wallpaper.jpg)