package com.pune.hyatt.ms.remote.api.jobs;

import com.pune.hyatt.ms.remote.bo.IObject;

public abstract class ParallelJobAdapter implements IJob {
	public IObject getNextSequentialJob(){return null;}
}
