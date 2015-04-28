package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;

public interface AgentGoodsDao {
	public Collection<AgentGoods> getAllAgentGoods();
	public AgentGoods getAgentGoodsById(int id);
	public void save(AgentGoods agentGoods);
	public void delete(AgentGoods agentGoods);
	public Collection<AgentGoods> getAgentGoodsByAgent(Agent agent);
}
