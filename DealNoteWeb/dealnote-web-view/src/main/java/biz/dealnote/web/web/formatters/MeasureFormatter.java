package biz.dealnote.web.web.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import biz.dealnote.web.model.Measure;
import biz.dealnote.web.service.DealNoteService;

public class MeasureFormatter implements Formatter<Measure> {

	private DealNoteService dealNoteService;
	
	@Autowired
	public MeasureFormatter(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@Override
	public String print(Measure object, Locale locale) {
		return object.getName();
	}

	@Override
	public Measure parse(String id, Locale locale) throws ParseException {
		Measure measure = dealNoteService.getMeasureById(Integer.valueOf(id));
		if(measure != null)
			return measure;
		throw new ParseException("Measure with id not found: " + id, 0);
	}

}
