package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.hibernate.Session;

public abstract class DAOFactory {
	
	/**
	 * Return DAO factory. 
	 * @param connectionType can be 'jpa' or 'jdbc'
	 * @param dbContext the DataSource name
	 * @return
	 */
	public static DAOFactory getFactory(String connectionType, String dbContext){
		Context ctx = null;
		Connection conn = null;
		DataSource ds=null;
		DAOFactory instance = null;
		
		if(connectionType.equals("jdbc") && dbContext != null){
			try{
				ctx = new InitialContext();
				ds = (DataSource)ctx.lookup(dbContext);
				instance = new JdbcDaoFactory(ds);
			}catch(Exception e){
				throw new DAOConfigurationException("Context " + dbContext + " is missing", e);
			}
		}else if(connectionType.equals("jpa")){
			//TODO: Create JPA instance
			instance = new JpaDaoFactory();
		}
		
		return instance;
	}
	/**
	 * Return JDBC connection
	 */
	abstract Connection getConnection() throws SQLException;
	/**
	 * Get Hibernate session
	 * @return
	 */
	abstract Session getSession();
	
	public abstract AgentDAO getAgentDAO();
	public abstract LocationDAO getLocationDAO();
	public abstract DocumentDAO getDocumentDAO();
	public abstract ClientDAO getClientDAO();
	
}
