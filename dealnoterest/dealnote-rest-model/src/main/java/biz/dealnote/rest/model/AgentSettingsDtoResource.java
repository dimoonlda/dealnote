package biz.dealnote.rest.model;

import java.util.Collection;

import biz.dealnote.rest.model.dto.AgentSettingsDto;

public class AgentSettingsDtoResource extends AbstractModelResource<AgentSettingsDto> {
	
	public AgentSettingsDtoResource(){}
	
	public AgentSettingsDtoResource(Collection<AgentSettingsDto> data){
		super(data);
	}
}
