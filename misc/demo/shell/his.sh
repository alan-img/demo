#!/bin/bash

function metastoreIsOrNotEnabled() {
  flag=1
  while [ $flag -ne 0 ]
  do
    sleep 0.5
    netstat -nltp | grep 9083 > /dev/null 2>&1
    flag=$?
  done
  echo metastore is running
}

function hiveServer2IsOrNotEnabled() {
  flag=1
  while [ $flag -ne 0 ]
  do
    sleep 0.5
    netstat -nltp | grep 10000 > /dev/null 2>&1
    flag=$?
  done
  echo hiveServer2 is running
}

function start() {
  nohup hive --service metastore > /dev/null 2>&1 &
  metastoreIsOrNotEnabled

  nohup hive --service hiveserver2 > /dev/null 2>&1 &
  hiveServer2IsOrNotEnabled
}

function stop() {
  kill -9 $(netstat -nlpt | grep 9083 | awk '{print $7}' | awk -F "/" '{print $1}')
  echo stopping metastore
  kill -9 $(netstat -nlpt | grep 10000 | awk '{print $7}' | awk -F "/" '{print $1}')
  echo stopping hiveServer2
}

function metastoreStatus() {
  netstat -nlpt | grep 9083 > /dev/null 2>&1
  if [ $? -eq 0 ]; then
    echo metastore is running
  else
    echo metastore is not running
  fi
}

function hiveServer2Status() {
  netstat -nlpt | grep 10000 > /dev/null 2>&1
  if [ $? -eq 0 ]; then
    echo hiveserver2 is running
  else
    echo hiveserver2 is not running
  fi
}

case $1 in "start")
  echo -e "\033[40;36m--------------- starting HIVE ---------------\033[0m"
  start
;;
"stop")
  echo -e "\033[40;36m--------------- stopping HIVE ---------------\033[0m"
  stop
;;
"restart")
  echo -e "\033[40;36m--------------- restart HIVE ---------------\033[0m"
  stop
  start
;;
"stat")
  echo -e "\033[40;36m--------------- HIVE status ---------------\033[0m"
  metastoreStatus
  hiveServer2Status
;;
*)
echo function:
  echo " start | stop | stat | restart | metastore and hive server2"
  echo example:
  echo " his start"
  echo " his stop"
  echo " his stat"
  echo " his restart"
esac