package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Measure;

public class TestMeasureDaoJpa extends AbstractDaoJpaTest{

	private static final String NEW_MEASURE_NAME = "New name";
	
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
		assertNotNull(measureDao.getMeasureById(DefaultObjectsFactory.DEFAULT_MEASURE_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		Measure measure = DefaultObjectsFactory.createDefaultMeasure(null);
		measureDao.save(measure);
		assertNotNull(measure.getId());
		
		measure.setName(NEW_MEASURE_NAME);
		measureDao.save(measure);
		
		measure = measureDao.getMeasureById(measure.getId());
		assertEquals(NEW_MEASURE_NAME, measure.getName());
		
		measureDao.delete(measure);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = measureDao.getAllMeasure().size();
		Measure measure = DefaultObjectsFactory.createDefaultMeasure(null);
		measureDao.save(measure);
		assertTrue(size < measureDao.getAllMeasure().size());
		
		measureDao.delete(measure);
		assertTrue(size == measureDao.getAllMeasure().size());
	}


}
