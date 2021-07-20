#!/bin/bash

cd $(dirname ${0})
jarFile=word-0.0.1-SNAPSHOT.jar
logFile=${PWD}/run.log
pidFile=${PWD}/pid.log

if [[ ! -f ${jarFile} ]]; then
    echo "错误: 没有找到 ${jarFile}"
    exit 1
fi

# 杀死进程
if [[ -f ${pidFile} ]]; then
    pid=$(head -1 ${pidFile})
    if [[ -n ${pid} ]]; then
        echo "杀死进程: ${pid}"
        kill -9 ${pid} 2>/dev/null 
    fi
fi

# 启动项目
>${logFile}
>${pidFile}
echo "重新启动: ${jarFile}"
JAVA_OPTS="-server -Xms128m -Xmx128m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=218m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Djava.awt.headless=true"
nohup java ${JAVA_OPTS} -jar ${jarFile} >> ${logFile} 2>&1 &
pid=${!}
# 3s后检查pid是否存在
sleep 3s
if [[ -z $(ps -ef | grep ${pid} | grep -v "grep" | awk '{print $2}') ]]; then
    echo "启动失败: ${jarFile}"
else
    echo "${pid}" > ${pidFile}
    echo "启动成功: ${jarFile}"
    echo "pid:      ${pid}"
fi