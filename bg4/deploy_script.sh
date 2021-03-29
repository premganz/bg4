#!/bin/bash
rm -rf date.txt
datenow=`date +%Y`
echo $secret$datenow > date.txt
hash=`sha256sum date.txt`
echo $hash
curl https://github.com/premganz/bg4/blog/master/bg4/deploy.flag | grep $hash
if [ $? == 0 ]  then
        read -n1 -r -p "Press any key to continue.." key
        echo 'found flag'
        cd /home/ubuntu/leafycampus
        rm -rf master.zip
        rm -rf bg4-master
        wget  https://github.com/premganz/bg4/archive/refs/heads/master.zip
        unzip master.zip
        cd bg4-master/bg4
        mvn clean install -P fatjar
        java -cp /home/ubuntu/leafycampus/bg4-master/target/bg4-1.0.0.jar:/home/ubuntu/leafycampus/bg4-master/src/main/resources/*:/home/ubuntu/leafycampus/bg4-master/target/lib/* org.spo.ifs.template.Main
else
        echo exiting
fi
