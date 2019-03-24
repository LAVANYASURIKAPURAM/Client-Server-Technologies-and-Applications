# 1C Downloaded and Installed Spark in the Hadoop Cluster. 

# General code execution command
# cp 1all_spark.scala ~
# run the code with the following command
# spark-shell --packages com.databricks:spark-avro_2.11:4.0.0 -i 1all_spark.scala 
# the above will execute the entier d to g
# else ecah can be executed at a time
# 1D Inserted the dataset to my local Hadoop File system and then loaded the data into SparkShell and display the first 20 rows of each dataset. Place this result named “Result.png” in Results” folder. 

## 1.1 configured config.vm.synced_folder "./data", "/vagrant_data" in the vagrantfile
## 1.2 Placed the categories.avro and products.avro in the data folder.
## 1.3 loaded the  categories.avro and products.avro by using the following commands.
## hadoop fs -mkdir -p spark_dataset/
## Changed the directory cd /vagrant_data/
## hdfs dfs -put -f categories.avro spark_dataset/
## hdfs dfs -put -f products.avro spark_dataset/
## 1.5 Loaded the data into SparkShell and display the first 20 rows of each dataset by issuing the following commands
## Entered the spard shell using the below command 
## $SPARK_HOME/bin/spark-shell --packages com.databricks:spark-avro_2.11:4.0.0
## import com.databricks.spark.avro._
## val sqlContext = new org.apache.spark.sql.SQLContext(sc)
## val category= sqlContext.read.avro("hdfs://localhost/user/vagrant//spark_dataset/categories.avro")
## category.show()
## val prod= sqlContext.read.avro("hdfs://localhost/user/vagrant//spark_dataset/products.avro")
## prod.show()
## Placed the result in the Results folder  
<table class="image">
<caption align="bottom"> Data loading in spark</caption>
<tr><td><img src=https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/Result.png alt= Data loading in spark></td></tr>
</table>
![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/Result.png)
##  output file
## The code is placed in code folder as part1d.sh

# 1E  Filtered the dataset using RDD, which has products price less than 100 USD 

## code
## val newNames = Seq("prod_id", "cat_id", "prod_name", "price", "link") 
## val dfRenamed = prod.toDF(newNames: _*)
## val df1 = dfRenamed.selectExpr("prod_id", "cat_id", "prod_name","cast(price as float) price", "link")
## val rdd_rows: org.apache.spark.rdd.RDD[org.apache.spark.sql.Row] = df1.rdd
## val newRDD = rdd_rows.filter(t => (t(3) != null))
## val sourceRdd = newRDD.map(_.mkString(","))
## val m = sourceRdd.map(_.split(",")).filter(_(3).toFloat < 100)
## m.foreach(array => println(array.mkString(" ")))
## val ms = m.map(_.mkString(","))
## ms.collect().foreach(println)
## ms.saveAsTextFile("hdfs://localhost/user/vagrant/output/2/")
<table class="image">
<caption align="bottom">e count</caption>
<tr><td><img src=https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/1ecount.PNG alt= Data loading in spark></td></tr>
</table>
<table class="image">
<caption align="bottom">e output</caption>
<tr><td><img src=https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/e_output.PNG alt= Data loading in spark></td></tr>
</table>
![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/Result_0.txt)
##  output file
## The code is placed in code folder as part1e.sh

# 1F  Found the top 10 products prices in each category
## val rddData = ms.flatMap(_.split("\n")).map(line => line.split(",")).map(array => (array(0), array(1), array(2),array(3),array(4)))
## val rdd_dfs = rddData.toDF("prod_id","cat_id","prod_name","price","link")
## rdd_dfs.show()
## Joining two dataframe:
## val resultDfs = rdd_dfs.join(category, rdd_dfs("cat_id") === category("category_id"))
## top 10 products prices in each category
## import org.apache.spark.sql.functions.{row_number, max, broadcast}
## import org.apache.spark.sql.expressions.Window
## val w = Window.partitionBy($"cat_id").orderBy($"price".desc)
## val dfTops = resultDfs.withColumn("rn", row_number.over(w)).where($"rn" < 11).drop("rn")
## dfTops.collect.foreach(println)
<table class="image">
<caption align="bottom">f output</caption>
<tr><td><img src=https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/f_output.PNG alt= Data loading in spark></td></tr>
</table>
![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/Result_1.txt)
##  output file
## The code is placed in code folder as part1f.sh

# 1G Find the highest and lowest product price in each category
## The code is placed in code folder as part1g.sh



<table class="image">
<caption align="bottom">g output</caption>
<tr><td><img src=https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/1g_output.PNG alt= Data loading in spark></td></tr>
</table>
![](https://github.com/illinoistech-itm/lsurikapuram/blob/master/ITMD-521/Week-13/Results/Result_2.avro)
##  output file
