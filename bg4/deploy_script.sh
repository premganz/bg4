#!/bin/bash
#rm -rf  master.zip.2
#rm -rf  bg2-master/
wget  https://github.com/premganz/bg2/archive/master.zip
unzip master.zip
cd bg2-master/

if grep --quiet Y deploy.flag; then  
	echo 'text found'
	mvn clean install -P fatjar
  java -cp /home/ec2-user/jetty/bg2-master/target/bg2-1.0.0.jar:/home/ec2-user/jetty/bg2-master/src/main/resources/*:/home/ec2-user/jetty/bg2-master/target/lib/* org.spo.ifs.template.Main
else
	echo 'not found'
fi

