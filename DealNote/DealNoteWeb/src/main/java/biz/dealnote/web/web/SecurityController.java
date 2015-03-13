package biz.dealnote.web.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import biz.dealnote.web.model.Message;

@Controller
@RequestMapping(value="/security")
public class SecurityController {

	private MessageSource messageSource;
	
	@RequestMapping(value = "/loginfail")
	public String loginFail(Model model, Locale locale){
		model.addAttribute("message", new Message("error", 
				messageSource.getMessage("message.error.login", new Object[]{}, locale)));
		return "login";
	}
	
	@RequestMapping(value = "/login")
	public String initLogin(){
		return "login";
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
