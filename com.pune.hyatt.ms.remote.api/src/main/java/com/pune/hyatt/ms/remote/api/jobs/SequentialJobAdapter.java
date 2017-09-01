package com.pune.hyatt.ms.remote.api.jobs;

import java.util.List;

import com.pune.hyatt.ms.remote.bo.IObject;

public abstract class SequentialJobAdapter implements IJob {

	public List<IObject> getParallelJobs() {return null;}

}
