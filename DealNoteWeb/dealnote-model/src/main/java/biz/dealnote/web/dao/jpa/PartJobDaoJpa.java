package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.PartJobDao;
import biz.dealnote.web.model.PartJob;
import biz.dealnote.web.model.User;

@Repository
public class PartJobDaoJpa implements PartJobDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<PartJob> getPartJobs() {
		return em.createQuery("from PartJob", PartJob.class)
				.getResultList();
	}

	@Override
	public Collection<PartJob> getActivePartJobsByUser(User user) {
		return em.createQuery("from PartJob p where p.isActive = 1 and p.user = :user", 
				PartJob.class)
				.setParameter("user", user)
				.getResultList();
	}

	@Override
	public PartJob getPartJobById(Integer id) {
		return em.createQuery("from PartJob p where p.id = :id", PartJob.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(PartJob partJob) {
		if(partJob.isNew()){
			em.persist(partJob);
		}else{
			em.merge(partJob);
		}
	}

	@Override
	public void delete(PartJob partJob) {
		em.remove(partJob);
	}

}
