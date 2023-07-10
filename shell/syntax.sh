# -eq 这种方式只能用在数字类型
# ==、!=这种方式可以用在数字和字符类型
a=10
if [ $a -eq 10 ]; then
    echo alan
else
  echo jack
fi
if [ $a == 10 ]; then
    echo alan
  else
    echo jack
fi
b=hello
if [ $b == hello ]; then
    echo alan
  else
    echo jack
fi
# case 语法示例
c=stop
case $c in
start)
  echo start test
  ;;
stop)
  echo stop test
  ;;
restart)
  echo restart test
  ;;
*)
  echo other
  ;;
esac
# for循环示例
for i in {1..5} ; do
    echo i = $i
done
for (( i = 0; i < 5; i++ )); do
    echo i = $i
done
# while循环示例
d=0
while [ $d != 10 ]; do
    echo d = $d
#    d=$[$d+1]
#    d=$(($d + 1))
#    let d++
    let d+=1
done
# 函数示例 传参用$1 $2按位置取
function add(){
  echo $[$1 + $2]
  return 10
}
add 1 2
echo $? # 获取函数返回值
# 输入参数示例
read -p "please input a num: " num
echo num: $num
# 取上级目录
dir=$(basename /opt/mod/apache-hive-3.1.2-bin)
echo dir: $dir
# 取当前目录
name=`dirname /opt/mod/apache-hive-3.1.2-bin`
echo name: $name
# s表示秒(默认)，m表示分钟，h表示小时
sleep 2s
# 获取监听指定端口的进程对应的进程号，这里提供两个版本
pid=$(netstat -nlpt | grep 22 | awk '{print $7}' | cut -d "/" -f 1)
echo pid = $pid
pid=`netstat -nlpt | grep 22 | awk '{print $7}' | awk -F "/" '{print $1}'`
echo pid = $pid
# 进程启动后等待直至进程开始监听端口
function hiveIsEnabled() {
  flag=1
  while [ $flag != 0 ]
  do
    sleep 0.5
    netstat -nltp | grep 10000 > /dev/null 2>&1
    # 获取netstat命令的返回值 返回0表示端口已经开始监听
    # 返回其他值表示没有开始监听
    flag=$?
  done
  echo hive is running
}
# 命令表现情况总结
# 纯前台进程 日志打印到控制台
sh test.sh
# 前台进程 日志打印到文件 前台不退出
sh test.sh > log 2>&1
# 窗口进程 日志打印到文件 前台退出 窗口一旦关闭 进程退出
sh test.sh > log 2>&1 &
# 纯后台进程 日志打印到文件 前台退出 窗口关闭 进程不退出
nohup sh test.sh > log 2>&1 &