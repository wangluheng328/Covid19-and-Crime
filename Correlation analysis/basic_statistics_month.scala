var crime_date = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/crime_date.csv")

var covid = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/epid_clean.csv")

covid = covid.filter(covid("location")==="US_NY_NYC")

var covid_month = covid.groupBy(month(col("date")).alias("month")).agg(mean(col("new_confirmed")).alias("mean_new_confirmed"))

var crime_month = crime_date.groupBy(month(col("date")).alias("month")).agg(mean(col("count")).alias("mean_crime_count"))

val df = covid_month.join(crime_month, Seq("month"))
df.describe().show()
