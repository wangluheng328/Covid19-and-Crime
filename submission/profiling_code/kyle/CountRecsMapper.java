import java.io.IOException;    

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class CountRecsMapper
	extends Mapper<LongWritable, Text, Text, IntWritable>{


	@Override
	public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException{

		if (key.get() == 0){
			return;
		}

		String temp = "Total number of records: ";




		context.write(new Text(temp), new IntWritable(1));



	}
}