package biz.dealnote.web.dao;

import java.util.List;

import biz.dealnote.web.beans.Agent;

public interface AgentDAO {
	public List<Agent> getActiveAgentsList() throws DAOException;
	public List<Agent> getAgentsList() throws DAOException;
	public Agent getAgentById(int agentId);
	public void addAgent(Agent agent);
	public void updateAgent(Agent agent);
	public void deleteAgentById(int agentId) throws DAOException;
	
}
