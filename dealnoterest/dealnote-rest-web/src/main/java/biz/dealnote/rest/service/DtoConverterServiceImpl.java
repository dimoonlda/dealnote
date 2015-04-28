package biz.dealnote.rest.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.rest.controllers.DealNoteRestController;
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
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.dao.LocationSaveStateDao;
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

@Service
public class DtoConverterServiceImpl implements DtoConverterService {

	private static Logger logger = Logger.getLogger(DtoConverterServiceImpl.class);
	
	@Autowired
	private LocationDAO LocationDao;
	
	@Autowired
	private AgentDAO agentDao;
	
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private ClientDAO clientDao;
	
	@Autowired
	private DocTypeDao docTypeDao;
	
	@Autowired
	private LocationSaveStateDao locationSaveStateDao;
	
	@Override
	public RouteDto buildRouteDto(Route route) {
			RouteDto dto = new RouteDto(route.getId(), 
					route.getName(), 
					route.getOuterId());
			return dto;
	}

	@Override
	public Collection<RouteDto> buildRouteDtoCollection(Collection<Route> routes) 
			throws CreateDtoException {
		try{
			return routes.stream()
					.map((route) -> buildRouteDto(route))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build RouteDto collection exception. ", e);
		}
	}

	@Override
	public PriorityColorDto buildPriorityColorDto(PriorityColor priorityColor) {
		PriorityColorDto dto = new PriorityColorDto(priorityColor.getId(), 
				priorityColor.getColorCode(), 
				priorityColor.getDescription());
		return dto;
	}

	@Override
	public Collection<PriorityColorDto> buildPriorityColorDtoCollection(
			Collection<PriorityColor> priorityColors) throws CreateDtoException {
		try{
			return priorityColors.stream()
					.map((priorityColor) -> buildPriorityColorDto(priorityColor))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build PriorityColorDto collection exception. ", e);
		}
	}

	@Override
	public MeasureDto buildMeasureDto(Measure measure) {
		MeasureDto dto = new MeasureDto(measure.getId(), 
				measure.getOuterId(), 
				measure.getName());
		return dto;

	}

	@Override
	public Collection<MeasureDto> buildMeasureDtoCollection(
			Collection<Measure> measures) throws CreateDtoException {
		try{
			return measures.stream()
					.map((measure) -> buildMeasureDto(measure))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build MeasureDto collection exception. ", e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AgentSettingsDto buildAgentSettingsDto(Agent agent)
			throws CreateDtoException {
		AgentSettingsDto dto = new AgentSettingsDto();
		dto.adminPass = agent.getAdminPass();
		dto.agentClientId = agent.getAgentAsClient() != null ? agent.getAgentAsClient().getId() : null;
		dto.autoDiscount = agent.getAutoDiscount();
		dto.dayDelDoc = agent.getDayDelDoc();
		dto.email = agent.getEmail();
		dto.fio = agent.getFio();
		dto.frequencyGetGPS = agent.getFrequencyGetGPS();
		dto.frequencySendGPS = agent.getFrequencySendGPS();
		dto.gpsByDay = agent.getGpsByDay();
		dto.gpsFromHour = agent.getGpsFromHour();
		dto.gpsToHour = agent.getGpsToHour();
		dto.id = agent.getId();
		dto.isAppStat = agent.getIsAppStat();
		dto.isGPS = agent.getIsGPS();
		dto.isGPSBeforeOrder = agent.getIsGPSBeforeOrder();
		dto.moneyformat = agent.getMoneyformat();
		dto.moneyname = agent.getMoneyname();
		dto.qtyformat = agent.getQtyformat();
		dto.regnumnext1 = agent.getRegnumnext1();
		dto.regnumnext2 = agent.getRegnumnext2();
		dto.regnumprefix1 = agent.getRegnumprefix1();
		dto.regnumprefix2 = agent.getRegnumprefix2();
		dto.strictstopship = agent.getStrictstopship();
		dto.vsandps = agent.getVsandps();
		dto.wsServiceName = agent.getWsServiceName();
		return dto;
	}

	@Transactional(readOnly = true)
	@Override
	public MeasureLinkDto buildMeasureLinkDto(MeasureLink measureLink) {
		MeasureLinkDto dto = new MeasureLinkDto(
				measureLink.getGoods() != null ? measureLink.getGoods().getId() : null, 
				measureLink.getMeasureSrc() != null ? measureLink.getMeasureSrc().getId() : null, 
				measureLink.getMeasureDst() != null ? measureLink.getMeasureDst().getId() : null, 
				measureLink.getSrcValue(), 
				measureLink.getDstValue());
		return dto;
	}

	@Override
	public Collection<MeasureLinkDto> buildMeasureLinkDtoCollection(
			Collection<MeasureLink> measureLinks) throws CreateDtoException {
		try{
			return measureLinks.stream()
					.map((measureLink) -> buildMeasureLinkDto(measureLink))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build MeasureLinkDto collection exception. ", e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public GoodsGroupDto buildGoodsGroupDto(GoodsGroup group) {
		GoodsGroupDto dto = new GoodsGroupDto(group.getId(), 
				group.getOuterId(), 
				group.getName(), 
				group.getParentId());
		return dto;
	}

	@Override
	public Collection<GoodsGroupDto> buildGoodsGroupDtoCollection(
			Collection<GoodsGroup> groups) throws CreateDtoException {
		try{
			return groups.stream()
					.map((group) -> buildGoodsGroupDto(group))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build GoodsGroupDto collection exception. ", e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public GoodsDto buildGoodsDto(Goods goods) {
		GoodsDto dto = new GoodsDto();
		dto.fName = goods.getfName();
		dto.goodsData = goods.getGoodsDataAsString();
		dto.goodsGroupId = 
				goods.getGoodsGroup() !=null ? goods.getGoodsGroup().getId() : null;
		dto.id = goods.getId();
		dto.isActive = goods.getIsActive();
		dto.measureId = 
				goods.getMeasure() != null ? goods.getMeasure().getId() : null;
		dto.name = goods.getName();
		dto.outerId = goods.getOuterId();
		dto.priorityColorId = 
				goods.getPriorityColor() != null ? goods.getPriorityColor().getId() : null;
		dto.sortPos = goods.getSortPos();
		dto.vatCoef = goods.getVatCoef();
		dto.weight = goods.getWeight();
		return dto;
	}

	@Override
	public Collection<GoodsDto> buildGoodsDtoCollection(Collection<Goods> goods)
			throws CreateDtoException {
		try{
			return goods.stream()
					.map((g) -> buildGoodsDto(g))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build GoodsDto collection exception. ", e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public ClientGroupDto buildClientGroupDto(ClientGroup clientGroup) {
		ClientGroupDto dto = new ClientGroupDto(clientGroup.getId(), 
				clientGroup.getName(), 
				clientGroup.getOuterId());
		return dto;
	}

	@Override
	public Collection<ClientGroupDto> buildClientGroupDtoCollection(
			Collection<ClientGroup> clientGroups) throws CreateDtoException {
		try{
			return clientGroups.stream()
					.map((g) -> buildClientGroupDto(g))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build ClientGroupDto collection exception. ", e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public ClientDto buildClientDto(Client client) {
		ClientDto dto = new ClientDto();
		dto.addressLaw = client.getAddressLaw();
		dto.addressLocation = client.getAddressLocation();
		dto.bankAccount = client.getBankAccount();
		dto.bankName = client.getBankName();
		dto.defaultDiscount = client.getDefaultDiscount();
		dto.dogNum = client.getDogNum();
		dto.fName = client.getFName();
		dto.groupId = client.getGroup() != null ? client.getGroup().getId() : null;
		dto.id = client.getId();
		dto.isNotActive = client.getIsNotActive();
		dto.latitude = client.getLatitude();
		dto.longitude = client.getLongitude();
		dto.mfo = client.getMfo();
		dto.okpo = client.getOkpo();
		dto.outerId = client.getOuterId();
		dto.phone = client.getPhone();
		dto.routeId = client.getRoute() != null ? client.getRoute().getId() : null;
		dto.stopShipment = client.getStopShipment();
		dto.taxCode = client.getTaxCode();
		dto.taxNum = client.getTaxNum();
		dto.name = client.getName();
		return dto;
	}

	@Override
	public Collection<ClientDto> buildClientDtoCollection(
			Collection<Client> clients) throws CreateDtoException {
		try{
			return clients.stream()
					.map((g) -> buildClientDto(g))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build ClientDto collection exception. ", e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public AgentGoodsDto buildAgentGoodsDto(AgentGoods goods) {
		AgentGoodsDto dto = new AgentGoodsDto(
				buildGoodsDto(goods.getGoods()), 
				goods.getPrice(), 
				goods.getAvailable());
		return dto;
	}

	@Override
	public Collection<AgentGoodsDto> buildAgentGoodsDtoCollection(
			Collection<AgentGoods> goods) throws CreateDtoException {
		try{
			return goods.stream()
					.map((g) -> buildAgentGoodsDto(g))
					.collect(Collectors.toList());
		}catch(Exception e){
			throw new CreateDtoException("Build AgentGoodsDto collection exception. ", e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Location buildLocation(LocationDto locationDto) {
		Location location = new Location();
		location.setAgent(agentDao.getAgentById(locationDto.agentId));
		location.setAccuracy(locationDto.accuracy);
		location.setBattery(locationDto.battery);
		location.setCreationDate(new DateTime(locationDto.creationDate * 1000));
		location.setLatitude(locationDto.latitude);
		location.setLongitude(locationDto.longitude);
		location.setProvider(locationDto.provider);
		location.setSavestate(
				locationSaveStateDao.getStateById(locationDto.savestate));
		location.setSearchtime(locationDto.searchtime);
		return location;
	}

	@Override
	public Optional<Document> biuldDocument(DocumentDto docDto) {
		final Document document = new Document();
		try{
			document.setAgent(agentDao.getAgentById(docDto.agentId));
			document.setClient(clientDao.getClietnById(docDto.clientId));
			document.setDescript(docDto.descript);
			document.setDiscount(docDto.discount);
			document.setDocDate(new DateTime(docDto.docDate * 1000));
			document.setDocType(docTypeDao.getDocTypeById(docDto.docTypeId));
			document.setItemCount(docDto.itemCount);
			document.setLatitude(docDto.latitude);
			document.setLongitude(docDto.longitude);
			document.setLinkId(docDto.linkId);
			document.setRegNum(docDto.regNum);
			document.setSaleType(docDto.saleType);
			document.setSendingDate(new DateTime()); //current date
			document.setSumWithoutVat(docDto.sumWithoutVat);
			document.setSumWithVat(docDto.sumWithVat);
			document.setTermDate(new DateTime(docDto.termDate * 1000));
			
			docDto.details.forEach((detailDto) -> {
				document.addDetail(buildDocumentDetail(detailDto)
						.orElseThrow(NullPointerException::new));
			});
		}catch(Exception e){
			logger.error(String.format("Error when building document on agentId: %d.", 
					docDto.agentId), e);
			return Optional.empty();
		}
		return Optional.ofNullable(document);
	}

	@Override
	public Optional<DocumentDetail> buildDocumentDetail(
			DocumentDetailDto detailDto) {
		DocumentDetail detail = new DocumentDetail();
		try{
			detail.setGoods(goodsDao.getGoodsById(detailDto.goodsId));
			detail.setItemcount(detailDto.itemcount);
			detail.setPriceWithoutVat(detailDto.priceWithoutVat);
			detail.setPriceWithVat(detailDto.priceWithVat);
		}catch(Exception e){
			detail = null;
			logger.error(String.format("Error when building document detail."), e);
		}
		return Optional.ofNullable(detail);
	}

}
