package biz.dealnote.web.dao.jpa;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.DAOFactory;
import biz.dealnote.web.dao.DocumentDAO;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.utils.SessionUtil;

public class JpaDaoFactory extends DAOFactory {
	
	private SessionFactory sessionFactory;
	
	public JpaDaoFactory(SessionFactory sessionFactory) {
		this.sessionFactory  = sessionFactory;
	}

	@Override
	public Connection getConnection() throws SQLException {
		throw new UnsupportedOperationException("Use getSession() method for getting JDBC connection.");
	}

	@Override
	public Session getSession() {
		return SessionUtil.getSession();
	}

	@Override
	public AgentDAO getAgentDAO() {
		return new AgentDaoJpa(this.sessionFactory);
	}

	@Override
	public LocationDAO getLocationDAO() {
		return new LocationDaoJpa(this.sessionFactory);
	}

	@Override
	public DocumentDAO getDocumentDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDAO getClientDAO() {
		return new ClientDaoJpa(this.sessionFactory);
	}

}
