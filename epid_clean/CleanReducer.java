import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.NullWritable;

public class CleanReducer
	extends Reducer<Text, IntWritable, NullWritable, Text>{

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
		throws IOException, InterruptedException{

		NullWritable nullWritable = NullWritable.get();

		for (IntWritable value : values){
			context.write(nullWritable, key);

		}


	}


	
}
