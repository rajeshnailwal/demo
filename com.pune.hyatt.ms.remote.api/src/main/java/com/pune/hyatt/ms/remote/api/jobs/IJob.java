package com.pune.hyatt.ms.remote.api.jobs;

import java.io.Externalizable;
import java.util.List;

import com.pune.hyatt.ms.remote.bo.IObject;

public interface IJob extends Externalizable {
	public void addJob(IObject obj);
	public IObject getNextSequentialJob();
	public List<IObject> getParallelJobs();
}
