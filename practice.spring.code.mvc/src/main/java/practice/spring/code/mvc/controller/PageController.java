package practice.spring.code.mvc.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
	
	private static AtomicInteger counter = new AtomicInteger(0);
	
	@RequestMapping("/home")
	public String getHomePage(@RequestParam(value="name", required=false, defaultValue="Guest") String userName, Model model){
		model.addAttribute("name", userName);
		model.addAttribute("visitor", counter.incrementAndGet());
		return "homepage";
	}
}
