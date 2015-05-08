package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.PayFormDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.PayForm;

public class TestPayFormDao extends AbstractDaoJpaTest{

	public static final String NEW_PAY_FORM_NAME = "Default name";
	
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
		assertNotNull(payFormDao.getPayFormById(DefaultObjectsFactory.DEFAULT_PAY_FORM_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		PayForm form = DefaultObjectsFactory.createDefaultPayForm(null);
		payFormDao.save(form);
		assertNotNull(form.getId());
		
		form.setName(NEW_PAY_FORM_NAME);
		payFormDao.save(form);
		
		form = payFormDao.getPayFormById(form.getId());
		assertEquals(NEW_PAY_FORM_NAME, form.getName());
		
		payFormDao.delete(form);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = payFormDao.getAllPayForms().size();
		PayForm form = DefaultObjectsFactory.createDefaultPayForm(null);
		payFormDao.save(form);
		assertTrue(size < payFormDao.getAllPayForms().size());
		
		payFormDao.delete(form);
		assertTrue(size == payFormDao.getAllPayForms().size());
	}
}
