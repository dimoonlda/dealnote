package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.model.Measure;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestMeasureDaoJpa {

	private static final Integer TEST_ID = 1;
	private static final String TEST_NAME = "tt";
	private static final Integer TEST_OUTER_ID = 0;
	
	@Autowired
	private MeasureDao measureDao;
	
	@Test
	@Transactional
	public void testGetAllMeasure() {
		assertEquals(15, measureDao.getAllMeasure().size());
	}

	@Test
	@Transactional
	public void testGetMeasureById() {
		assertNotNull(measureDao.getMeasureById(TestMeasureDaoJpa.TEST_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		Measure measure = TestMeasureDaoJpa.createMeasure();
		measureDao.save(measure);
		assertNotNull(measure.getId());
		
		measure.setName(TestMeasureDaoJpa.TEST_NAME);
		measureDao.save(measure);
		
		measure = measureDao.getMeasureById(measure.getId());
		assertEquals(TestMeasureDaoJpa.TEST_NAME, measure.getName());
		
		measureDao.delete(measure);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = measureDao.getAllMeasure().size();
		Measure measure = TestMeasureDaoJpa.createMeasure();
		measureDao.save(measure);
		assertTrue(size < measureDao.getAllMeasure().size());
		
		measureDao.delete(measure);
		assertTrue(size == measureDao.getAllMeasure().size());
	}

	public static Measure createMeasure(){
		Measure test = new Measure();
		test.setName(TestMeasureDaoJpa.TEST_NAME);
		test.setOuterId(TestMeasureDaoJpa.TEST_OUTER_ID);
		return test;
	}
}
