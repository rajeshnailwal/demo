package practice.spring.code.ws.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BigSaleDayOfferBroadcastScheduler {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	private String[] offerItem = {"T-Shirts 45 % discount", "Home Appliances 30% discount", "Electronics upto 70% discount"};
	private int current = 0;
	
	@Scheduled(fixedRate = 6*1000)
	public void broadCastOffer(){
		String offer = offerItem[++current % 3];
		template.convertAndSend("/bsday/offers",offer);
		System.out.println("here");
	}
	
}
