#!/bin/bash

tagname=${1:-'master'}
rm -rf LABEL
git clone -b $tagname https://github.com/semanticbits/label-priv.git LABEL
cd LABEL/code/LABEL
grails -Dgrails.env=qa war LABEL.war
sudo service tomcat7 stop
sudo rm -rf /var/lib/tomcat7/webapps/LABEL*
sudo cp -f LABEL.war /var/lib/tomcat7/webapps
sudo service tomcat7 start

