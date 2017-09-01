package practice.apache.hadoop.mapreduce.customkey;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class CustomKey implements WritableComparable<CustomKey> {
	
	private Text url, date, time, ip;
	private IntWritable id;
	
	public CustomKey(){
		id = new IntWritable();
		url = new Text();
		date = new Text();
		time = new Text();
		ip = new Text();
	}
	
	public CustomKey(IntWritable id, Text url, Text date, Text time, Text ip){
		this.id = id;
		this.url = url;
		this.date = date;
		this.time = time;
		this.ip = ip;
	}
	
	public void set(IntWritable id, Text url, Text date, Text time, Text ip){
		this.id = id;
		this.url = url;
		this.date = date;
		this.time = time;
		this.ip = ip;
	}
	
	public Text getUrl() {
		return url;
	}

	public Text getDate() {
		return date;
	}

	public Text getTime() {
		return time;
	}

	public Text getIp() {
		return ip;
	}

	public IntWritable getId() {
		return id;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		id.readFields(in);
		url.readFields(in);
		date.readFields(in);
		time.readFields(in);
		ip.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		id.write(out);
		url.write(out);
		date.write(out);
		time.write(out);
		ip.write(out);
	}

	@Override
	public int compareTo(CustomKey key) {
		if(ip.compareTo(key.ip) == 0){
			return time.compareTo(key.time);
		}
		return ip.compareTo(key.ip);
	}
	
	@Override
	public boolean equals(Object object){
		boolean isSameClass = object != null ? object.getClass().getName().equals(this.getClass().getName()) : false;
		
		if(isSameClass){
			CustomKey other = (CustomKey)object;
			return (this.ip == other.ip || (this.ip != null && this.ip.equals(other.ip))) 
					&& (this.time == other.time || (this.time != null && this.time.equals(other.time)));
		}
		
		return isSameClass;
	}
	
	@Override
	public int hashCode(){
		return this.ip.hashCode();
	}
}
