#!/bin/bash
# SCOOP IMPORT SCRIPT - STEP-4.SH
# Author: LAVANYA SURIKAPURAM

#Importing Records 1000-3000 from the widgets 5000 record table


sqoop import --connect jdbc:mysql://localhost/hadoopguide --username root --password itmd521 --table widgets --where "id>999 AND id<3001" -m 1 --target-dir /vagrant_data/STEP4