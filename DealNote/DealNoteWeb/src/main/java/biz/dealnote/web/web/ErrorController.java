package biz.dealnote.web.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {
	
	@RequestMapping(value = "/400")
	public String proceedError400(){
		return "error/400";
	}
	
	@RequestMapping(value = "/404")
	public String proceedError404(){
		return "error/404";
	}

	@RequestMapping(value = "/501")
	public String proceedError501(){
		return "error/501";
	}
	
	@RequestMapping(value = "/500")
	public String proceedError500(){
		return "error/500";
	}
}
