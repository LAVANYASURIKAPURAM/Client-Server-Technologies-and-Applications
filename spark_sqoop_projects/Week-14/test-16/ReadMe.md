# Test 16 execution instructions 

### According to the above 15 tests 

![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-14/images/test_17.PNG)

### the file size for log files is 33 gb 

### Hence the intermediate compression along with combiner for any file is suitable
### From the above tests it is known that the text file has performed well for 249 splits

Split on cluster for 16th test 
![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-14/images/split_16.PNG)

### the results is also placed 
![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-14/images/16_result.PNG)

# INSTRUCTIONS TO EXECUTE THE CODE 
#### Place the MaximumHttpResposesPerMonth.java  MaximumHttpResposesPerMonthMapper.java and MaximumHttpResposesPerMonthReducer.java in the data folder
#### cd /vagrant_data (change directory)
#### compile using the below commands 
#### hadoop com.sun.tools.javac.Main MaximumHttpResposesPerMonth*.java
#### jar cf mht.jar MaximumHttpResposesPerMonth*.class
#### hadoop jar mht.jar MaximumHttpResposesPerMonth /user/large-log/web-server-logs.txt /output/surikapuram/test16


## THE total map reduce time
![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-14/images/test_16.png)
### It has taken 10 mins 39sec, hence we could say this could be the efficient time