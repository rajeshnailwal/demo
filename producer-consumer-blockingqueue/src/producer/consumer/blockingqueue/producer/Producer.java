package producer.consumer.blockingqueue.producer;
/**
 * 
 * @author rajesh nailwal
 *
 */
import producer.consumer.blockingqueue.message.Message;
import producer.consumer.blockingqueue.queue.QueueFactory;

public class Producer implements Runnable {

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(500);
				Message message = new Message(Long.toString(System.nanoTime()));
				System.out.println(message.getMessage()+" produced by "+Thread.currentThread().getName());
				QueueFactory.getQueue().offer(message);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
