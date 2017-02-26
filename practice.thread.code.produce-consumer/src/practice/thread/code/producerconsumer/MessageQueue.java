package practice.thread.code.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
	
	public static final int QUEUE_SIZE = 10;
	
	private final Queue<String> mq = new LinkedList<String>();
	
	public synchronized String getMessage() throws InterruptedException{
		
		while(mq.size() == 0) wait();
		
		String message = mq.poll();
		
		notifyAll();
		
		return message;
	}
	
	public synchronized void addMessage(String message) throws InterruptedException{
		
		while(mq.size() == QUEUE_SIZE) wait();
		
		mq.offer(message);
		
		notifyAll();
	}
	
	
}
