package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Document;

@Repository
public class DocumentDaoJpa implements DocumentDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void save(Document document) {
		if(document.getId() == null){
			em.persist(document);
		}else{
			em.merge(document);
		}
	}

	@Override
	public Collection<Document> getDocumentsByAgentForPeriod(
			DateTime dateStart, DateTime dateEnd, Agent agent) {
		return em.createQuery("from Document d where d.agent = :agent "
				+ "and d.docDate >= :dateStart "
				+ "and d.docDate <= :dateEnd", Document.class)
				.setParameter("agent", agent)
				.setParameter("dateStart", dateStart)
				.setParameter("dateEnd", dateEnd)
				.getResultList();
	}

	@Override
	public Document getDocById(Integer id) {
		return em.createQuery("from Document d where d.id = :id", Document.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void delete(Document document) {
		em.remove(document);
	}

	@Override
	public Collection<Document> getDocumentsByAgent(Agent agent) {
		return em.createQuery("from Document d where d.agent = :agent", Document.class)
				.setParameter("agent", agent)
				.getResultList();
	}

}
