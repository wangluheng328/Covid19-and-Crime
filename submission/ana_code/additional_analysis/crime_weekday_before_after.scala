var crime = spark.read.options(Map("inferSchema"->"true", "header"->"false")).csv("/user/hm1920/crime_new.csv").toDF("id", "date", "1", "2", "3", "4", "type")

crime = crime.drop("1","2","3","4")

crime = crime.withColumn("date", to_date($"date", "MM/dd/yyyy"))

//2019 and 2020

var crime_2020 = crime.filter(crime("date").gt(lit("2020-01-01")))

var crime_2019 = crime.filter(crime("date").lt(lit("2020-01-01"))).filter(crime("date").gt(lit("2018-12-31")))


//averge group by week

var crime_2019_week = crime_2019.withColumn("weekday", date_format(col("date"), "u"))

var crime_2020_week = crime_2020.withColumn("weekday", date_format(col("date"), "u"))


var crime_week_2019 = crime_2019_week.groupBy("weekday").count().orderBy(desc("weekday"))

var crime_week_2020 = crime_2020_week.groupBy("weekday").count().orderBy(desc("weekday"))


crime_week_2019.na.replace("count", Map(67079 ->  67079*52/53)).orderBy(desc("count")).show()

crime_week_2020.na.replace("count", Map(58551 ->  58551*52/53)).orderBy(desc("count")).show()


//in 2019 before covid Friday is when crime happen most frequently

//in 2020 after covid Friday is when crime happen most frequently

//There is not much difference except for all days in a week, crime happen less after covid