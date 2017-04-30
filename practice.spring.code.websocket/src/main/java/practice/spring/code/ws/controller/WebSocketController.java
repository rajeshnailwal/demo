package practice.spring.code.ws.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import practice.spring.code.ws.resource.SaleDeclaration;

@Controller
public class WebSocketController {
	
	@MessageMapping("/timeleft")
	@SendTo("/bsday/offers")
	public SaleDeclaration broadcastTimeLeftForSale(String msg){
		System.out.println(msg);
		SaleDeclaration sd = new SaleDeclaration();
		sd.setTime(System.nanoTime());
		sd.setSaleMessage("Returned "+msg);
		return sd;
	}
}
