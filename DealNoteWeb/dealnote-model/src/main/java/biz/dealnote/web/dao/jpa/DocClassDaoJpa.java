package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.DocClassDao;
import biz.dealnote.web.model.DocClass;

@Repository
public class DocClassDaoJpa implements DocClassDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<DocClass> getDocClasses() {
		return em.createQuery("from DocClass c", DocClass.class)
				.getResultList();
	}

	@Override
	public Optional<DocClass> getDocClassById(int id) {
		return Optional.ofNullable(em.createQuery("from DocClass c where c.id = :id", DocClass.class)
				.setParameter("id", id)
				.getSingleResult());
	}

	@Override
	public void save(DocClass docClass) {
		if(docClass.isNew()){
			em.persist(docClass);
		}else{
			em.merge(docClass);
		}
	}

	@Override
	public void delete(DocClass docClass) {
		em.remove(docClass);
	}

}
