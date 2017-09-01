package com.pune.hyatt.ms.remote.rmi.server.callbacks;

import com.pune.hyatt.ms.remote.api.jobs.IJob;

@FunctionalInterface
public interface JobRecievedCallback {
	public void recieve(IJob job);
}
