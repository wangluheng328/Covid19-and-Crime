var crime = spark.read.options(Map("inferSchema"->"true", "header"->"false")).csv("/user/hm1920/crime_new.csv").toDF("id", "date", "1", "2", "3", "4", "type")

crime = crime.drop("1","2","3","4")

crime = crime.withColumn("date", to_date($"date", "MM/dd/yyyy"))

//2019 and 2020

var crime_2020 = crime.filter(crime("date").gt(lit("2020-01-01")))

var crime_2019 = crime.filter(crime("date").lt(lit("2020-01-01"))).filter(crime("date").gt(lit("2018-12-31")))

//max month and different type

var crime_2019_month = crime_2019.withColumn("month", date_format(col("date"), "M"))

var crime_2020_month = crime_2020.withColumn("month", date_format(col("date"), "M"))

var month_type_2020 = crime_2020_month.groupBy("month", "type").count().orderBy(desc("count"))

var month_type_2019 =crime_2019_month.groupBy("month", "type").count().orderBy(desc("count"))

month_type_2020.coalesce(1).write.option("header","true").csv("month_type_2020.csv")

month_type_2019.coalesce(1).write.option("header","true").csv("month_type_2019.csv")

//group by month 

crime_2020_month.groupBy("month").count().orderBy(desc("count")).show()

crime_2019_month.groupBy("month").count().orderBy(desc("count")).show()

//in 2019 before covid July is when crime happen most frequently

//in 2020 after covid August is when crime happen most frequently