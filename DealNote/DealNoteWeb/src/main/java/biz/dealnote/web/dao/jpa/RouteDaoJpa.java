package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.model.Route;

@Repository
public class RouteDaoJpa implements RouteDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<Route> getRoutes() {
		return this.em.createQuery("from Route r")
				.getResultList();
	}

	@Override
	public Route getRouteById(int routeId) {
		Query query = this.em.createQuery("from Route r where r.id=:id");
		query.setParameter("id", routeId);
		return (Route) query.getSingleResult();
	}

}
