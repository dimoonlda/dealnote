package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.PartDao;
import biz.dealnote.web.model.Part;

@Repository
public class PartDaoJpa implements PartDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Part> getParts() {
		return em.createQuery("from Part p", Part.class)
				.getResultList();
	}

	@Override
	public Part getPartById(Integer id) {
		return em.createQuery("from Part p where p.id = :id", Part.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(Part part) {
		if(part.isNew()){
			em.persist(part);
		}else{
			em.merge(part);
		}
	}

	@Override
	public void delete(Part part) {
		em.remove(part);
	}

}
