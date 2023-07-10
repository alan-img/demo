#!/bin/bash

################################################################
#####################  Variable Pre-Definition  ################
################################################################
DATE=$(date "+%Y-%m-%d")

TIME=$(date "+%Y-%m-%d %H:%M:%S")

SCRIPT_PATH=$(dirname $(readlink -f "$0"))

SCRIPT_NAME=$(basename "$BASH_SOURCE" .sh)

LOG_PATH=${SCRIPT_PATH}/logs

LOG_FILE=${LOG_PATH}/${SCRIPT_NAME}_${DATE}.log
################################################################
#####################  Function Pre-Definition  ################
################################################################
function log_info() {
  echo "$(date "+%Y-%m-%d %H:%M:%S") [INFO ] $@" >> ${LOG_FILE}
}

function log_error() {
  echo "$(date "+%Y-%m-%d %H:%M:%S") [ERROR] $@" >> ${LOG_FILE}
}

function log_success() {
  log_info "###################################################"
  log_info "#################     SUCCESS    ##################"
  log_info "###################################################"
}

function log_failure() {
  log_info "###################################################"
  log_info "#################     FAILURE    ##################"
  log_info "###################################################"
}

function prepare() {
  mkdir -p ${LOG_PATH}
  touch ${LOG_FILE}
}
################################################################
###########################  ENV Prepare  ######################
################################################################
prepare

################################################################
########################  Execution Script  ####################
################################################################
count=0
while true
do
    pid=$(ps -ef | grep test.py | grep -v grep | awk '{print $2}')
    if [ "${pid}" == "" ]; then
         log_info "current is ${count} time execution. start execute task..."
         let count++
         nohup python3.8 -u ${SCRIPT_PATH}/test.py 5050000 /root/gab_test/images_set_505w_donnot_operate > ${LOG_FILE} 2>&1 &
    fi
    sleep 30
done

