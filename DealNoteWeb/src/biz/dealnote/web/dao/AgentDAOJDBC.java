package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biz.dealnote.web.beans.Agent;

public class AgentDAOJDBC implements AgentDAO {
	
	public static final String colAgent_ID = "ID";
	public static final String colAgent_SNAME = "SNAME";
	public static final String colAgent_ROLECODE = "ROLECODE";
	public static final String colAgent_SUPERVISORID = "SUPERVISORID";
	public static final String colAgent_USERID = "USERID";
	
	private static final String SQL_LIST_ACTIVE_AGENTS = "SELECT * FROM AGENTS WHERE ISACTIVE<>0";
	private DAOFactory daoFactory;
	
	public AgentDAOJDBC(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Agent> getActiveAgentsList() throws DAOException {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rsAgents = null;
		List<Agent> agentsData = new ArrayList<Agent>();
		try {
			conn = daoFactory.getConnection();
			preparedStatement = conn.prepareStatement(SQL_LIST_ACTIVE_AGENTS);
			rsAgents = preparedStatement.executeQuery();
			while(rsAgents.next()){
				Agent agent = new Agent();
				agent.setId(rsAgents.getInt(colAgent_ID));
				agent.setName(rsAgents.getString(colAgent_SNAME));
				agentsData.add(agent);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.close(conn, preparedStatement, rsAgents);
		}
		return agentsData;
	}

}
