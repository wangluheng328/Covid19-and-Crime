var crime_date = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/crime_date.csv")

var covid = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/epid_clean.csv")

covid = covid.filter(covid("location")==="US_NY_NYC")

covid = covid.filter(covid("date").gt(lit("2020-01-23")))

covid = covid.filter(covid("date").lt(lit("2021-01-01")))

var covid_month = covid.groupBy(month(col("date")).alias("month")).agg(mean(col("new_deceased")).alias("mean_new_deceased"))

var crime_month = crime_date.groupBy(month(col("date")).alias("month")).agg(mean(col("count")).alias("mean_crime_count"))

val df = covid_month.join(crime_month, Seq("month"))
df.describe().show()
