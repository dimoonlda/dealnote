package biz.dealnote.web.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.SystemSetsDao;
import biz.dealnote.web.model.SystemSets;

@Repository
public class SystemSetsDaoJpa implements SystemSetsDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public SystemSets getSystemSetsById(Integer id) {
		return em.createQuery("from SystemSets s where s.id = :id", SystemSets.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	/**
	 * Table SYSSETS must have only one record with id = 1.
	 */
	public void save(SystemSets sets) {
		sets.setId(1);
		if(getSystemSetsById(1) != null){
			em.merge(sets);
		}else{
			em.persist(sets);
		}
	}

}
