package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.PriorityColorDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.PriorityColor;

public class TestPriorityColorDao extends AbstractDaoJpaTest{

	private static final Integer TEST_ID = 0;
	
	@Autowired
	private PriorityColorDao priorityColorDao;
	
	@Test
	@Transactional
	public void testGetAllPriorityColors() {
		assertEquals(9, priorityColorDao.getAllPriorityColors().size());
	}

	@Test
	@Transactional
	public void testGetPriorityColorById() {
		assertNotNull(priorityColorDao.getPriorityColorById(TestPriorityColorDao.TEST_ID)
				.getDescription());
	}

	@Test
	@Transactional
	public void testSave() {
		PriorityColor color = DefaultObjectsFactory.createDefaultPriorityColor(null);
		priorityColorDao.save(color);
		assertNotNull(color.getId());
		
		color.setDescription("TEST");
		priorityColorDao.save(color);
		
		color = priorityColorDao.getPriorityColorById(color.getId());
		assertEquals("TEST", color.getDescription());
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = priorityColorDao.getAllPriorityColors().size();
		PriorityColor color = DefaultObjectsFactory.createDefaultPriorityColor(null);
		priorityColorDao.save(color);
		assertTrue(size < priorityColorDao.getAllPriorityColors().size());
		
		priorityColorDao.delete(color);
		assertTrue(size == priorityColorDao.getAllPriorityColors().size());
	}

}
