import org.apache.spark.mllib.stat.Statistics

import org.apache.spark.rdd.RDD

var crime = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/hm1920/crime_type.csv")

var covid = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/hm1920/epid_clean.csv")

covid = covid.filter(covid("date").gt(lit("2020-01-23")))

covid = covid.filter(covid("date").lt(lit("2021-01-01")))

val covid_nyc_new_c = covid.select("new_confirmed").map(f=>f.getInt(0)).collect.toList.drop(1).sliding(1, 2).flatten.toList

var covid_nyc_new_c_double = covid_nyc_new_c.map(_.toDouble)



var RB = crime.filter(crime("type") === "ROBBERY")

var FA = crime.filter(crime("type") === "FELONY ASSAULT")

var SC = crime.filter(crime("type") === "SEX CRIMES")

var H2 = crime.filter(crime("type") === "HARRASSMENT 2")



val RB_date = RB.select("count").map(f=>f.getInt(0)).collect.toList

val FA_date = FA.select("count").map(f=>f.getInt(0)).collect.toList

val SC_date = SC.select("count").map(f=>f.getInt(0)).collect.toList

val H2_date = H2.select("count").map(f=>f.getInt(0)).collect.toList



var RB_d = RB_date.map(_.toDouble)

var FA_d = FA_date.map(_.toDouble)

var SC_d = SC_date.map(_.toDouble)

var H2_d = H2_date.map(_.toDouble)




val covid_nyc_new_c_rdd: RDD[Double] = spark.sparkContext.parallelize(covid_nyc_new_c_double.toSeq)

val RB_dd: RDD[Double] = spark.sparkContext.parallelize(RB_d.toSeq)

val FA_dd: RDD[Double] = spark.sparkContext.parallelize(FA_d.toSeq)

val SC_dd: RDD[Double] = spark.sparkContext.parallelize(SC_d.toSeq)

val H2_dd: RDD[Double] = spark.sparkContext.parallelize(H2_d.toSeq)


val RB_correlation: Double = Statistics.corr(covid_nyc_new_c_rdd, RB_dd, "pearson")

val FA_correlation: Double = Statistics.corr(covid_nyc_new_c_rdd, FA_dd, "pearson")

val SC_correlation: Double = Statistics.corr(covid_nyc_new_c_rdd, SC_dd, "pearson")

val H2_correlation: Double = Statistics.corr(covid_nyc_new_c_rdd, H2_dd, "pearson")


println(s"Robbery Correlation is: $RB_correlation")

println(s"FELONY ASSAULT Correlation is: $FA_correlation")

println(s"SEX CRIMES Correlation is: $SC_correlation")

println(s"HARRASSMENT 2 Correlation is: $H2_correlation")


//Correlation is: -0.37035180358283887
//Correlation is: -0.46913761145316174
//Correlation is: -0.45638676093112335
//Correlation is: -0.6489481870609697
