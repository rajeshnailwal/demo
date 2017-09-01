package practice.apache.hadoop.mapreduce.customvalue;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, IntWritable, CustomValue> {

	@Override
	public void map(LongWritable id, Text line, Context context) throws IOException, InterruptedException{
		String[] fields = line.toString().split("\t");
		System.out.println(line+"===========>"+fields[1]);
		CustomValue value = new CustomValue(new IntWritable(Integer.parseInt(fields[0])) ,new IntWritable(Integer.parseInt(fields[1])), new Text(fields[2]));
		context.write(value.getManagerId(), value);
	}
}
