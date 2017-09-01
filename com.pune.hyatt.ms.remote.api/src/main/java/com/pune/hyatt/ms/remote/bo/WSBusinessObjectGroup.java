package com.pune.hyatt.ms.remote.bo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class WSBusinessObjectGroup implements Externalizable, IObject{

	private List<IWSBusinessObject> group = new ArrayList<IWSBusinessObject>(); 
	private ReentrantLock lock = new ReentrantLock();
	
	public void addBusinessObjectInGroup(IWSBusinessObject bo){
		lock.lock();
		group.add(bo);
		lock.unlock();
	}
	
	public List<IWSBusinessObject> getGroup(){
		return Collections.unmodifiableList(group);
	}
	
	public void writeExternal(ObjectOutput out) throws IOException {
		
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		
	}	
}
