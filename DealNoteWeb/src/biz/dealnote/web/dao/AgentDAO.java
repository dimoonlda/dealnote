package biz.dealnote.web.dao;

import java.util.List;

import org.dtrader.web.beans.AgentBean;

public interface AgentDAO {
	public List<AgentBean> getActiveAgentsList() throws DAOException;
}
