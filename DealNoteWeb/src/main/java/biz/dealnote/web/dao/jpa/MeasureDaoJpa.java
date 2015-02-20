package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.model.Measure;

@Repository
public class MeasureDaoJpa implements MeasureDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Measure> getAllMeasure() {
		return em.createQuery("from Measure m", Measure.class)
				.getResultList();
	}

	@Override
	public Measure getMeasureById(int id) {
		return (Measure) em.createQuery("from Measure m where m.id=:id")
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(Measure measure) {
		if(measure.getId()==null){
			em.persist(measure);
		}else{
			em.merge(measure);
		}
	}

	@Override
	public void delete(Measure measure) {
		em.remove(measure);
	}

}
