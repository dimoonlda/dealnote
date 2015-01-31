package biz.dealnote.web.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.DAOException;
import biz.dealnote.web.dao.DAOFactory;
import biz.dealnote.web.dao.DAOUtil;
import biz.dealnote.web.model.Agent;

public class AgentDAOJDBC implements AgentDAO {
	
	public static final String colAgent_ID = "ID";
	public static final String colAgent_SNAME = "SNAME";
	public static final String colAgent_ROLECODE = "ROLECODE";
	public static final String colAgent_SUPERVISORID = "SUPERVISORID";
	public static final String colAgent_USERID = "USERID";
	
	private static final String SQL_LIST_ACTIVE_AGENTS = "SELECT * FROM AGENTS WHERE ISACTIVE<>0";
	private static final String SQL_LIST_ALL_AGENTS = "SELECT * FROM AGENTS";
	private DAOFactory daoFactory;
	
	public AgentDAOJDBC(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Agent> getActiveAgentsList() throws DAOException {
		return getAgentsList(SQL_LIST_ACTIVE_AGENTS);
	}

	@Override
	public List<Agent> getAgentsList() throws DAOException {
		return getAgentsList(SQL_LIST_ALL_AGENTS);
	}

	public List<Agent> getAgentsList(String sql) throws DAOException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rsAgents = null;
		List<Agent> agentsData = new ArrayList<Agent>();
		try {
			conn = daoFactory.getConnection();
			preparedStatement = conn.prepareStatement(sql);
			rsAgents = preparedStatement.executeQuery();
			while(rsAgents.next()){
				Agent agent = new Agent();
				agent.setId(rsAgents.getInt(colAgent_ID));
				agent.setName(rsAgents.getString(colAgent_SNAME));
				agentsData.add(agent);
			}
		} catch (SQLException e) {
			//TODO: Log
			throw new DAOException(e);
		} finally {
			DAOUtil.close(conn, preparedStatement, rsAgents);
		}
		return agentsData;
	}

	@Override
	public void deleteAgentById(int agentId) throws DAOException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Agent getAgentById(int agentId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void save(Agent agent) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
