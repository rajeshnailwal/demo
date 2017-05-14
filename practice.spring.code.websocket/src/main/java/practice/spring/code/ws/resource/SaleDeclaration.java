package practice.spring.code.ws.resource;

public class SaleDeclaration {
	private long time;
	private String saleMessage;
	
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getSaleMessage() {
		return saleMessage;
	}
	public void setSaleMessage(String message) {
		this.saleMessage = message;
	}	
}
