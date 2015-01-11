package biz.dealnote.web.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import biz.dealnote.web.beans.Agent;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.utils.SessionUtil;

public class AgentDaoJpa implements AgentDAO {
	private DAOFactory daoFactory;
	
	private Session session;
	
	public AgentDaoJpa(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
		session = SessionUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public List<Agent> getActiveAgentsList() throws DAOException {
		List<Agent> agentsList = (List<Agent>) session
				.createQuery("from Agent agent where agent.active=1").list();

		return agentsList;
	}

	@Override
	public List<Agent> getAgentsList() throws DAOException {
		List<Agent> agentsList = 
				(List<Agent>) session.createQuery("from Agent").list();
		
		return agentsList;
	}

	@Override
	public void deleteAgentById(int agentId) throws DAOException {
		
		Query q = session.createQuery("delete from Agent where id=:id");
		q.setInteger("id", agentId);
		q.executeUpdate();
	}

	@Override
	public Agent getAgentById(int agentId) {
		
		Agent agent = (Agent) session.get(Agent.class, agentId);
		return agent;
	}

	@Override
	public void addAgent(Agent agent) {
		if(agent != null){
			session.save(agent);
		}
	}

	@Override
	public void updateAgent(Agent agent) {
		if(agent != null){
			session.update(agent);
		}		
	}

}
