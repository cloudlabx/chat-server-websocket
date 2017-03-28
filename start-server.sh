#!/bin/bash

# Source the Cloudcenter user env file to onboard C3 specifc vars
source /usr/local/cliqr/etc/userenv

export INSTALL_ROOT_FOLDER=/tmp
export JAR_NAME="chat-server-websocket-0.1-jar-with-dependencies.jar"
export JAR_URL="https://github.com/cloudlabx/chat-server-websocket/raw/master/target"
export HOST_IP=$1
export HOST_PORT="8025"

#tools 
sudo yum -y install jre
sudo yum -y install wget

cd $INSTALL_ROOT_FOLDER
sudo curl -SLO $JAR_URL"/"$JAR_NAME
sudo nohup java -jar $JAR_NAME $HOST_IP $HOST_PORT &

#custom java installation
# export ORACLE_URL="http://download.oracle.com/otn-pub/java/jdk/8u60-b27"
# export JAR_NAME="jre-8u60-linux-x64.rpm"
# sudo wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "$ORACLE_URL/$JAR_NAME"
# sudo yum -y localinstall jre-8u60-linux-x64.rpm
# sudo rm -rf $INSTALL_ROOT_FOLDER/jre-8u60-linux-x64.rpm

