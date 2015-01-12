package biz.dealnote.web.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.Session;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.DAOFactory;
import biz.dealnote.web.dao.DocumentDAO;
import biz.dealnote.web.dao.LocationDAO;

public class JdbcDaoFactory extends DAOFactory {
	
	private DataSource dataSource;
	
	public JdbcDaoFactory(DataSource ds) {
		this.dataSource = ds;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	@Override
	public AgentDAO getAgentDAO() {
		return new AgentDAOJDBC(this);
	}

	@Override
	public LocationDAO getLocationDAO() {
		return new LocationDAOJDBC(this);
	}

	@Override
	public DocumentDAO getDocumentDAO() {
		return new DocumentDAOJDBC(this);
	}

	@Override
	public ClientDAO getClientDAO() {
		return new ClientDaoJdbc(this);
	}

	@Override
	public Session getSession() {
		throw new UnsupportedOperationException("You can use this method only with JPA");
	}

}
