package practice.apache.hadoop.mapreduce.customkey;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<CustomKey, IntWritable, Text, IntWritable> {
	
	@Override
	public void reduce(CustomKey key, Iterable<IntWritable> iterable, Context context) throws IOException, InterruptedException{
		int sum = 0;
		for(IntWritable num : iterable){
			sum += num.get();
		}
		context.write(key.getIp(), new IntWritable(sum));
	}

}
