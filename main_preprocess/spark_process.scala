var crime = spark.read.options(Map("inferSchema"->"true", "header"->"false")).csv("/user/hm1920/crime_new.csv").toDF("id", "date", "1", "2", "3", "4", "type")

crime = crime.drop("1","2","3","4")

crime = crime.withColumn("date", to_date($"date", "MM/dd/yyyy"))

crime = crime.filter(crime("date").gt(lit("2020-01-22")))

var crime_date = crime.groupBy("date").count().orderBy("date")

var crime_type = crime.groupBy("date", "type").count().orderBy("date")

crime_date.show()

crime_type.show()

crime_date.coalesce(1).write.option("header","true").csv("crime_date.csv")

crime_type.coalesce(1).write.option("header","true").csv("crime_type.csv")

var epid = spark.read.options(Map("inferSchema"->"true", "header"->"false")).csv("/user/hm1920/epid_new.csv").toDF("date", "location", "new_confirmed", "new_deceased", "new_recovered", "1", "total_confirmed","total_deceased")

epid = epid.drop("1")

epid = epid.withColumn("date", to_date($"date", "yyyy-MM-dd"))

epid.coalesce(1).write.option("header","true").csv("epid_clean.csv")