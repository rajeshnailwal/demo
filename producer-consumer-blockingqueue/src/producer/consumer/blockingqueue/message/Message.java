package producer.consumer.blockingqueue.message;
/**
 * 
 * @author rajesh nailwal
 *
 */
public class Message {
	
	private String message;
	
	public Message(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
