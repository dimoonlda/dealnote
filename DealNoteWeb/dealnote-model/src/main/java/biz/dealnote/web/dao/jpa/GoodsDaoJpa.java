package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Goods;

@Repository
public class GoodsDaoJpa implements GoodsDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Goods> getAllGoods() {
		return em.createQuery("from Goods g", Goods.class)
				.getResultList();
	}

	@Override
	public Goods getGoodsById(int id) {
		return (Goods) em.createQuery("from Goods g where g.id=:id")
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(Goods goods) {
		if(goods.getId()==null){
			em.persist(goods);
		}else{
			em.merge(goods);
		}
	}

	@Override
	public void delete(Goods goods) {
		em.remove(goods);
	}

	@Override
	public Collection<Goods> getGoodsByAgent(Agent agent) {
		return this.em
				.createQuery("select a.goods from AgentGoods a inner join a.goods where a.agent = :agent", Goods.class)
				.setParameter("agent", agent)
				.getResultList();
	}

}
