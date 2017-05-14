package producer.consumer.blockingqueue.queue;

import producer.consumer.blockingqueue.message.Message;
/**
 * 
 * @author rajesh nailwal
 *
 */
public class QueueFactory {
	
	private static BlockingQueue<Message> queue;
	
	public static BlockingQueue<Message> getQueue(){
		if(queue == null){
			synchronized (QueueFactory.class) {
				if(queue == null){
					queue = new BlockingQueue<Message>();
				}
			}
		}
		return queue;
	}
	
}
