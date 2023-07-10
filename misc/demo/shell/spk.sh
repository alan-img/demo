#!/bin/bash

case $1 in
"start")
    echo -e "\033[40;36m------------------- starting spark --------------------\033[0m"
    ssh hadoop101 "source /etc/profile; /opt/mod/spark-3.0.0-bin-hadoop3.2/sbin/start-history-server.sh"
    ssh hadoop101 "source /etc/profile; /opt/mod/spark-3.0.0-bin-hadoop3.2/sbin/start-thriftserver.sh"
    ;;
"stop")
    echo -e "\033[40;36m------------------- stopping spark --------------------\033[0m"
    ssh hadoop101 "source /etc/profile; /opt/mod/spark-3.0.0-bin-hadoop3.2/sbin/stop-history-server.sh"
    ssh hadoop101 "source /etc/profile; /opt/mod/spark-3.0.0-bin-hadoop3.2/sbin/stop-thriftserver.sh"
    ;;
"restart")
    echo -e "\033[40;36m------------------- restarting spark --------------------\033[0m"
    ssh hadoop101 "source /etc/profile; /opt/mod/spark-3.0.0-bin-hadoop3.2/sbin/start-history-server.sh"
    ssh hadoop101 "source /etc/profile; /opt/mod/spark-3.0.0-bin-hadoop3.2/sbin/start-thriftserver.sh"
    ssh hadoop101 "source /etc/profile; /opt/mod/spark-3.0.0-bin-hadoop3.2/sbin/stop-thriftserver.sh"
    ;;
"stat")
    echo -e "\033[40;36m------------------- spark status --------------------\033[0m"
    ssh hadoop101 "source /etc/profile; netstat -nlpt | grep 18080 > /dev/null 2>&1"
    if [ $? -eq 0 ]; then
      echo spark-history-server is running, listening 18080
    else
      echo spark-history-server is not running
    fi
    ssh hadoop101 "source /etc/profile; netstat -nlpt | grep 10001 > /dev/null 2>&1"
    if [ $? -eq 0 ]; then
      echo spark-thrift-server is running, listening 10001
    else
      echo spark-thrift-server is not running
    fi
    ;;
*)
    echo function:
    echo "  start | stop | restart | stat spark-history-server"
    ;;
esac
