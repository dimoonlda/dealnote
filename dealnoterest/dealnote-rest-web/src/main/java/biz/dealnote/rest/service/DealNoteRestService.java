package biz.dealnote.rest.service;

import java.util.Collection;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.ClientGroup;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.GoodsGroup;
import biz.dealnote.web.model.Location;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;
import biz.dealnote.web.model.PriorityColor;
import biz.dealnote.web.model.Route;

public interface DealNoteRestService {

	public GoodsGroup getGoodsGroupById(int id);
	public Collection<GoodsGroup> getAllGoodsGroups();
	public Collection<Client> getClientsByAgentID(int agentId);
	public Collection<ClientGroup> getAllClientGroups();
	public Collection<Measure> getAllMeasures();
	public Collection<Route> getAllRoutes();
	public Collection<Goods> getGoodsByAgent(Agent agent);
	public Agent getAgentById(int id);
	public Collection<AgentGoods> getAgentGoodsByAgent(Agent agent);
	public Collection<PriorityColor> getAllPriorityColor();
	public Collection<MeasureLink> getMeasureLinkByAgent(Agent agent);
	public void saveLocation(Location location);
	public void saveLocations(Collection<Location> locations);
	public void deleteLocation(Location location);
	public void save(PriorityColor priority);
	public void save(Document doc);
}
