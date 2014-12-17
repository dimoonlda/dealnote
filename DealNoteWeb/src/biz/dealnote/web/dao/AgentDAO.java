package biz.dealnote.web.dao;

import java.util.List;

import biz.dealnote.web.beans.Agent;

public interface AgentDAO {
	public List<Agent> getActiveAgentsList() throws DAOException;
}
