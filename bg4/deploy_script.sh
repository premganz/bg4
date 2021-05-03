#!/bin/bash
rm -rf date.txt
datenow=`date +%Y%m%d%H`
#echo $secret$datenow
hash=`echo -n $secret$datenow | sha256sum | cut -c1-20
echo $secret$datenow`
echo $hash
curl https://github.com/premganz/bg4/blob/master/bg4/deploy.flag | grep $hash 
if [ $? == 0 ]	
then
	#read -n1 -r -p "Press any key to continue.." key	
	killall java
	echo 'found flag'
	cd /home/ubuntu/leafycampus
	rm -rf master.zip
	rm -rf bg4-master
	wget  https://github.com/premganz/bg4/archive/refs/heads/master.zip
	unzip master.zip
	cd bg4-master/bg4
	mvn clean install -P fatjar
	zip -r ~/data-backup/$datenow.zip  ~/data-cms/cms1
	#cp /home/ubuntu/leafycampus/bg4-master/bg4/src/main/resources/Schema_resource.xml ~/data-cms/cms1/content/meta/meta-schema/Schema-cms.xml
	java -cp /home/ubuntu/leafycampus/bg4-master/bg4/target/bg4-b1.6.2.jar:/home/ubuntu/leafycampus/bg4-master/bg4/src/main/resources/*:/home/ubuntu/leafycampus/bg4-master/bg4/target/lib/* org.spo.svc3.trx.pgs.utils.CmsUtils
	java -cp /home/ubuntu/leafycampus/bg4-master/bg4/target/bg4-b1.6.2.jar:/home/ubuntu/leafycampus/bg4-master/bg4/src/main/resources/*:/home/ubuntu/leafycampus/bg4-master/bg4/target/lib/* org.spo.ifs.template.Main
fi
echo done
