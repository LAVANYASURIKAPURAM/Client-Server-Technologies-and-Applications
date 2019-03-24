import com.databricks.spark.avro._
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
val category= sqlContext.read.avro("hdfs://localhost/user/vagrant/spark_dataset/categories.avro")
category.show()

import com.databricks.spark.avro._
val sqlContext = new org.apache.spark.sql.SQLContext(sc)
val prod= sqlContext.read.avro("hdfs://localhost/user/vagrant/spark_dataset/products.avro")
prod.show()