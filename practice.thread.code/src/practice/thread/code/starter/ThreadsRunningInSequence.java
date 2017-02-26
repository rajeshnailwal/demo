package practice.thread.code.starter;

import practice.thread.code.executioncontrolledthread.ControlledThread;

public class ThreadsRunningInSequence {
	public static void main(String...args) {
		
		String threadName = "ControlledThread : Thread A";
		String nextThreadName = "ControlledThread : Thread B";
		Thread t1 = new Thread(new ControlledThread(nextThreadName), threadName);
		
		threadName = nextThreadName;
		nextThreadName = "ControlledThread : Thread C";
		Thread t2 = new Thread(new ControlledThread(nextThreadName), threadName);
		
		threadName = nextThreadName;
		nextThreadName = "ControlledThread : Thread A";
		Thread t3 = new Thread(new ControlledThread(nextThreadName), threadName);
		
		ControlledThread.setBeginnerThread(nextThreadName);
		
		t1.start();
		t2.start();
		t3.start();
	}
}
