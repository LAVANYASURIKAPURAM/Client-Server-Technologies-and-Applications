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

resultDFminmax.coalesce(1).write.option("delimiter","|").avro("hdfs://localhost/user/vagrant/output/g_lav/")



