package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.DocumentDetailDao;
import biz.dealnote.web.model.DocumentDetail;

@Repository
public class DocumentDetailDaoJpa implements DocumentDetailDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<DocumentDetail> getDocumentDetailByDocumentId(Integer id) {
		return em.createQuery("from DocumentDetail dd where dd.document.id = :id", DocumentDetail.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public void save(DocumentDetail detail) {
		if(detail.getId() == null){
			em.persist(detail);
		}else{
			em.merge(detail);
		}
	}

	@Override
	public void delete(DocumentDetail detail) {
		em.remove(detail);
	}

}
