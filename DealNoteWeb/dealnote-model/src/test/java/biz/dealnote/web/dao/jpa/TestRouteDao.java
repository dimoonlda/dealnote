package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.RouteDao;
import biz.dealnote.web.model.Route;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestRouteDao {

	@Autowired
	private RouteDao routeDao;
	
	@Test
	@Transactional
	public void testGetRoutes() {
		assertEquals(4, routeDao.getRoutes().size());
	}

	@Test
	@Transactional
	public void testGetRouteById() {
		Route route = routeDao.getRouteById(-1);
		assertEquals("All", route.getName());
	}

}
