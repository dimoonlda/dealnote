package biz.dealnote.web.dao.jpa;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.model.Location;

@Repository
public class LocationDaoJpa implements LocationDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Location> getLocationList(Integer agentID, DateTime byDate){
		return getLocationList(agentID, byDate, byDate);
	}

	@Override
	public Collection<Location> getLocationList(Integer agentID, DateTime startDate,
			DateTime endDate){
		List<Location> result = null;
		try {
			DateTime nextDate = endDate.plusDays(1);

			Query q = em
					.createQuery("from Location location "
							+ " where location.agent.id=:agentId and "
							+ " location.creationDate >= :beginDate and location.creationDate < :endDate"
							+ " order by location.creationDate");
			q.setParameter("agentId", agentID);
			q.setParameter("beginDate", startDate);
			q.setParameter("endDate", nextDate);
			result = q.getResultList();
		} finally {
			//TODO: Log
		}

		return result;
	}

}
