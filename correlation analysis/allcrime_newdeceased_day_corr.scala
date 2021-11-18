import org.apache.spark.mllib.stat.Statistics

import org.apache.spark.rdd.RDD

var crime_date = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/crime_date.csv")

var covid = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/epid_clean.csv")

covid = covid.filter(covid("date").gt(lit("2020-01-23")))

covid = covid.filter(covid("date").lt(lit("2021-01-01")))

val covid_nyc_new_c = covid.select("new_deceased").map(f=>f.getInt(0)).collect.toList.drop(1).sliding(1, 2).flatten.toList

val covid_ny_new_c = covid.select("new_deceased").map(f=>f.getInt(0)).collect.toList.drop(0).sliding(1, 2).flatten.toList

val crime_date_nyc = crime_date.select("count").map(f=>f.getInt(0)).collect.toList

var crime_date_nyc_double = crime_date_nyc.map(_.toDouble)

var covid_nyc_new_c_double = covid_nyc_new_c.map(_.toDouble)

val covid_nyc_new_c_rdd: RDD[Double] = spark.sparkContext.parallelize(covid_nyc_new_c_double.toSeq)

val crime_date_nyc_rdd: RDD[Double] = spark.sparkContext.parallelize(crime_date_nyc_double.toSeq)

val correlation: Double = Statistics.corr(covid_nyc_new_c_rdd, crime_date_nyc_rdd, "pearson")

println(s"Correlation is: $correlation")



// correlation -0.6756082703735797




