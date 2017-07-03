#!/bin/bash
read -p "Please enter file path:" dirctory
read -p "Please enter file name:" file_name
path=$dirctory/$file_name
echo $path
#ip=`cat /etc/hosts | awk '{if ($2=="jrnjsyx-ThinkPad-Edge-E440"&&NF==2) print $1}'`
ip=`cat /etc/hosts | awk '{if ($2=="l-test.dev.cn1"&&NF==2) print $1}'`
echo $ip 
scp $path root@$ip:/tmp/

