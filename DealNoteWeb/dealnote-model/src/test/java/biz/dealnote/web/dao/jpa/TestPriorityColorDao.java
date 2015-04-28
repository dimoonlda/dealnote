package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.PriorityColorDao;
import biz.dealnote.web.model.PriorityColor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestPriorityColorDao {

	private static final String TEST_DESCRIPTION = "Default";
	private static final Integer TEST_ID = 0;
	private static final String TEST_COLOR_CODE = "#ffffff";
	
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
		PriorityColor color = TestPriorityColorDao.createTestPriorityColor();
		priorityColorDao.save(color);
		assertNotNull(color.getId());
		
		color.setDescription("TEST");
		priorityColorDao.save(color);
		
		color = priorityColorDao.getPriorityColorById(color.getId());
		assertEquals("TEST", color.getDescription());
		
		priorityColorDao.delete(color);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = priorityColorDao.getAllPriorityColors().size();
		PriorityColor color = TestPriorityColorDao.createTestPriorityColor();
		priorityColorDao.save(color);
		assertTrue(size < priorityColorDao.getAllPriorityColors().size());
		
		priorityColorDao.delete(color);
		assertTrue(size == priorityColorDao.getAllPriorityColors().size());
	}

	public static PriorityColor createTestPriorityColor(){
		PriorityColor test = new PriorityColor();
		test.setColorCode(TestPriorityColorDao.TEST_COLOR_CODE);
		test.setDescription(TestPriorityColorDao.TEST_DESCRIPTION);
		return test;
	}
}
