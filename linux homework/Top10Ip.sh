#!/bin/bash
cat /opt/apache-tomcat-8.0.43/logs/localhost_access_log.2017-05-10.txt | awk  '{print $1}' | sort | uniq -c | sort -n -r | head -10
