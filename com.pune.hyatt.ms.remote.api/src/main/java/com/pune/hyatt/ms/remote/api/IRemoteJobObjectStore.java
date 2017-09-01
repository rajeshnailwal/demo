package com.pune.hyatt.ms.remote.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.pune.hyatt.ms.remote.api.jobs.IJob;

@FunctionalInterface
public interface IRemoteJobObjectStore extends Remote {
    public void submitWSBusinessObject(IJob job) throws RemoteException;
}
