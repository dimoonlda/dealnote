package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.JobDao;
import biz.dealnote.web.model.Job;

@Repository
public class JobDaoJpa implements JobDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Job> getJobs() {
		return em.createQuery("from Job j", Job.class)
				.getResultList();
	}

	@Override
	public Job getJobById(Integer id) {
		return em.createQuery("from Job j where j.id = :id", Job.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(Job job) {
		if(job.isNew()){
			em.persist(job);
		}else{
			em.merge(job);
		}
	}

	@Override
	public void delete(Job job) {
		em.remove(job);
	}

}
