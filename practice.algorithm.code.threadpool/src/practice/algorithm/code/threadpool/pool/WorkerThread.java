package practice.algorithm.code.threadpool.pool;

import practice.algorithm.code.threadpool.threadqueue.BlockingQueue;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class WorkerThread extends Thread {
	private Runnable task;
	private BlockingQueue<WorkerThread> availableThreadQueue;
	private Object lock = new Object();
	
	
	public WorkerThread(BlockingQueue<WorkerThread> availableThreadQueue){
		this.availableThreadQueue = availableThreadQueue;
	}
	
	public void run(){
		while(true){
			synchronized (lock) {
				try {
					while(task == null) lock.wait();
					task.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					task = null;
					lock.notify();
				}				
				
				try {
					availableThreadQueue.offer(this);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setTask(Runnable task){
		synchronized (lock) {
			try {
				while(this.task != null) lock.wait();
				this.task = task;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.notifyAll();
			}
		}
	}
}