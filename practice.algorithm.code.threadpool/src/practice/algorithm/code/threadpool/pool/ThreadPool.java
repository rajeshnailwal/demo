package practice.algorithm.code.threadpool.pool;

import practice.algorithm.code.threadpool.threadqueue.BlockingQueue;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class ThreadPool {
	
	private BlockingQueue<WorkerThread> availableThreadQueue;
	
	private static ThreadPool threadPool;
	
	private ThreadPool(int size){
		availableThreadQueue = new BlockingQueue<WorkerThread>(size);
		WorkerThread thread;
		for(int i = 0; i < size; i++){
			try {
				thread = new WorkerThread(availableThreadQueue);
				thread.start();
				availableThreadQueue.offer(thread);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void assignTask(Runnable task){
		try {
			WorkerThread thread = availableThreadQueue.poll();
			thread.setTask(task);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static ThreadPool getThreadPool(int size){
		if(threadPool == null){
			synchronized (ThreadPool.class) {
				if(threadPool == null){
					threadPool = new ThreadPool(size);
				}
			}
		}
		return threadPool;
	}
	
}