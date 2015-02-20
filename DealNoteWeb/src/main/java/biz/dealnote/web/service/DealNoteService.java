package biz.dealnote.web.service;

import java.util.Collection;

import org.joda.time.DateTime;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;
import biz.dealnote.web.model.datatable.DataTable;
import biz.dealnote.web.model.datatable.JQueryDataTableParamModel;

public interface DealNoteService {
	public Collection<Agent> getActiveAgentsList();
	public Collection<Agent> getAgentsList();
	public Agent getAgentById(int agentId);
	public void saveAgent(Agent agent);
	public void deleteAgentById(int agentId);
	public DataTable getAgentDataTable(JQueryDataTableParamModel params);

	public Collection<Client> getClientsByAgent(int agentId);
	public Client getClietnById(int clientId);	
	public void saveClient(Client client);
	public void deleteClientById(int clientId);
	public DataTable getClientDataTable(int agentId, JQueryDataTableParamModel params);
	
	public Collection<Route> getRoutes();
	public Route getRouteById(int routeId);
	
	public Collection<ClientGroup> getGroups();
	public ClientGroup getGroupById(int groupId);
	
	public Collection<Location> getLocationList(Integer agentID, DateTime byDate);
	public Collection<Location> getLocationList(Integer agentID, DateTime startDate, DateTime endtDate);
	public DataTable getLocationDataTable(Agent agent, DateTime date, JQueryDataTableParamModel params);
	
	public Collection<Goods> getAllGoods();
	public Goods getGoodsById(int id);
	public void save(Goods goods);
	public void delete(Goods goods);
	
	public Collection<GoodsGroup> getAllGoodsGroups();
	public GoodsGroup getGoodsGroupById(int id);
	public void save(GoodsGroup group);
	public void delete(GoodsGroup group);
	
	public Collection<Measure> getAllMeasure();
	public Measure getMeasureById(int id);
	public void save(Measure measure);
	public void delete(Measure measure);
	
	public Collection<PriorityColor> getAllPriorityColors();
	public PriorityColor getPriorityColorById(int id);
	public void save(PriorityColor priority);
	public void delete(PriorityColor priority);
	
	public DataTable getGoodsGroupDataTable(JQueryDataTableParamModel params);
	public DataTable getMeasureDataTable(JQueryDataTableParamModel params);
	public DataTable getPriorityColorDataTable(JQueryDataTableParamModel params);
	public DataTable getGoodsDataTable(JQueryDataTableParamModel params);
}
