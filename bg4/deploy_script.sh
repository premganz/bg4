#!/bin/bash
rm -rf  master.zip
rm -rf  bg4-master/
wget  https://github.com/premganz/bg4/archive/refs/heads/master.zip
unzip master.zip
cd bg4-master
cd bg4
if grep --quiet Y deploy.flag; then  
	echo 'text found'
	mvn clean install -P fatjar
  java -cp /home/ubuntu/leafycampus/bg4-master/target/bg4-1.0.0.jar:/home/ubuntu/leafycampus/bg4-master/src/main/resources/*:/home/ubuntu/leafycampus/bg4-master/target/lib/* org.spo.ifs.template.Main
else
	echo 'not found'
fi

