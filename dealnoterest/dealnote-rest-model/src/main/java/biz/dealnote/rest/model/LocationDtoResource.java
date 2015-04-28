package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.LocationDto;

public class LocationDtoResource extends AbstractModelResource<LocationDto> {
	
	private RestClientInfo restClient;
	
	public LocationDtoResource(){}
	
	public LocationDtoResource(RestClientInfo restClient, 
			Collection<LocationDto> data){
		super(data);
		this.restClient = restClient;
	}

	public RestClientInfo getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClientInfo restClient) {
		this.restClient = restClient;
	}
	
}
