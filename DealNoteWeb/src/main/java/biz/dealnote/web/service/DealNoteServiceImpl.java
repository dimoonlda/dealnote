package biz.dealnote.web.service;

import java.util.Collection;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.ClientGroupDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.GoodsGroupDao;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.dao.PriorityColorDao;
import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.model.datatable.AgentJQueryDataTable;
import biz.dealnote.web.model.datatable.ClientsJQueryDataTable;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.GoodsGroupJQueryDataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;
import biz.dealnote.web.model.datatable.LocationJQueryDataTable;
import biz.dealnote.web.model.datatable.MeasureJQueryDataTable;
import biz.dealnote.web.model.datatable.PriorityColorJQueryDataTable;

@Service
public class DealNoteServiceImpl implements DealNoteService{

	private AgentDAO agentDao;
	private ClientDAO clientDao;
	private ClientGroupDao clientGroupDao;
	private RouteDao routeDao;
	private LocationDAO locationDao;
	private GoodsDao goodsDao;
	private GoodsGroupDao goodsGroupDao;
	private MeasureDao measureDao;
	private PriorityColorDao priorityColorDao;
	
	@Autowired
	public DealNoteServiceImpl(AgentDAO agentDao, 
			ClientDAO clientDao,
			ClientGroupDao clientGroupDao, 
			RouteDao routeDao, 
			LocationDAO locationDao,
			GoodsDao goodsDao,
			GoodsGroupDao goodsGroupDao,
			MeasureDao measureDao,
			PriorityColorDao priorityColorDao) {
		this.agentDao = agentDao;
		this.clientDao = clientDao;
		this.clientGroupDao = clientGroupDao;
		this.routeDao = routeDao;
		this.locationDao = locationDao;
		this.goodsDao = goodsDao;
		this.goodsGroupDao = goodsGroupDao;
		this.measureDao = measureDao;
		this.priorityColorDao = priorityColorDao;
	}
	
	@Override
	public Collection<Agent> getActiveAgentsList(){
		return agentDao.getActiveAgentsList();
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Agent> getAgentsList(){
		return agentDao.getAgentsList();
	}

	@Override
	@Transactional(readOnly = true)
	public Agent getAgentById(int agentId) {
		return agentDao.getAgentById(agentId);
	}

	@Override
	@Transactional
	public void saveAgent(Agent agent) {
		agentDao.save(agent);
	}

	@Override
	@Transactional
	public void deleteAgentById(int agentId){
		agentDao.deleteAgentById(agentId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Client> getClientsByAgent(int agentId) {
		return clientDao.getClientsByAgent(agentId);
	}

	@Override
	@Transactional(readOnly = true)
	public Client getClietnById(int clientId) {
		return clientDao.getClietnById(clientId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Route> getRoutes() {
		return routeDao.getRoutes();
	}

	@Override
	@Transactional(readOnly = true)
	public Route getRouteById(int routeId) {
		return routeDao.getRouteById(routeId);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ClientGroup> getGroups() {
		return clientGroupDao.getGroups();
	}

	@Override
	@Transactional(readOnly = true)
	public ClientGroup getGroupById(int groupId) {
		return clientGroupDao.getGroupById(groupId);
	}

	@Override
	@Transactional
	public void saveClient(Client client) {
		clientDao.save(client);
	}

	@Override
	@Transactional
	public void deleteClientById(int clientId) {
		clientDao.deleteById(clientId);
	}

	@Override
	@Transactional(readOnly = false)
	public DataTable getAgentDataTable(JQueryDataTableParamModel params) {
		DataTable dataTable = new AgentJQueryDataTable(agentDao.getAgentsList(), 
				params);
		dataTable.processData();
		return dataTable;
	}

	@Override
	@Transactional(readOnly = false)
	public DataTable getClientDataTable(int agentId,
			JQueryDataTableParamModel params) {
		DataTable dataTable = new ClientsJQueryDataTable(clientDao.getClientsByAgent(agentId), 
				params);
		dataTable.processData();
		return dataTable;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Location> getLocationList(Integer agentID, DateTime byDate) {
		return locationDao.getLocationList(agentID, byDate);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Location> getLocationList(Integer agentID,
			DateTime startDate, DateTime endtDate) {
		return locationDao.getLocationList(agentID, startDate, endtDate);
	}

	@Override
	@Transactional(readOnly = true)
	public DataTable getLocationDataTable(Agent agent, DateTime date,
			JQueryDataTableParamModel params) {
		Integer agentId = agent.getId();
		Collection<Location> locations = locationDao.getLocationList(agentId, date);
		DataTable dataTable = new LocationJQueryDataTable(
				locations, 
				params);
		dataTable.processData();
		return dataTable;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Goods> getAllGoods() {
		return goodsDao.getAllGoods();
	}

	@Override
	@Transactional(readOnly = true)
	public Goods getGoodsById(int id) {
		return goodsDao.getGoodsById(id);
	}

	@Override
	@Transactional
	public void save(Goods goods) {
		goodsDao.save(goods);
	}

	@Override
	@Transactional
	public void delete(Goods goods) {
		goodsDao.delete(goods);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<GoodsGroup> getAllGoodsGroups() {
		return goodsGroupDao.getAllGoodsGroups();
	}

	@Override
	@Transactional(readOnly = true)
	public GoodsGroup getGoodsGroupById(int id) {
		return goodsGroupDao.getGoodsGroupById(id);
	}

	@Override
	@Transactional
	public void save(GoodsGroup group) {
		goodsGroupDao.save(group);
	}

	@Override
	@Transactional
	public void delete(GoodsGroup group) {
		goodsGroupDao.delete(group);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<Measure> getAllMeasure() {
		return measureDao.getAllMeasure();
	}

	@Override
	@Transactional(readOnly = true)
	public Measure getMeasureById(int id) {
		return measureDao.getMeasureById(id);
	}

	@Override
	@Transactional
	public void save(Measure measure) {
		measureDao.save(measure);
	}

	@Override
	@Transactional
	public void delete(Measure measure) {
		measureDao.delete(measure);
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<PriorityColor> getAllPriorityColors() {
		return priorityColorDao.getAllPriorityColors();
	}

	@Override
	@Transactional(readOnly = true)
	public PriorityColor getPriorityColorById(int id) {
		return priorityColorDao.getPriorityColorById(id);
	}

	@Override
	@Transactional
	public void save(PriorityColor priority) {
		priorityColorDao.save(priority);
	}

	@Override
	@Transactional
	public void delete(PriorityColor priority) {
		priorityColorDao.delete(priority);
	}

	@Override
	public DataTable getGoodsGroupDataTable(JQueryDataTableParamModel params) {
		DataTable dataTable = new GoodsGroupJQueryDataTable(goodsGroupDao.getAllGoodsGroups(), 
				params);
		dataTable.processData();
		return dataTable;
	}

	@Override
	public DataTable getMeasureDataTable(JQueryDataTableParamModel params) {
		DataTable dataTable = new MeasureJQueryDataTable(measureDao.getAllMeasure(), 
				params);
		dataTable.processData();
		return dataTable;
	}

	@Override
	public DataTable getPriorityColorDataTable(JQueryDataTableParamModel params) {
		DataTable dataTable = new PriorityColorJQueryDataTable(priorityColorDao.getAllPriorityColors(), 
				params);
		dataTable.processData();
		return dataTable;
	}

	@Override
	public DataTable getGoodsDataTable(JQueryDataTableParamModel params) {
		// TODO Auto-generated method stub
		return null;
	}

}
