package biz.dealnote.web.web.formatters;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import biz.dealnote.web.model.Route;
import biz.dealnote.web.service.DealNoteService;

public class RouteFormatter implements Formatter<Route>{

	private DealNoteService dealNoteService;
	
	@Autowired
	public RouteFormatter(DealNoteService dealNoteService) {
		this.dealNoteService = dealNoteService;
	}
	
	@Override
	public String print(Route object, Locale locale) {
		return object.getName();
	}

	@Override
	public Route parse(String id, Locale locale) throws ParseException {
		Route route = dealNoteService.getRouteById(Integer.valueOf(id));
		if(route != null)
			return route;
		throw new ParseException("route with id not found: " + id, 0);
	}

}
