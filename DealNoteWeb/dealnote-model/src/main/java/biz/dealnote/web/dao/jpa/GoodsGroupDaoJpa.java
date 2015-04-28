package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.GoodsGroupDao;
import biz.dealnote.web.model.GoodsGroup;

@Repository
public class GoodsGroupDaoJpa implements GoodsGroupDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<GoodsGroup> getAllGoodsGroups() {
		return em.createQuery("from GoodsGroup gp", GoodsGroup.class)
				.getResultList();
	}

	@Override
	public GoodsGroup getGoodsGroupById(int id) {
		return (GoodsGroup) em.createQuery("from GoodsGroup gp where gp.id=:id")
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(GoodsGroup group) {
		if(group.getId()==null){
			em.persist(group);
		}else{
			em.merge(group);
		}
	}

	@Override
	public void delete(GoodsGroup group) {
		em.remove(group);
	}

}
