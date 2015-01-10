package biz.dealnote.web.dao;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public void deleteAgentById(int agentId) throws DAOException {
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		Query q = session.createQuery("delete from Agent where id=:id");
		q.setInteger("id", agentId);
		q.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Agent getAgentById(int agentId) {
		Session session = daoFactory.getSession();
		session.beginTransaction();
		
		Agent agent = (Agent) session.get(Agent.class, agentId);
		
		session.getTransaction().commit();
		session.close();
		return agent;
	}

	@Override
	public void addAgent(Agent agent) {
		if(agent != null){
			Session session = daoFactory.getSession();
			session.beginTransaction();
			
			session.save(agent);
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public void updateAgent(Agent agent) {
		if(agent != null){
			Session session = daoFactory.getSession();
			session.beginTransaction();
			
			session.update(agent);
			session.getTransaction().commit();
			session.close();
		}		
	}

}
