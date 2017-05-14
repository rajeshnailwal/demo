package practice.algorithm.code.threadpool.launcher;

import practice.algorithm.code.threadpool.pool.ThreadPool;
import practice.algorithm.code.threadpool.tasks.Task;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class ApplicationLauncher {

	public static void main(String[] args) {
		ThreadPool pool = ThreadPool.getThreadPool(5);
		
		Task task = new Task("1");
		pool.assignTask(task);
		
		task = new Task("2");
		pool.assignTask(task);
		
		task = new Task("3");
		pool.assignTask(task);
		
		task = new Task("4");
		pool.assignTask(task);
		
		task = new Task("5");
		pool.assignTask(task);
		
		task = new Task("6");
		pool.assignTask(task);
		
		task = new Task("7");
		pool.assignTask(task);
		
		task = new Task("8");
		pool.assignTask(task);
		
		task = new Task("9");
		pool.assignTask(task);
		
		task = new Task("10");
		pool.assignTask(task);
	}

}
