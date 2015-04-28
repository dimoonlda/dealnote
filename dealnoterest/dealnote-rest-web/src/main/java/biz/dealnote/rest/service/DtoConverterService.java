package biz.dealnote.rest.service;

import java.util.Collection;
import java.util.Optional;

import biz.dealnote.rest.controllers.exceptions.CreateDtoException;
import biz.dealnote.rest.model.dto.AgentGoodsDto;
import biz.dealnote.rest.model.dto.AgentSettingsDto;
import biz.dealnote.rest.model.dto.ClientDto;
import biz.dealnote.rest.model.dto.ClientGroupDto;
import biz.dealnote.rest.model.dto.DocumentDetailDto;
import biz.dealnote.rest.model.dto.DocumentDto;
import biz.dealnote.rest.model.dto.GoodsDto;
import biz.dealnote.rest.model.dto.GoodsGroupDto;
import biz.dealnote.rest.model.dto.LocationDto;
import biz.dealnote.rest.model.dto.MeasureDto;
import biz.dealnote.rest.model.dto.MeasureLinkDto;
import biz.dealnote.rest.model.dto.PriorityColorDto;
import biz.dealnote.rest.model.dto.RouteDto;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.DocumentDetail;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;

public interface DtoConverterService {
	public AgentSettingsDto buildAgentSettingsDto(Agent agent) throws CreateDtoException;

	//public Route buildRoute(RouteDto routeDto);
	public RouteDto buildRouteDto(Route route);
	public Collection<RouteDto> buildRouteDtoCollection(Collection<Route> routes) 
			throws CreateDtoException;
	
	//public PriorityColor buildPriorityColor(PriorityColorDto priorityColorDto);
	public PriorityColorDto buildPriorityColorDto(PriorityColor priorityColor);
	public Collection<PriorityColorDto> buildPriorityColorDtoCollection(Collection<PriorityColor> priorityColors) 
			throws CreateDtoException;

	//public Measure buildMeasure(MeasureDto measureDto);
	public MeasureDto buildMeasureDto(Measure measure);
	public Collection<MeasureDto> buildMeasureDtoCollection(Collection<Measure> measures) 
			throws CreateDtoException;

	//public MeasureLink buildMeasureLink(MeasureLinkDto measureLinkDto);
	public MeasureLinkDto buildMeasureLinkDto(MeasureLink measureLink);
	public Collection<MeasureLinkDto> buildMeasureLinkDtoCollection(Collection<MeasureLink> measureLinks) 
			throws CreateDtoException;

	//public GoodsGroup buildGoodsGroup(GoodsGroupDto groupDto);
	public GoodsGroupDto buildGoodsGroupDto(GoodsGroup group);
	public Collection<GoodsGroupDto> buildGoodsGroupDtoCollection(Collection<GoodsGroup> groups) 
			throws CreateDtoException;

	//public Goods buildGoods(GoodsDto goodsDto);
	public GoodsDto buildGoodsDto(Goods goods);
	public Collection<GoodsDto> buildGoodsDtoCollection(Collection<Goods> goods) 
			throws CreateDtoException;

	//public ClientGroup buildClientGroup(ClientGroupDto clientGroupDto);
	public ClientGroupDto buildClientGroupDto(ClientGroup clientGroup);
	public Collection<ClientGroupDto> buildClientGroupDtoCollection(Collection<ClientGroup> clientGroups) 
			throws CreateDtoException;

	//public Client buildClient(ClientDto clientDto);
	public ClientDto buildClientDto(Client client);
	public Collection<ClientDto> buildClientDtoCollection(Collection<Client> clients) 
			throws CreateDtoException;

	public AgentGoodsDto buildAgentGoodsDto(AgentGoods goods);
	public Collection<AgentGoodsDto> buildAgentGoodsDtoCollection(Collection<AgentGoods> goods) 
			throws CreateDtoException;

	public Location buildLocation(LocationDto locationDto);
	
	public Optional<Document> biuldDocument(DocumentDto documentDto);
	public Optional<DocumentDetail> buildDocumentDetail(DocumentDetailDto documentDetailDto);
}
