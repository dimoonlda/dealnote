package biz.dealnote.web.web.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.service.DealNoteService;

public class PriorityColorFormatter implements Formatter<PriorityColor> {

	private DealNoteService dealNoteService;
	
	@Autowired
	public PriorityColorFormatter(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@Override
	public String print(PriorityColor object, Locale locale) {
		return object.getColorCode();
	}

	@Override
	public PriorityColor parse(String id, Locale locale)
			throws ParseException {
		PriorityColor priority = dealNoteService.getPriorityColorById(Integer.valueOf(id));
		if(priority!=null){
			return priority;
		}
		throw new ParseException(
				String.format("PriorityColor with id %s is not found", id), 
				0);
	}

}
