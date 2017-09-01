package practice.apache.hadoop.mapreduce.customvalue;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class Reduce extends Reducer<IntWritable, CustomValue, IntWritable, Result> {
	
	@Override
	public void reduce(IntWritable managerId, Iterable<CustomValue> values, Context context) throws IOException, InterruptedException{
		IntWritable count = new IntWritable(0);
		
		for(CustomValue value : values){
			count.set(count.get()+1);
		}
		
		Result result = new Result(managerId, count);
		context.write(managerId, result);
	}
	
}
