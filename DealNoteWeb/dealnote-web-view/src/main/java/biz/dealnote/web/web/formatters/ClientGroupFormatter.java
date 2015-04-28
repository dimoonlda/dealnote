package biz.dealnote.web.web.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.service.DealNoteService;

public class ClientGroupFormatter implements Formatter<ClientGroup> {

	private DealNoteService dealNoteService;
	
	@Autowired
	public ClientGroupFormatter(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@Override
	public String print(ClientGroup object, Locale locale) {
		return object.getName();
	}

	@Override
	public ClientGroup parse(String id, Locale locale) throws ParseException {
		ClientGroup clientGroup = dealNoteService.getGroupById(Integer.valueOf(id));
		if(clientGroup != null)
			return clientGroup;
		throw new ParseException("ClientGroup with id not found: " + id, 0);
	}

}
