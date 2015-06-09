package biz.dealnote.rest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.AgentGoodsDao;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.GoodsGroupDao;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.dao.MeasureLinkDao;
import biz.dealnote.web.dao.PayFormDao;
import biz.dealnote.web.dao.PriorityColorDao;
import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.dao.WsServerDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.DocType;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;
import biz.dealnote.web.model.PayForm;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.model.WsServer;

@Service
public class DealNoteRestServiceImpl implements DealNoteRestService {

	private GoodsGroupDao goodsGroupDao;
	private ClientDAO clientDao;
	private ClientGroupDao clientGroupDao;
	private MeasureDao measureDao;
	private RouteDao routeDao;
	private GoodsDao goodsDao;
	private AgentDAO agentDao;
	private AgentGoodsDao agentGoodsDao;
	private PriorityColorDao priorityColorDao;
	private MeasureLinkDao measureLinkDao;
	private LocationDAO locationDao;
	private DocumentDao documentDao;
	private WsServerDao wsServerDao;
	private DocTypeDao docTypeDao;
	private PayFormDao payFormDao;
	
	
	@Autowired
	public DealNoteRestServiceImpl(GoodsGroupDao goodsGroupDao,
			ClientDAO clientDao, ClientGroupDao clientGroupDao,
			MeasureDao measureDao, RouteDao routeDao, GoodsDao goodsDao,
			AgentDAO agentDao, AgentGoodsDao agentGoodsDao,
			PriorityColorDao priorityColorDao, MeasureLinkDao measureLinkDao,
			LocationDAO locationDao, DocumentDao documentDao, WsServerDao wsServerDao,
			PayFormDao payFormDao, DocTypeDao docTypeDao) {
		this.goodsGroupDao = goodsGroupDao;
		this.clientDao = clientDao;
		this.clientGroupDao = clientGroupDao;
		this.measureDao = measureDao;
		this.routeDao = routeDao;
		this.goodsDao = goodsDao;
		this.agentDao = agentDao;
		this.agentGoodsDao = agentGoodsDao;
		this.priorityColorDao = priorityColorDao;
		this.measureLinkDao = measureLinkDao;
		this.locationDao = locationDao;
		this.documentDao = documentDao;
		this.wsServerDao = wsServerDao;
		this.payFormDao = payFormDao;
		this.docTypeDao = docTypeDao;
	}

	@Transactional(readOnly = true)
	@Override
	public GoodsGroup getGoodsGroupById(int id) {
		return goodsGroupDao.getGoodsGroupById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<GoodsGroup> getAllGoodsGroups() {
		return goodsGroupDao.getAllGoodsGroups();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Client> getClientsByAgentID(int agentId) {
		return clientDao.getClientsByAgent(agentId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ClientGroup> getAllClientGroups() {
		return clientGroupDao.getGroups();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Measure> getAllMeasures() {
		return measureDao.getAllMeasure();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Route> getAllRoutes() {
		return routeDao.getRoutes();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Goods> getGoodsByAgent(Agent agent) {
		return goodsDao.getGoodsByAgent(agent);
	}

	@Override
	@Transactional(readOnly = true)
	public Agent getAgentById(int id) {
		return agentDao.getAgentById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<AgentGoods> getAgentGoodsByAgent(Agent agent) {
		return agentGoodsDao.getAgentGoodsByAgent(agent);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PriorityColor> getAllPriorityColor() {
		return priorityColorDao.getAllPriorityColors();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<MeasureLink> getMeasureLinkByAgent(Agent agent) {
		return measureLinkDao.getMeasureLinkByAgent(agent);
	}

	@Override
	@Transactional
	public void saveLocation(Location location) {
		locationDao.save(location);
	}

	@Override
	@Transactional
	public void saveLocations(Collection<Location> locations) {
		locations.stream().forEach(location -> {
			locationDao.save(location);
		});
		
	}

	@Override
	@Transactional
	public void deleteLocation(Location location) {
		locationDao.delete(location);
	}

	@Override
	@Transactional
	public void save(PriorityColor priority) {
		priorityColorDao.save(priority);
	}

	@Override
	@Transactional
	public void save(Document doc) {
		documentDao.save(doc);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<WsServer> getWsServers() {
		return wsServerDao.getServers();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PayForm> getPayForms() {
		return payFormDao.getAllPayForms();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<DocType> getDocTypes() {
		return docTypeDao.getAllDocTypes();
	}
}
