package practice.thread.code.producerconsumer;

import java.util.logging.Logger;

public class Producer implements Runnable {
	
	private static Logger log = Logger.getLogger(Producer.class.getName());
	
	private MessageQueue mq = null;
	
	public Producer(MessageQueue mq){
		this.mq = mq;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				String message = Long.toString(System.nanoTime());
				System.out.println("Message Produced : "+message);
				mq.addMessage(message);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				log.info("Producer thread got interrupted");
			}
		}
	}

}
