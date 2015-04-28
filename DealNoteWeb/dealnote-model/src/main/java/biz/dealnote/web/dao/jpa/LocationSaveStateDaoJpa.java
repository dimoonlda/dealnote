package biz.dealnote.web.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.LocationSaveStateDao;
import biz.dealnote.web.model.LocationSaveState;
@Repository
public class LocationSaveStateDaoJpa implements LocationSaveStateDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public LocationSaveState getStateById(int id) {
		return (LocationSaveState) this.em.createQuery("from LocationSaveState l where l.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

}
