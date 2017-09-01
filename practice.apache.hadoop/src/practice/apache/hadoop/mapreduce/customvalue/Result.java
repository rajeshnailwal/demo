package practice.apache.hadoop.mapreduce.customvalue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;

public class Result implements Writable {

	private IntWritable managerId;
	private IntWritable employees;
	
	public Result(){
		managerId = new IntWritable();
		employees = new IntWritable();
	}
	
	public Result(IntWritable managerId, IntWritable employees){
		this.managerId = managerId;
		this.employees = employees;
	}
	
	public IntWritable getManagerId() {
		return managerId;
	}

	public IntWritable getEmployees() {
		return employees;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		managerId.readFields(in);
		employees.readFields(in);
	}

	@Override
	public void write(DataOutput out) throws IOException {
		managerId.write(out);
		employees.write(out);
	}
	
	public String toString(){
		return managerId+"\t"+employees;
	}
	
}
