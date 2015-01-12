package biz.dealnote.web.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import biz.dealnote.web.beans.Agent;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.DAOException;

public class AgentDaoJpa extends BaseDaoJpa implements AgentDAO {
	
	public AgentDaoJpa(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Agent> getActiveAgentsList() throws DAOException {
		List<Agent> agentsList = (List<Agent>) getSession()
				.createQuery("from Agent agent where agent.active=1").list();

		return agentsList;
	}

	@Override
	public List<Agent> getAgentsList() throws DAOException {
		List<Agent> agentsList = 
				(List<Agent>) getSession().createQuery("from Agent").list();
		
		return agentsList;
	}

	@Override
	public void deleteAgentById(int agentId) throws DAOException {
		
		Query q = getSession().createQuery("delete from Agent where id=:id");
		q.setInteger("id", agentId);
		q.executeUpdate();
	}

	@Override
	public Agent getAgentById(int agentId) {
		
		Agent agent = (Agent) getSession().get(Agent.class, agentId);
		return agent;
	}

	@Override
	public void addAgent(Agent agent) {
		if(agent != null){
			getSession().save(agent);
		}
	}

	@Override
	public void updateAgent(Agent agent) {
		if(agent != null){
			getSession().update(agent);
		}		
	}

}
