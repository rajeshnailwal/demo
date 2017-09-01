package com.pune.hyatt.ms.remote.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;

import com.pune.hyatt.ms.remote.api.IRemoteJobObjectStore;
import com.pune.hyatt.ms.remote.api.jobs.IJob;
import com.pune.hyatt.ms.remote.rmi.server.callbacks.JobRecievedCallback;

public class Server extends UnicastRemoteObject implements IRemoteJobObjectStore {
	
	private static JobRecievedCallback callback;
	private static ReentrantLock lock = new ReentrantLock();
	private static Server server;
	
	public Server(JobRecievedCallback callback) throws RemoteException {
		super();
		this.callback = callback;
	}
	
	public static final void start(String binding, JobRecievedCallback callback) throws RemoteException, MalformedURLException{
		lock.lock();
		System.setProperty("java.rmi.server.hostname",binding);
		if(server == null) Naming.rebind("//"+binding+"/ms", server = new Server(callback));
		lock.unlock();
	}

	public void submitWSBusinessObject(IJob job) {
		callback.recieve(job);
	}
	
}
