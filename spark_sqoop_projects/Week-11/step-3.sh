#!/bin/bash
# SCOOP IMPORT SCRIPT - STEP-3.SH
# Author: LAVANYA SURIKAPURAM

sqoop import --connect jdbc:mysql://localhost/hadoopguide --table widgets -m 1 --username root --password itmd521