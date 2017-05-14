package producer.consumer.blockingqueue.queue;

import java.util.LinkedList;
import java.util.Queue;
/**
 * 
 * @author rajesh nailwal
 *
 */
public class BlockingQueue<T> {
	
	private final int SIZE = 10;
	
	private Queue<T> queue = new LinkedList<T>();
	
	private Object lock = new Object();
	
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
