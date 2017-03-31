#!/bin/bash
# cloudcenter init files
. /usr/local/osmosix/etc/.osmosix.sh
. /usr/local/osmosix/etc/userenv
. /usr/local/osmosix/service/utils/cfgutil.sh

export INSTALL_ROOT_FOLDER=~
export JAR_NAME="chat-server-websocket-0.1-jar-with-dependencies.jar"
export JAR_URL="https://github.com/cloudlabx/chat-server-websocket/raw/master/target"
export HOST_IP=$CliqrTier_chatserver_1_PUBLIC_IP
export HOST_PORT="8025"

#tools 
echo "Install Java Runtime" >> /var/log/install-chat-server.log
sudo yum -y install jre
echo "Install WGET" >> /var/log/install-chat-server.log
sudo yum -y install wget

#installation
cd $INSTALL_ROOT_FOLDER
echo "download jar file" >> /var/log/install-chat-server.log
sudo curl -SLO $JAR_URL"/"$JAR_NAME >> /var/log/install-chat-server.log
echo "start server" >> /var/log/install-chat-server.log
sudo nohup java -jar $JAR_NAME $HOST_IP $HOST_PORT &

#custom java installation
# export ORACLE_URL="http://download.oracle.com/otn-pub/java/jdk/8u60-b27"
# export JAR_NAME="jre-8u60-linux-x64.rpm"
# sudo wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "$ORACLE_URL/$JAR_NAME"
# sudo yum -y localinstall jre-8u60-linux-x64.rpm
# sudo rm -rf $INSTALL_ROOT_FOLDER/jre-8u60-linux-x64.rpm

