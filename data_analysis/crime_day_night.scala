var crime_date = spark.read.options(Map("inferSchema"->"true", "header"->"true")).csv("/user/lw2534/stage2/crime_new.csv")

