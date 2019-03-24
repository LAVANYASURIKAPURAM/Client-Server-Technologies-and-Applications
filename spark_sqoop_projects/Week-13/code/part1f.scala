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
erdd.coalesce(1).saveAsTextFile("hdfs://localhost/user/vagrant/output/f_lav_01/")