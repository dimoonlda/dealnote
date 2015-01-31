package biz.dealnote.web.web.formatters;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.service.DealNoteService;

public class AgentFormatter implements Formatter<Agent>{

	private DealNoteService dealNoteService;
	
	@Autowired
	public AgentFormatter(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@Override
	public String print(Agent object, Locale locale) {
		return object.getName();
	}

	@Override
	public Agent parse(String id, Locale locale) throws ParseException {
		Agent agent = dealNoteService.getAgentById(Integer.valueOf(id));
		if(agent != null)
			return agent;
		throw new ParseException("agent with id not found: " + id, 0);
	}
}
