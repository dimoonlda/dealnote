package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcDaoFactory extends DAOFactory {
	
	private DataSource dataSource;
	
	public JdbcDaoFactory(DataSource ds) {
		this.dataSource = ds;
	}

	@Override
	Connection getConnection() throws SQLException {
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

}
