import java.io.IOException;    

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class CleanMapper
	extends Mapper<LongWritable, Text, Text, IntWritable>{


	@Override
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException{


		String[] line = value.toString().split(",");


		if (line[1].equals("US_NY") | line[1].equals("US_NY_NYC")){

			for(int i = 0; i < line.length; i++){
				if(line[i].isEmpty()){
					line[i] = "0";
				}



			}
			String out = String.join(",", line);
			context.write( new Text(out), new IntWritable(1));
		}
		else{
			return;
		}


	}
}