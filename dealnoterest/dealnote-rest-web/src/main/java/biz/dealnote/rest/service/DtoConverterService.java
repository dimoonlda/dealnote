package biz.dealnote.rest.service;

import java.util.Collection;
import java.util.Optional;

import biz.dealnote.rest.controllers.exceptions.CreateDtoException;
import biz.dealnote.rest.model.dto.AgentGoodsDto;
import biz.dealnote.rest.model.dto.AgentSettingsDto;
import biz.dealnote.rest.model.dto.ClientDto;
import biz.dealnote.rest.model.dto.ClientGroupDto;
import biz.dealnote.rest.model.dto.DocTypeDto;
import biz.dealnote.rest.model.dto.DocumentDetailDto;
import biz.dealnote.rest.model.dto.DocumentDto;
import biz.dealnote.rest.model.dto.GoodsDto;
import biz.dealnote.rest.model.dto.GoodsGroupDto;
import biz.dealnote.rest.model.dto.LocationDto;
import biz.dealnote.rest.model.dto.MeasureDto;
import biz.dealnote.rest.model.dto.MeasureLinkDto;
import biz.dealnote.rest.model.dto.PayFormDto;
import biz.dealnote.rest.model.dto.PriorityColorDto;
import biz.dealnote.rest.model.dto.RouteDto;
import biz.dealnote.rest.model.dto.WsServerDto;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.DocType;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.DocumentDetail;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;
import biz.dealnote.web.model.PayForm;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.model.WsServer;

public interface DtoConverterService {
	public AgentSettingsDto buildAgentSettingsDto(Agent agent) throws CreateDtoException;

	public RouteDto buildRouteDto(Route route);
	public Collection<RouteDto> buildRouteDtoCollection(Collection<Route> routes) 
			throws CreateDtoException;
	
	public PriorityColorDto buildPriorityColorDto(PriorityColor priorityColor);
	public Collection<PriorityColorDto> buildPriorityColorDtoCollection(Collection<PriorityColor> priorityColors) 
			throws CreateDtoException;

	public MeasureDto buildMeasureDto(Measure measure);
	public Collection<MeasureDto> buildMeasureDtoCollection(Collection<Measure> measures) 
			throws CreateDtoException;

	public MeasureLinkDto buildMeasureLinkDto(MeasureLink measureLink);
	public Collection<MeasureLinkDto> buildMeasureLinkDtoCollection(Collection<MeasureLink> measureLinks) 
			throws CreateDtoException;

	public GoodsGroupDto buildGoodsGroupDto(GoodsGroup group);
	public Collection<GoodsGroupDto> buildGoodsGroupDtoCollection(Collection<GoodsGroup> groups) 
			throws CreateDtoException;

	public GoodsDto buildGoodsDto(Goods goods);
	public Collection<GoodsDto> buildGoodsDtoCollection(Collection<Goods> goods) 
			throws CreateDtoException;

	public ClientGroupDto buildClientGroupDto(ClientGroup clientGroup);
	public Collection<ClientGroupDto> buildClientGroupDtoCollection(Collection<ClientGroup> clientGroups) 
			throws CreateDtoException;

	public ClientDto buildClientDto(Client client);
	public Collection<ClientDto> buildClientDtoCollection(Collection<Client> clients) 
			throws CreateDtoException;

	public AgentGoodsDto buildAgentGoodsDto(AgentGoods goods);
	public Collection<AgentGoodsDto> buildAgentGoodsDtoCollection(Collection<AgentGoods> goods) 
			throws CreateDtoException;

	public Location buildLocation(LocationDto locationDto);
	
	public Optional<Document> buildDocument(DocumentDto documentDto);
	public Optional<DocumentDetail> buildDocumentDetail(DocumentDetailDto documentDetailDto);
	
	public WsServerDto buildWsServerDto(WsServer server);
	public Collection<WsServerDto> buildWsServerDtoCollection(Collection<WsServer> serversCol) 
			throws CreateDtoException;
	
	public PayFormDto buildPayFormDto(PayForm payForm);
	public Collection<PayFormDto> buildPayFormDtoCollection(Collection<PayForm> payFormCol) 
			throws CreateDtoException;

	public DocTypeDto buildDocTypeDto(DocType docType);
	public Collection<DocTypeDto> buildDocTypeDtoCollection(Collection<DocType> docTypeCol) 
			throws CreateDtoException;
}
