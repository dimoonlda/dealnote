package biz.dealnote.web.dao;

import java.util.Collection;
import biz.dealnote.web.model.Agent;

public interface AgentDAO {
	public Collection<Agent> getActiveAgentsList();
	public Collection<Agent> getAgentsList();
	public Agent getAgentById(int agentId);
	public void save(Agent agent);
	public void deleteAgentById(int agentId);
	
}
