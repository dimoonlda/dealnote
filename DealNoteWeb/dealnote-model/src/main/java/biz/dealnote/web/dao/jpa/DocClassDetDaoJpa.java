package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.DocClassDetDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DocClassDet;

@Repository
public class DocClassDetDaoJpa implements DocClassDetDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<DocClassDet> getDocClassDets() {
		return em.createQuery("from DocClassDet d", DocClassDet.class)
				.getResultList();
	}

	@Override
	public Optional<DocClassDet> getDocClassDetById(int id) {
		return Optional.ofNullable(
				em.createQuery("from DocClassDet d where d.id = :id", DocClassDet.class)
				.setParameter("id", id)
				.getSingleResult());
	}

	@Override
	public Collection<DocClassDet> getDocClassDetsByAgent(Agent agent) {
		return em.createQuery("from DocClassDet d where d.agent = :agent", DocClassDet.class)
				.setParameter("agent", agent)
				.getResultList();
	}

	@Override
	public void save(DocClassDet docClassDet) {
		if(docClassDet.isNew()){
			em.persist(docClassDet);
		}else{
			em.merge(docClassDet);
		}
	}

	@Override
	public void delete(DocClassDet docClassDet) {
		em.remove(docClassDet);
	}

}
