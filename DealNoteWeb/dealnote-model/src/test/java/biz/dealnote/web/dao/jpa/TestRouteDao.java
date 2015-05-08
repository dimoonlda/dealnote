package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.model.Route;

public class TestRouteDao extends AbstractDaoJpaTest{

	@Autowired
	private RouteDao routeDao;
	
	@Test
	public void testGetRoutes() {
		assertEquals(4, routeDao.getRoutes().size());
	}

	@Test
	public void testGetRouteById() {
		Route route = routeDao.getRouteById(-1);
		assertEquals("All", route.getName());
	}

}
