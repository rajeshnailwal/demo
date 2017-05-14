package producer.consumer.blockingqueue.consumer;

import producer.consumer.blockingqueue.message.Message;
import producer.consumer.blockingqueue.queue.QueueFactory;
/**
 * 
 * @author rajesh nailwal
 *
 */
public class Consumer implements Runnable{

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10000);
				Message msg = QueueFactory.getQueue().poll();
				System.out.println(msg.getMessage()+" consumed by "+Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
