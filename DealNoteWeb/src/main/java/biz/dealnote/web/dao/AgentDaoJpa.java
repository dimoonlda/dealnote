package biz.dealnote.web.dao;

import java.util.List;

import org.hibernate.Session;

import biz.dealnote.web.beans.Agent;
import biz.dealnote.web.dao.AgentDAO;

public class AgentDaoJpa implements AgentDAO {
	private DAOFactory daoFactory;
	
	public AgentDaoJpa(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Agent> getActiveAgentsList() throws DAOException {
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		List<Agent> agentsList = (List<Agent>) session
				.createQuery("from Agent agent where agent.active=1").list();
		session.getTransaction().commit();
		session.close();
		
		return agentsList;
	}

	@Override
	public List<Agent> getAgentsList() throws DAOException {
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		List<Agent> agentsList = (List<Agent>) session.createQuery("from Agent").list();
		session.getTransaction().commit();
		session.close();
		
		return agentsList;
	}

}
