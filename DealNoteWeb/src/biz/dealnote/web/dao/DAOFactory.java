package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class DAOFactory {
	
	public static DAOFactory getFactory(String dbContext){
		Context ctx = null;
		Connection conn = null;
		DataSource ds=null;
		DAOFactory instance = null;
		
		if(dbContext != null){
			try{
				ctx = new InitialContext();
				ds = (DataSource)ctx.lookup(dbContext);
				instance = new JdbcDaoFactory(ds);
			}catch(Exception e){
				throw new DAOConfigurationException("Context " + dbContext + " is missing", e);
			}
		}
		
		return instance;
	}
	
	abstract Connection getConnection() throws SQLException;
	
	public abstract AgentDAO getAgentDAO();
	public abstract LocationDAO getLocationDAO();
	public abstract DocumentDAO getDocumentDAO();
	public abstract ClientDAO getClientDAO();
	
}
