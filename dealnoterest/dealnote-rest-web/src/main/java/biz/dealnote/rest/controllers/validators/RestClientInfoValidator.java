package biz.dealnote.rest.controllers.validators;

import org.springframework.validation.Errors;

import biz.dealnote.rest.model.RestClientInfo;

public class RestClientInfoValidator {
	
	public void validate(RestClientInfo client, Errors errors){
		
		if(client == null){
			errors.reject("client object is null.");
			return;
		}
		
		if(client.getAgentId() == null){
			errors.rejectValue("agentId", "required", "agentId is null.");
		}
		
	}
}
