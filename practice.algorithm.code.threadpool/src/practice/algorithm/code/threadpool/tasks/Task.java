package practice.algorithm.code.threadpool.tasks;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class Task implements Runnable {
	
	private String id;
	
	public Task(String id){
		this.id = id;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Task "+this+" is completed");
	}
	
	public String toString(){
		return id;
	}

}
