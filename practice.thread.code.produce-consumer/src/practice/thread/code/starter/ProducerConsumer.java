package practice.thread.code.starter;

import practice.thread.code.producerconsumer.Consumer;
import practice.thread.code.producerconsumer.MessageQueue;
import practice.thread.code.producerconsumer.Producer;

public class ProducerConsumer {
	
	public static void main(String...args) {
		
		MessageQueue mq = new MessageQueue();
		
		Thread producer = new Thread(new Producer(mq));
		Thread consumer1 = new Thread(new Consumer(mq), "Consumer 1");
		Thread consumer2 = new Thread(new Consumer(mq), "Consumer 2");
		
		producer.start();
		consumer1.start();
		consumer2.start();
	}
	
}
