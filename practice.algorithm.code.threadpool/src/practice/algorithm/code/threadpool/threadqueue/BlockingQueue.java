package practice.algorithm.code.threadpool.threadqueue;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author rajesh nailwal
 *
 */
public class BlockingQueue<T> {
	
	private final int SIZE;
	
	private Queue<T> queue = new LinkedList<T>();
	
	private Object lock = new Object();
	
	public BlockingQueue(){
		this.SIZE = 10;
	}
	
	public BlockingQueue(int size){
		this.SIZE = size;
	}
	
	public void offer(T t) throws InterruptedException{
		
		synchronized (lock) {
			
			while(queue.size() == SIZE) lock.wait();
			
			queue.offer(t);
			
			lock.notifyAll();			
		}
		
	}
	
	public T poll() throws InterruptedException{
		
		synchronized (lock) {
			while(queue.size() == 0) lock.wait();
			
			T t = queue.poll();
			
			lock.notifyAll();
			
			return t;
		}
	}
	
	
}
