package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;

import biz.dealnote.web.utils.SessionUtil;

public class JpaDaoFactory extends DAOFactory {

	@Override
	Connection getConnection() throws SQLException {
		throw new UnsupportedOperationException("Use getSession() method for getting JDBC connection.");
	}

	@Override
	Session getSession() {
		return SessionUtil.getSession();
	}

	@Override
	public AgentDAO getAgentDAO() {
		return new AgentDaoJpa(this);
	}

	@Override
	public LocationDAO getLocationDAO() {
		return new LocationDaoJpa(this);
	}

	@Override
	public DocumentDAO getDocumentDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDAO getClientDAO() {
		return new ClientDaoJpa(this);
	}

}
