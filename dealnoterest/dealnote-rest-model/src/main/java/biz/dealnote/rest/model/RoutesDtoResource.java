package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.RouteDto;

public class RoutesDtoResource extends AbstractModelResource<RouteDto> {
	
	public RoutesDtoResource(){}
	
	public RoutesDtoResource(Collection<RouteDto> data){
		super(data);
	}
}
