#!/bin/bash

cd /opt/mod/spark-2.4.4-bin-hadoop2.7/sbin

case $1 in "start")
    echo -e "\033[40;36m--------------- starting SPK ---------------\033[0m"
    start-history-server.sh
		;;
"stop")
    echo -e "\033[40;36m--------------- stopping SPK ---------------\033[0m"
    stop-history-server.sh
		;;
"restart")
    echo -e "\033[40;36m--------------- restart SPK ---------------\033[0m"
    stop-history-server.sh
    start-history-server.sh
		;;
*)
    echo function:
	  echo "  start | stop | restart spark history"
    echo example:
    echo "  spk start"
    echo "  spk stop"
    echo "  spk restart"
esac