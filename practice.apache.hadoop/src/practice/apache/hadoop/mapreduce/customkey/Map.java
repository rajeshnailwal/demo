package practice.apache.hadoop.mapreduce.customkey;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<LongWritable, Text, CustomKey, IntWritable>{
	
	private Text url = new Text();
	private Text date = new Text();
	private Text time = new Text();
	private Text ip = new Text();
	private IntWritable id = new IntWritable();
	
	private CustomKey key = new CustomKey();
	private static final IntWritable one = new IntWritable(1);
	
	@Override
	public void map(LongWritable identifier, Text line, Context context) throws IOException, InterruptedException{
		String[] values = line.toString().split("\t");
		
		id.set(Integer.parseInt(values[0]));
		url.set(values[1]);
		date.set(values[2]);
		time.set(values[3]);
		ip.set(values[4]);
		
		key.set(id, url, date, time, ip);
		context.write(key, one);
	}
}
