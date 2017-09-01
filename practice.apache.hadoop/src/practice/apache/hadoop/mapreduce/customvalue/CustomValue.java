package practice.apache.hadoop.mapreduce.customvalue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class CustomValue implements Writable {

	private IntWritable managerId;
	private IntWritable employeeId;
	private Text name;
	
	public CustomValue(){
		this.managerId = new IntWritable();
		this.employeeId = new IntWritable();
		this.name = new Text();
	}
	
	public CustomValue(IntWritable managerId, IntWritable employeeId, Text name) {
		this.managerId = managerId;
		this.employeeId = employeeId;
		this.name = name;
	}
	
	public IntWritable getManagerId() {
		return managerId;
	}

	public IntWritable getEmployeeId() {
		return employeeId;
	}

	public Text getName() {
		return name;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		managerId.readFields(in);
		employeeId.readFields(in);
		name.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		managerId.write(out);
		employeeId.write(out);
		name.write(out);
	}	
	
}
