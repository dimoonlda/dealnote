package biz.dealnote.web.web.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.service.DealNoteService;

public class GoodsGroupFormatter implements Formatter<GoodsGroup> {

	private DealNoteService dealNoteService;
	
	@Autowired
	public GoodsGroupFormatter(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@Override
	public String print(GoodsGroup object, Locale locale) {
		return object.getName();
	}

	@Override
	public GoodsGroup parse(String id, Locale locale) throws ParseException {
		GoodsGroup group = dealNoteService.getGoodsGroupById(Integer.valueOf(id));
		if(group!=null){
			return group;
		}
		throw new ParseException(
				String.format("GoodsGroup with id %s is not found", id), 
				0);
	}

}
