package com.pune.hyatt.ms.remote.api.jobs;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import com.pune.hyatt.ms.remote.bo.IObject;

public class SequentialJob extends SequentialJobAdapter {
	
	private LinkedList<IObject> job = new LinkedList<IObject>();
	private ReentrantLock lock = new ReentrantLock();
	
	public void addJob(IObject obj) {
		lock.lock();
		job.addLast(obj);
		lock.unlock();
	}

	public IObject getNextSequentialJob() {
		lock.lock();
		IObject obj = job.removeFirst();
		lock.unlock();
		return obj;
	}
	
	public void writeExternal(ObjectOutput out) throws IOException {
			
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
	}

		
}
