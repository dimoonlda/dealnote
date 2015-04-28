package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.model.DocType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestDocTypeDaoJpa {

	@Autowired
	private DocTypeDao docTypeDao;
	
	private static final Integer TEST_TYPE_ID = 1;
	private static final String TEST_TYPE_NAME = "Test name";
	
	@Test
	@Transactional
	public void testGetAllDocTypes() {
		assertEquals(3, docTypeDao.getAllDocTypes().size());
	}

	@Test
	@Transactional
	public void testGetDocTypeById() {
		assertNotNull(docTypeDao.getDocTypeById(TEST_TYPE_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		DocType type = createTestDocType();
		docTypeDao.save(type);
		assertNotNull("Object wasn't saved. Id isn't created.", type.getId());
		
		type.setName(TEST_TYPE_NAME);
		docTypeDao.save(type);
		
		type = docTypeDao.getDocTypeById(type.getId());
		assertEquals("Object wasn't updated.", TEST_TYPE_NAME, type.getName());
		
		docTypeDao.delete(type);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = docTypeDao.getAllDocTypes().size();
		DocType type = createTestDocType();
		docTypeDao.save(type);
		assertTrue("Object wasn't added.", 
				size < docTypeDao.getAllDocTypes().size());
		
		docTypeDao.delete(type);
		assertTrue("Object wasn't removed.", 
				size == docTypeDao.getAllDocTypes().size());
	}
	
	public DocType createTestDocType(){
		DocType test = new DocType();
		test.setName(TEST_TYPE_NAME);
		return test;
	}

}
