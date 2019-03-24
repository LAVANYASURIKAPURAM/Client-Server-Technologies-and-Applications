import com.databricks.spark.avro._
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
val category= sqlContext.read.avro("hdfs://localhost/user/vagrant/spark_dataset/categories.avro")
category.show()

import com.databricks.spark.avro._
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
val prod= sqlContext.read.avro("hdfs://localhost/user/vagrant/spark_dataset/products.avro")
prod.show()


val newNames = Seq("prod_id", "cat_id", "prod_name", "price", "link")
val dfRenamed = prod.toDF(newNames: _*)
val df1 = dfRenamed.selectExpr("prod_id", 
                        "cat_id", 
                        "prod_name", 
                        "cast(price as float) price",
                        "link")
val rdd_rows: org.apache.spark.rdd.RDD[org.apache.spark.sql.Row] = df1.rdd
val newRDD = rdd_rows.filter(t => (t(3) != null))
val sourceRdd = newRDD.map(_.mkString(","))
val m = sourceRdd.map(_.split(",")).filter(_(3).toFloat < 100)
m.foreach(array => println(array.mkString(" ")))
val ms = m.map(_.mkString(","))
ms.collect().foreach(println)
ms.saveAsTextFile("hdfs://localhost/user/vagrant/output/1e_lav04/")

val rddData = ms.flatMap(_.split("\n")).map(line => line.split(",")).map(array => (array(0), array(1), array(2),array(3),array(4)))
val rdd_dfs = rddData.toDF("prod_id", 
                        "cat_id", 
                        "prod_name", 
                        "price",
                        "link")
rdd_dfs.show()

val resultDfs = rdd_dfs.join(category, rdd_dfs("cat_id") === category("category_id"))




import org.apache.spark.sql.functions.{row_number, max, broadcast}
import org.apache.spark.sql.expressions.Window
val w = Window.partitionBy($"cat_id").orderBy($"price".desc)
val dfTops = resultDfs.withColumn("rn", row_number.over(w)).where($"rn" < 11).drop("rn")
dfTops.collect.foreach(println)


val colsToRemove = Seq("prod_id", "cat_id", "category_id", "link") 
import org.apache.spark.sql.Column
val filteredDFTop10 = dfTops.select(dfTops.columns .filter(colName => !colsToRemove.contains(colName)) .map(colName => new Column(colName)): _*)

val columns: Array[String] = filteredDFTop10.columns
val reorderedColumnNames: Array[String] =  Array("category_name", "prod_name", "price")
val resultDFTop10= filteredDFTop10.select(reorderedColumnNames.head, reorderedColumnNames.tail: _*)

val rdd_rows_f: org.apache.spark.rdd.RDD[org.apache.spark.sql.Row] = resultDFTop10.rdd

val sourceRddf = rdd_rows_f.map(_.mkString(","))

val erdd = sourceRddf.map(x => (x.split(',')(0).toString+"\t"+x.split(',')(1).toString+"\t"+x.split(',')(2).toString))

erdd.collect().foreach(println)
erdd.coalesce(1).saveAsTextFile("hdfs://localhost/user/vagrant/output/f_lav_05/")


val resultpartDfs = df1.join(category, df1("cat_id") === category("category_id"))


val resultDf02 = resultpartDfs.selectExpr("prod_id", 
                        "cat_id", 
                        "prod_name", 
                        "cast(price as float) price",
                        "link",
                        "category_id",
                        "category_name" )


import org.apache.spark.sql.functions.{row_number, max, broadcast}
import org.apache.spark.sql.expressions.Window

val wdn = Window.partitionBy($"cat_id").orderBy($"price".desc)
val dfcatmaxn = resultDf02.withColumn("rn", row_number.over(wdn)).where($"rn" === 1).drop("rn").drop("prod_id", "link", "category_id")

val wascn = Window.partitionBy($"cat_id").orderBy($"price".asc)
val dfcatminn = resultDf02.withColumn("rn", row_number.over(wascn)).where($"rn" === 1).drop("rn").drop("prod_id", "link", "category_id", "category_name")

val max_price = Seq("cat_id", "highest_product_name", "Highest_product_price", "category_name")
val max_priceupn = dfcatmaxn.toDF(max_price: _*)

val min_price = Seq("cat_id_dup", "lowest_product_name", "Lowest_product_price")
val min_priceupn = dfcatminn.toDF(min_price: _*)

val resultDfs_lastn = max_priceupn.join(min_priceupn, max_priceupn("cat_id") === min_priceupn("cat_id_dup"))


val colsToRemove_g = Seq("cat_id", "cat_id_dup") 
import org.apache.spark.sql.Column
val filteredDFminmax = resultDfs_lastn.select(resultDfs_lastn.columns .filter(colName => !colsToRemove_g.contains(colName)) .map(colName => new Column(colName)): _*)

val columns: Array[String] = filteredDFminmax.columns
val reorderedColumnNames_minmax: Array[String] =  Array("category_name", "highest_product_name", "Highest_product_price", "lowest_product_name", "Lowest_product_price")

val resultDFminmax= filteredDFminmax.select(reorderedColumnNames_minmax.head, reorderedColumnNames_minmax.tail: _*)

resultDFminmax.coalesce(1).write.option("delimiter","|").avro("hdfs://localhost/user/vagrant/output/g_lav05/")

