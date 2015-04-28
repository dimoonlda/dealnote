package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.model.Agent;

@Repository
public class AgentDaoJpa implements AgentDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Agent> getActiveAgentsList(){
		Collection<Agent> agentsList = this.em
				.createQuery("from Agent agent where agent.active=1", Agent.class)
				.getResultList();

		return agentsList;
	}

	@Override
	public Collection<Agent> getAgentsList(){
		Collection<Agent> agentsList = this.em.createQuery("from Agent", Agent.class)
				.getResultList();
		
		return agentsList;
	}

	@Override
	public void deleteAgentById(int agentId){
		
		Query q = this.em.createQuery("delete from Agent where id=:id");
		q.setParameter("id", agentId);
		q.executeUpdate();
	}

	@Override
	public Agent getAgentById(int agentId) {
		
		Agent agent = (Agent) this.em
				.createQuery("from Agent agent where agent.id = :id")
				.setParameter("id", agentId)
				.getSingleResult();
		return agent;
	}

	@Override
	public void save(Agent agent) {
		if(agent.getId() == null){
			this.em.persist(agent);
		} else {
			this.em.merge(agent);
		}
	}

}
