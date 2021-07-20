#!/bin/bash

cd $(dirname ${0})
pidFile=${PWD}/pid.log
# 杀死进程
if [[ -f ${pidFile} ]]; then
    pid=$(head -1 ${pidFile})
    if [[ -n ${pid} ]]; then
        echo "杀死进程: ${pid}"
        kill -9 ${pid}
    fi
fi
>${pidFile}