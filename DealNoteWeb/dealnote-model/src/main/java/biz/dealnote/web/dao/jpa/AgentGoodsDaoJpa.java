package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.AgentGoodsDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;

@Repository
public class AgentGoodsDaoJpa implements AgentGoodsDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<AgentGoods> getAllAgentGoods() {
		return this.em.createQuery("from AgentGoods g", AgentGoods.class)
				.getResultList();
	}

	@Override
	public AgentGoods getAgentGoodsById(int id) {
		return (AgentGoods)this.em.createQuery("from AgentGoods g where g.id=:id")
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(AgentGoods agentGoods) {
		if(agentGoods.isNew()){
			this.em.persist(agentGoods);
		}else{
			this.em.merge(agentGoods);
		}
	}

	@Override
	public void delete(AgentGoods agentGoods) {
		this.em.remove(agentGoods);
	}

	@Override
	public Collection<AgentGoods> getAgentGoodsByAgent(Agent agent) {
		return this.em.createQuery("from AgentGoods g where g.agent = :agent", AgentGoods.class)
				.setParameter("agent", agent)
				.getResultList();
	}

}
