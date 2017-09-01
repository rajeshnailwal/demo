package com.pune.hyatt.ms.remote.bo;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.pune.hyatt.ms.remote.api.jobs.ITask;

public class WSBusinessObject implements IWSBusinessObject {

	private ITask task;
	
	public WSBusinessObject(ITask task){
		this.task = task;
	}
	
	public void writeExternal(ObjectOutput out) throws IOException {
		
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
	}

	public Object call() throws Exception {
		return task.execute();
	}
	
}
