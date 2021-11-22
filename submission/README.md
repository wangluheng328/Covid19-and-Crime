# Submission readme


## How to run my code:
**Note: descriptions of each code file are down below**\
**All imput data (imported csv) can be found on 1.project_data folder in hm1920's hdfs or 2. stage2 folder in lw2534's hdfs**\
**All output csv can also be found on 1.project_data folder in hm1920's hdfs or 2. stage2 folder in lw2534's hdfs**\
**If you'd like to run our code, there's no need to manually upload the data, since you get just use our directories**
1. follow the instruction in data_ingest/data_ingest.txt\
   download the data sets and put them to hdfs\
   result can be found in project_data/crime.csv and project_data/epid.csv\
   due to storage issue data folder is not included in the assignment submission\
   they can be found on the hdfs
2. run the mapreduce job Clean.java in etl_code/wes following the normal procedure of running mapreduce jobs on nyu cluster\
   the input is: crime.csv\
   the output is cleaned data: crime_new.csv
3. run the mapreduce job Clean.java in etl_code/kyle following the normal procedure of running mapreduce jobs on nyu cluster\
   the input is: epid.csv\
   the output is cleaned data: epid_new.csv
4. run the mapreduce job CountRecs.java in profiling_code/wes following the normal procedure of running mapreduce jobs on nyu cluster\
   the input is: crime_new.csv\
   the output is row count of the input data\
   this gives a better idea of the output csv files from Clean.java
5. run the mapreduce job CountRecs.java in profiling_code/kyle following the normal procedure of running mapreduce jobs on nyu cluster\
   the input is: epid_new.csv\
   the output is row count of the input data\
   this gives a better idea of the output csv files from Clean.java
6. Additional_Process\
   put the additional_process.scala from ana_code/additional_process folder to hdfs\
   make sure that both epid_new.csv and crime_new.csv are also put on the cluster\
   run by open spark and type command :load additional_process.scala\
   a few desmonstration will show on the command prompt and three output csv will be created
7. Basic statistics
    - **all data files needed are already in HDFS of lw2534**
    - allcrime_day_stats.scala
      - put allcrime_day_stats.scala from ana_code/basic_statistics folder to hdfs
      - run by open spark and type command :load allcrime_day_stats.scala 
      - the statistics for crime and covid will be printed out (see `bs_allcrime_day_stats.png`)
    - allcrime_newdeceased_month_stats.scala
      - similar to above, just use :load allcrime_newdeceased_month_stats.scala
      - the stats for mean crime and mean new_deceased by month are printed out (see `bs_allcrime_new_deceased_month.png`)
    - allcrime_newconfirmed_month_stats.scala
      - similar to above, just use :load allcrime_newconfirmed_month_stats.scala
      - the stats for mean crime and mean new_confirmed by month are printed out (see `bs_allcrime_new_confirmed_month.png`)
8. Additional Analysis
    - crime_month_before_after.scala
      - put crime_month_before_after.scala from ana_code/additional_analysis folder to hdfs
      - make sure that crime_new.csv is also on hdfs
      - run by open spark and type command :load crime_month_before_after.scala 
      - the crime count from 2019 and 2020 grouped by month will be printed on the screen
      - the crime count from 2019 and 2020 grouped by month and type will be output as csv
      - month_type_2019.csv and month_type_2020.csv
    - crime_weekday_before_after.scala
      - put crime_weekday_before_after.scala from ana_code/additional_analysis folder to hdfs
      - makesure that crime_new.csv is also on hdfs
      - run by open spark and type command :load crime_weekday_before_after.scala 
      - the crime count from 2019 and 2020 grouped by weekday will be printed on the screen
    - covid_month_day_series.scala.scala
      - put covid_month_day_series.scala from ana_code/additional_analysis folder to hdfs
      - run by open spark and type command :load covid_month_day_series.scala 
      - note that there is not outputting printout for this one, instead if will creates 10 directories in the /stage2 folder
      - please refer to screenshots named `aa_timeseries_1.png` and `aa_timeseries_2.png`
    - crime_day_night.scala
      - put crime_day_night.scala from ana_code/additional_analysis folder to hdfs
      - run by open spark and type command :load crime_day_night.scala 
      - there will be two dataframes being printed
      - please refer to screenshots named `aa_daynight_before_after.png`
    - crime_covid_regression.scala
      - put crime_covid_regression.scala from ana_code/additional_analysis folder to hdfs
      - run by open spark and type command :load crime_covid_regression.scala 
      - there will be several outputs of the regression model being printed
      - please refer to screenshots named `aa_regression.png`
      

9. Correlation Analysis
    - typecrime_newconfirmed_day_corr.scala
      - put typecrime_newconfirmed_day_corr.scala from ana_code/correlation_analysis folder to hdfs
      - make sure that crime_type.csv and epid_clean.csv are also on hdfs
      - run by open spark and type command :load typecrime_newconfirmed_day_corr.scala
      - the correlation between four types of crime counts and daily counts of newly confirmed covid cases will be print on the screen
    - allcrime_newconfirmed_day_corr.scala
      - put allcrime_newconfirmed_day_corr.scala from ana_code/correlation_analysis folder to hdfs
      - run by open spark and type command :load allcrime_newconfirmed_day_corr.scala
      - a correlation coefficient of -0.6756 will be printed (please refer to screenshot named `ca_newconfirmed_day.png`)
    - allcrime_newdeceased_day_corr.scala
      - put allcrime_newdeceased_day_corr.scala from ana_code/correlation_analysis folder to hdfs
      - run by open spark and type command :load allcrime_newdeceased_day_corr.scala
      - a correlation coefficient of -0.6670 will be printed (please refer to screenshot named `ca_newdeceased_day.png`)
    - allcrime_newconfirmed_month_corr.scala
      - put allcrime_newconfirmed_month_corr.scala from ana_code/correlation_analysis folder to hdfs
      - run by open spark and type command :load allcrime_newconfirmed_month_corr.scala
      - a correlation coefficient of -0.8915 will be printed (please refer to screenshot named `ca_newconfirmed_month.png`)
    - allcrime_newdeceased_month_corr.scala
      - put allcrime_newdeceased_month_corr.scala from ana_code/correlation_analysis folder to hdfs
      - run by open spark and type command :load allcrime_newdeceased_month_corr.scala
      - a correlation coefficient of -0.8450 will be printed (please refer to screenshot named `ca_newdeceased_month.png`)


## Description of all files

## data_ingest:
  **data_ingest.txt** explains how the data is manually downloaded from its original website
  and then uploaded to hdfs 
## etl_code:
  **wes**:\
    initial cleaning code by wesley wang using mapreduce\
    **Clean.java**, **CleanMapper.java**, **CleanReducer.java** are the cleaning code

  **kyle**:\
    initial cleaning code by kyle ma using mapreduce\
    **Clean.java**, **CleanMapper.java**, **CleanReducer.java** are the cleaning code
## profiling_code:
  **wes**:\
    initial profiling code by wesley wang using mapreduce\
    **CountRecs.java**, **CountRecsMapper.java**, **ountRecsReducer.java** are the profiling code

  **kyle**:\
    initial profiling code by kyle ma using mapreduce\
    **CountRecs.java**, **CountRecsMapper.java**, **CountRecsReducer.java** are the profiling code


## ana_code:
  - **additional_process**
    - **additional_process.SCALA**
      * the code unifies the date column format for both crime_new.csv and epid_new.csv that generated from the initial cleaning process so the date from two data set matches in terms of range and type
      * it also drops columns irrelevant to future analysis
      - **it then output three csv files for future analysis**
        - crime_date.csv: data from crime_new.csv grouped by date and count total incident per date
      	- crime_type.csv: data from crime_new.csv grouped by date and crime type then count total incident per date for each type
      	- epid_clean.csv: data from epid_new.csv without unnecessary columns and uniformed date format
      * a few demonstrations would be shown on the interface in the process
  - **basic_statistics**
    - **allcrime_day_stats.SCALA** 
      * read in both crime and covid data
      * get rid of covid data in NY areas other than NYC since crime data are for NYC only
      * merge covid and crime dataframes and get basic statistics for both (mean, median, max, min, etc.)
    - **allcrime_newconfirmed_month_stats.SCALA**
      * read in both crime and covid data
      * get rid of covid data in NY areas other than NYC since crime data are for NYC only
      * group both dataframes by month, merge, and get basic statistics
      * note that when grouping, we keep the new_confirmed column for future statistics calculation;
    - **allcrime_newdeceased_month_stats.SCALA**
      * read in both crime and covid data
      * get rid of covid data in NY areas other than NYC since crime data are for NYC only
      * group both dataframes by month, merge, and get basic statistics
      * note that when grouping, we keep the new_deceased column for future statistics calculation
  - **correlation_analysis**
    - **allcrime_newcomfirmed_day_corr.SCALA**
      * read in both crime and covid data
      * truncate datasets to span the same period of time (2020-01-23 to 2020-12-31)
      * get rid of covid data in NY areas other than NYC since crime data are for NYC only
      * calculate correlation coefficient between covid new confirmed and crime count (all types) by day
    - **allcrime_newcomfirmed_month_corr.SCALA**
      * read in both crime and covid data
      * truncate datasets to span the same period of time (2020-01-23 to 2020-12-31)
      * get rid of covid data in NY areas other than NYC since crime data are for NYC only
      * group both dataframe (covid and crime) by month and get the mean of covid new confirmed
      * calculate correlation coefficient between mean of covid new confirmed and crime count (all types) by month
    - **allcrime_newdeceased_day_corr.SCALA**
      * read in both crime and covid data
      * truncate datasets to span the same period of time (2020-01-23 to 2020-12-31)
      * get rid of covid data in NY areas other than NYC since crime data are for NYC only
      * calculate correlation coefficient between covid new deceased and crime count (all types) by day
    - **allcrime_newdeceased_month_corr.SCALA**
      * read in both crime and covid data
      * truncate datasets to span the same period of time (2020-01-23 to 2020-12-31)
      * get rid of covid data in NY areas other than NYC since crime data are for NYC only
      * group both dataframe (covid and crime) by month and get the mean of covid new deceased
      * calculate correlation coefficient between mean of covid new deceased and crime count (all types) by month
    - **typecrime_newconfirmed_day_corr.SCALA**
      * the code finds correlation between daily newconfirmed covide cases and the number of four types of crime incidents
      * the four incidents are Robbery, Felony assult, Sex crimes, and Harrassment 2.
      - **the result is printed**:
      	- Robbery Correlation is: -0.37035180358283887
        - FELONY ASSAULT Correlation is: -0.46913761145316174
        - SEX CRIMES Correlation is: -0.45638676093112335
        - HARRASSMENT 2 Correlation is: -0.6489481870609697
  - **additional_analysis**
    - **crime_month_before_after.SCALA**
      * check which month do crime happen most frequently before and after covid
      * by isolating the data from 2019 and 2020 from crime_new.csv and transform the datetype of date column to month
      * the crime count from 2019 and 2020 per month are generated by group the data set by month and count
      - **result shows that**:
        - in 2019 before covid July is when crime happen most frequently
        - in 2020 after covid August is when crime happen most frequently
      * for exploration purpose
      *  the crime count from 2019 and 2020 are also grouped by month and type 
      * the result is output into csv files for easier examination
    - **crime_weekday_before_after.SCALA**
      * check which weekday do crime happen most frequently before and after covid
      * by isolating the data from 2019 and 2020 from crime_new.csv and transform the datetype of date column to weekday
      * the crime count from 2019 and 2020 per month are generated by group the data set by weekday and count
      * the one weekday with 53 occurence is normalized by multipling 52/53 
      - **result shows that**:
        - in 2019 before covid Friday is when crime happen most frequently
        - in 2020 after covid Friday is when crime happen most frequently
        - There is not much difference except for all days in a week, crime happen less after covid
    - **covid_month_day_series.SCALA**
      * note that the covid dataset contains these columns: new_confirmed, new_deceased, new_recovered, total_confirmed, total_deceased
      * read in covid dataset as a dataframe;
      * truncate the dataframe to span 2020-01-23 to 2020-12-31
      * extract the new_confirmed column into a sepearte dataframe, and write as an single output
      * do the same for new_deceased, new_recovered, total_confirmed, total_deceased
      * then, group the dataframes based on month and sum each of the five columns
      * write 5 new outputs based on these grouped and summed output
      * note: this file and the process within, aims to produce neatly formatted time series data so as to make good plots
      * note: it is hard to direclty understand all these time series data, but with a graph like line plot, the overall trend will be clear
    - **crime_day_night.SCALA**
      * read in crime dataset as dataframe
      * det rid of uncessary columns, keeping the column which records the exact time of the crimes
      * truncate the dataframe to span 2019-01-01 to 2020-12-31
      * create two separate dataframes based on whether the crime happened before covid or after (based on the first covid incident which occured on 2020-02-28)
      * group both dataframes based on hour (0-23) and count the total crime happened during those hours throughout the entire before/after-covid period
      * order the entries based on count of crimes
      * note: this analysis can show us whether the crime-popular hour/time of the day changes before and after covid
    - **crime_covid_regression.SCALA**:
      * read in both crime and covid dataset as dataframes
      * filter the covid dataset to contain only nyc data and span the period of 2020-01-23 to 2021-01-01
      * merge to datasets based on date
      - prepare for regression:
        - select new_confirmed, new_deceased, and new_recovered as features, and crime count as label
        - initialize linear regression model
        - fit the dataframe
        - output: number of iterations, objective history, RMSE, r2, coefficients and intercept
      * note: this analysis does not gurantee any causal inference, we focus on how each feature about covid relates to crime count (by observing the coefficients)
