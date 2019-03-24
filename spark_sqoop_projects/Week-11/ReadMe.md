# 1 Created a create.sql that will create the database hadoopguide, table widgets

## basic step : configure config.vm.synced_folder "./data", "/vagrant_data" in the vagrantfile
## 1.1 palced the create.sql in data folder
## 1.2 run the create.sql by issuing the following commands
##     Entered into the MySql shell
##      mysql -u root -p
##      type password: itmd521
## 1.3 Command to run the create.sql file
## source /vagrant_data/create.sql

# 2 Python script named step-2.py that will generate 5000 records (based on the 3 sample items given in the text) and INSERT this into the widgets table. 

## 2.1 Based on the 3 sample items given in the text: we need to insert the 3 sample records into the widgets table intitially
##     run the insert_3records.sql script
##     Entered into the MySql shell
##     mysql -u root -p
##     type password: itmd521
##     Command to run the insert_3records.sql file
## 	   source /vagrant_data/insert_3records.sql
## 2.2 Perform necessary sqoop setting for the sqoop import to work 
## 2.3 see that all nodes are up(hdfs namenode -format, start-dfs.sh, start-yarn.sh, mr-jobhistory-daemon.sh  start historyserver)
## 2.4 Run the Sqoop import command for the 3 records
##     sqoop import --connect jdbc:mysql://localhost/hadoopguide --table widgets -m 1 --username root --password itmd521
## 2.5 To copy the output file  to local machine give a commad as below
##      go to /vagrant_data folder from home --> issue the following commands from home directory
##      cd /vagrant_data
##      hdfs dfs -get /user/vagrant/widgets/part-m-00000
## 2.6 clear the widgets table to insert 5000 records, run the following
##     run the create.sql by issuing the following commands
##     Entered into the MySql shell
##      mysql -u root -p
##      type password: itmd521
##      Command to run the create.sql file
## 		source /vagrant_data/create.sql
## 2.7  placed the step-2.py file in data folder
## 2.8  Make sure you are in  /vagrant_data directory
## 2.9  Python is typically installed as part of the default installation.
## 2.10  Run the command python -V to check for the version (Python 2.7.12)
## 2.11  For Connector/Python 2.1.7 do the following
##       wget http://cdn.mysql.com/Downloads/Connector-Python/mysql-connector-python-2.1.7.zip#md5=3df394d89300db95163f17c843ef49df
##       unzip mysql-connector-python-2.1.7.zip
##       cd mysql-connector-python-2.1.7
##       sudo python2.7 setup.py install
## 2.12  Run the python script step-2.py 
##       python step-2.py
## 2.13  Check if the 5000 data is inseted in the sql 
##      mysql -u root -p
##      use hadoopguide;
##       select * from widgets;
			
# 3 Included a shell script named: step-3.sh that will execute the Sqoop import as given by the text book 
## 3.1 clear the /user/vagrant/widgets/part-m-00000 as we had generted sqoop import for 3 records in the previous step-2(issue theh below command)
## hadoop fs -rmr /user/vagrant/widgets
## 3.1 Placed the step-3.sh in data folder 
## 3.2 change the directory cd /vagrant_data(or make suer you are in /vagrant_data directory)
## 3.3 Run the step-3.sh file as issuing the below commands
## chmod +x step-3.sh
## bash step-3.sh
## 3.4 The Map task was execute 
## 3.5 The Output was verified using the commad:
## hadoop fs -cat widgets/part-m-00000

# 4 Included a shell script named step-4.sh that only import record 1000-3000 for an sqoop import 
## 4.1 Placed the step-4.sh in data folder 
## 4.2 changed the directory cd /vagrant_data
## 4.3 Ran the step-4.sh file 
## chmod +x step-4.sh
## bash step-4.sh
## 4.4 The Map task was execute 
## 4.5 The Output was verified using the commad:
## hadoop fs -cat /vagrant_data/STEP4/part-m-00000 

# 5 “Working with Imported data”  - Ran the MaxWidgetID.java 
## 5.1 clone hadoop book 
## git clone https://github.com/tomwhite/hadoop-book.git
## 5.2 Configure sqoop as required
## 5.3 go to the following path 
## cd hadoop-book 
## cd ch15-sqoop/
## cd src 
## cd main
## cd java
## 5.4 Run the following command in order to generate the jar file and execute the MR
## hadoop com.sun.tools.javac.Main MaxWidgetId.java Widget.java
## jar cf sqoop-examples.jar *.class
## hadoop jar sqoop-examples.jar MaxWidgetId -libjars $SQOOP_HOME/sqoop-1.4.6.jar
## 5.5  check the output in the following command
## hadoop fs -cat /user/vagrant/maxwidget/part-r-00000
## Result screenshot
<table class="image">
<caption align="bottom">command: /user/vagrant/maxwidget/part-r-00000</caption>
<tr><td><img src=images/Part_5_Result.PNG alt=command: /user/vagrant/maxwidget/part-r-00000></td></tr>
</table>

# 6 Modify the MaxWidgetID.java file to find The average price for each widget 
## 6.1 modified the MaxWidgetID.java to AvgWidgetPrice.java 
## 6.2 Placed AVGWidgetPriceGenericAvro.java Widget.java in the /vagrant_data folder along with AvgWidgetPrice.java
## 6.3 Verified if all the nodes are up
## 6.4 Run the scoop import command 
## sqoop import --connect jdbc:mysql://localhost/hadoopguide --table widgets -m 1 --username root --password itmd521 (if not ran before in step 3)
## 6.5 Run the following command in order to generate the jar file and execute the MR(make sure your in /vagrant_data directory else give cd ~, cd /vagrant_data)
## hadoop com.sun.tools.javac.Main AVGWidgetPrice.java Widget.java
## jar cf sqoop-examples.jar *.class
## hadoop jar sqoop-examples.jar AVGWidgetPrice -libjars $SQOOP_HOME/sqoop-1.4.6.jar
## 6.7  check the output in the following command
## hadoop fs -cat /user/vagrant/avgwidgetprice/part-r-00000
## 6.8 issue the command to copy the output file to local directory 
## hdfs dfs -get /user/vagrant/avgwidgetprice/part-r-00000
## part-r-00000 is the output file for part 6 
## Result screenshot
<table class="image">
<caption align="bottom">command: /user/vagrant/avgwidgetprice/part-r-00000</caption>
<tr><td><img src=images/Part_6_Result.PNG alt=command: /user/vagrant/avgwidgetprice/part-r-00000></td></tr>
</table>
##  output file
Link for output file part-r-00000 [part-r-00000](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-11/part-r-00000).