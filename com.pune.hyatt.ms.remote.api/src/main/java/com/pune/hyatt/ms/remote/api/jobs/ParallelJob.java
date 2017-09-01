package com.pune.hyatt.ms.remote.api.jobs;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.pune.hyatt.ms.remote.bo.IObject;

public class ParallelJob extends ParallelJobAdapter {
	
	private List<IObject> job = new ArrayList<IObject>();
	
	public void addJob(IObject obj){
		job.add(obj);
	}

	public List<IObject> getParallelJobs() {
		return Collections.unmodifiableList(job);
	}
	
	public void writeExternal(ObjectOutput out) throws IOException {
		
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
	}
	
}
