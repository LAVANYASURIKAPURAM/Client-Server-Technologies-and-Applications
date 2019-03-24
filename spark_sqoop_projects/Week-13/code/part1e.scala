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
ms.saveAsTextFile("hdfs://localhost/user/vagrant/output/1e_lav/")