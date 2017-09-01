package com.pune.hyatt.ms.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pune.hyatt.ms.remote.api.IRemoteJobObjectStore;
import com.pune.hyatt.ms.remote.api.jobs.ParallelJob;

@SpringBootApplication
public class ApplicationLauncher {
	
	private static IRemoteJobObjectStore look_up;

	public static void main(String[] args)
		throws MalformedURLException, RemoteException, NotBoundException {

		look_up = (IRemoteJobObjectStore) Naming.lookup("//localhost/ws");
		
		ParallelJob job = new ParallelJob();
		look_up.submitWSBusinessObject(job);

	}
}
