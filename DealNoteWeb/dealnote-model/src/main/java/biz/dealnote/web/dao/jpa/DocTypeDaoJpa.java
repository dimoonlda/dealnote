package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.model.DocType;

@Repository
public class DocTypeDaoJpa implements DocTypeDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<DocType> getAllDocTypes() {
		return em.createQuery("from DocType d", DocType.class)
				.getResultList();
	}

	@Override
	public DocType getDocTypeById(int id) {
		return (DocType)em.createQuery("from DocType d where d.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(DocType docType) {
		if(docType.getId() == null){
			em.persist(docType);
		}else{
			em.merge(docType);
		}
	}

	@Override
	public void delete(DocType docType) {
		em.remove(docType);
	}

}
