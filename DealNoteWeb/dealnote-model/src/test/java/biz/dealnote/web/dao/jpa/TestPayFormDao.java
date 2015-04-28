package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.PayFormDao;
import biz.dealnote.web.model.PayForm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestPayFormDao {

	private static final String TEST_FORM_NAME = "Test form";
	private static final Integer TEST_FORM_OUTER_ID = 0;
	private static final Integer TEST_FORM_ID = 1;
	
	@Autowired
	private PayFormDao payFormDao;
	
	@Test
	@Transactional
	public void testGetAllPayForms() {
		assertEquals(3, payFormDao.getAllPayForms().size());
	}

	@Test
	@Transactional
	public void testGetPayFormById() {
		assertNotNull(payFormDao.getPayFormById(TestPayFormDao.TEST_FORM_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		PayForm form = TestPayFormDao.createTestPayForm();
		payFormDao.save(form);
		assertNotNull(form.getId());
		
		form.setName(TestPayFormDao.TEST_FORM_NAME);
		payFormDao.save(form);
		
		form = payFormDao.getPayFormById(form.getId());
		assertEquals(TestPayFormDao.TEST_FORM_NAME, form.getName());
		
		payFormDao.delete(form);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = payFormDao.getAllPayForms().size();
		PayForm form = TestPayFormDao.createTestPayForm();
		payFormDao.save(form);
		assertTrue(size < payFormDao.getAllPayForms().size());
		
		payFormDao.delete(form);
		assertTrue(size == payFormDao.getAllPayForms().size());
	}
	
	public static PayForm createTestPayForm(){
		PayForm test = new PayForm();
		test.setName(TestPayFormDao.TEST_FORM_NAME);
		test.setOuterId(TestPayFormDao.TEST_FORM_OUTER_ID);
		return test;
	}

}
