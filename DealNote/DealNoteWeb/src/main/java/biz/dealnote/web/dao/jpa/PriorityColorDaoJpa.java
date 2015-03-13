package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.PriorityColorDao;
import biz.dealnote.web.model.PriorityColor;

@Repository
public class PriorityColorDaoJpa implements PriorityColorDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<PriorityColor> getAllPriorityColors() {
		return em.createQuery("from PriorityColor pc", PriorityColor.class)
				.getResultList();
	}

	@Override
	public PriorityColor getPriorityColorById(int id) {
		return (PriorityColor) em.createQuery("from PriorityColor pc where pc.id=:id")
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(PriorityColor priority) {
		if(priority.getId()==null){
			em.persist(priority);
		}else{
			em.merge(priority);
		}
	}

	@Override
	public void delete(PriorityColor priority) {
		em.remove(priority);
	}

}
