#!/bin/bash

for((i = 101;i <= 103; i++));
do
        echo -e "\033[40;36m------------------- hadoop$i --------------------\033[0m"
    ssh hadoop$i "source /etc/profile; jps"
done