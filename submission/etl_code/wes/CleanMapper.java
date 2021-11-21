import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class CleanMapper
 extends Mapper<LongWritable, Text, Text, IntWritable> {
 private static final int MISSING = 9999;

 @Override
 public void map(LongWritable key, Text value, Context context)
 throws IOException, InterruptedException {

	String temp = value.toString();
	String[] arr = temp.split(",");
	int id = Integer.parseInt(arr[0]);  
	String op = arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+","+arr[4]+","+arr[6]+","+arr[8];
	context.write(new Text(op),new IntWritable(1));
 
 
 
 
	 }
	}
