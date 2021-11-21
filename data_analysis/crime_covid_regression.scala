var crime_date = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/crime_date.csv")
var covid = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/epid_clean.csv")
covid = covid.filter(covid("location")==="US_NY_NYC")
covid = covid.filter(covid("date").gt(lit("2020-01-23")))
covid = covid.filter(covid("date").lt(lit("2021-01-01")))
val df = covid.join(crime_date, Seq("date"))
import org.apache.spark.ml.feature.{VectorAssembler, StringIndexer, OneHotEncoderEstimator}
import org.apache.spark.ml.regression.LinearRegression
var assembler = new VectorAssembler().setInputCols(Array("new_confirmed","new_deceased","new_recovered")).setOutputCol("features")
var df_transformed = assembler.transform(df)
var lr = new LinearRegression()
df_transformed = df_transformed.withColumnRenamed("count", "label")
var lrModel = lr.fit(df_transformed)
val trainingSummary = lrModel.summary
println(s"numIterations: ${trainingSummary.totalIterations}")
println(s"objectiveHistory: [${trainingSummary.objectiveHistory.mkString(",")}]")
println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
println(s"r2: ${trainingSummary.r2}")
println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")