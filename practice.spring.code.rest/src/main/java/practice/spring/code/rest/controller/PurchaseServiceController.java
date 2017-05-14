package practice.spring.code.rest.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import practice.spring.code.rest.resource.ItemType;
import practice.spring.code.rest.resource.Order;
import practice.spring.code.rest.resource.OrderStatus;

@RestController
public class PurchaseServiceController {
	
	@RequestMapping("/")
	public String greetings(@RequestParam(value="user", defaultValue="Rajesh")String name){
		return "Welcome "+name;
	}
	
	@RequestMapping("/fetchitemlist")
	public ItemType[] getItems(@RequestParam(value="type", defaultValue="all")String itemType){
		ItemType[] types = new ItemType[3];
		types[0] = new ItemType();
		types[0].setTypeName("Clothing");
		types[0].setGender("male");
		types[0].setCode(1);
		
		types[1] = new ItemType();
		types[1].setTypeName("Clothing");
		types[1].setGender("female");
		types[1].setCode(2);
		
		types[2] = new ItemType();
		types[2].setTypeName("Clothing");
		types[2].setGender("kids");
		types[2].setCode(3);
		return types;
	}
	
	@RequestMapping(value = "/orderbuy", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public OrderStatus orderBuy(@RequestBody Order order){
		OrderStatus status = new OrderStatus();
		status.setStatus("Successful");
		status.setMessage("Your order for item "+order.getItemId()+" will be delivered on Monday");
		return status;
	}
}
