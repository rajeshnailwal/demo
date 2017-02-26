package practice.thread.code.producerconsumer;

import java.util.logging.Logger;

public class Consumer implements Runnable {

	private static Logger log = Logger.getLogger(Consumer.class.getName());
	private MessageQueue mq = null;
	
	public Consumer(MessageQueue mq){
		this.mq = mq;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				String message = mq.getMessage();
				System.out.println("Message consumed by "+Thread.currentThread().getName()+" is : "+message);
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				log.info("Consumer thread got interrupted");
			}
		}
	}

}
