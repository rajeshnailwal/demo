package practice.thread.code.executioncontrolledthread;

import java.util.logging.Logger;

public class ControlledThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(ControlledThread.class.getName());
	
	private static Lock lock = new Lock();
	private String nextThreadToExecute;
	
	public ControlledThread(String nextThreadToExecute) {
		this.nextThreadToExecute = nextThreadToExecute;
	}
	
	public static void setBeginnerThread(String threadName){
		if(lock.getThreadToExecute() != null){
			logger.info("Beginner thread is already set and it is "+lock.getThreadToExecute());
		} else {
			lock.setThreadToExecute(threadName);
		}
	}
	
	@Override
	public void run() {
		try {
			while(true){
				synchronized (lock) {
					System.out.println("Lock obtained by : "+Thread.currentThread().getName());
					
					while(!lock.getThreadToExecute().equals(Thread.currentThread().getName())) {
						lock.wait();
					}
					
					System.out.println("Current thread for execution : "+lock.getThreadToExecute());
					
					/*
					 * Do whatever you want to do here 
					 * 
					 * Put your business logic in this place before this thread hands over task to next
					 * thread 
					 */
					
					lock.setThreadToExecute(nextThreadToExecute);					
					
					System.out.println("Next thread for execution : "+lock.getThreadToExecute());
					
					lock.notifyAll();
				}
				System.out.println("One iteration of thread : "+Thread.currentThread().getName()+" finished");
			}
		} catch (Exception e) {
			logger.info(Thread.currentThread().getName()+" interrupted due to "+e.getClass().getName());
		}
	}
	
	private static class Lock {
		private String threadToExecute;

		public synchronized String getThreadToExecute() {
			return threadToExecute;
		}

		public synchronized void setThreadToExecute(String threadToExecute) {
			this.threadToExecute = threadToExecute;
		}
		
	}

}
