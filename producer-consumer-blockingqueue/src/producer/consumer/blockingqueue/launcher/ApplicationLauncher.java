package producer.consumer.blockingqueue.launcher;

import producer.consumer.blockingqueue.consumer.Consumer;
import producer.consumer.blockingqueue.producer.Producer;
/**
 * 
 * @author rajesh nailwal
 *
 */
public class ApplicationLauncher {

	public static void main(String[] args) {
		Thread t = new Thread(new Producer(), "Producer Thread 1");
		t.start();
		t = new Thread(new Producer(), "Producer Thread 2");
		t.start();
		t = new Thread(new Producer(), "Producer Thread 3");
		t.start();
		
		t = new Thread(new Consumer(), "Consumer Thread 1");
		t.start();
		t = new Thread(new Consumer(), "Consumer Thread 2");
		t.start();
		t = new Thread(new Consumer(), "Consumer Thread 3");
		t.start();
	}

}
