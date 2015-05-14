package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.DocType;

public class TestDocTypeDaoJpa extends AbstractDaoJpaTest{

	@Autowired
	private DocTypeDao docTypeDao;
	
	private static final Integer TEST_DOC_TYPE_ID = 1;
	private static final String NEW_TYPE_NAME = "Test name";
	
	@Test
	public void testGetAllDocTypes() {
		assertEquals(3, docTypeDao.getAllDocTypes().size());
	}

	@Test
	public void testGetDocTypeById() {
		assertNotNull(docTypeDao.getDocTypeById(TEST_DOC_TYPE_ID));
	}

	@Test
	public void testSave() {
		DocType type = DefaultObjectsFactory.createDefaultDocType(null);
		docTypeDao.save(type);
		assertNotNull("Object wasn't saved. Id isn't created.", type.getId());
		
		type.setName(NEW_TYPE_NAME);
		docTypeDao.save(type);
		
		type = docTypeDao.getDocTypeById(type.getId());
		assertEquals("Object wasn't updated.", NEW_TYPE_NAME, type.getName());
	}

	@Test
	public void testDelete() {
		int size = docTypeDao.getAllDocTypes().size();
		DocType type = DefaultObjectsFactory.createDefaultDocType(null);
		docTypeDao.save(type);
		assertTrue("Object wasn't added.", 
				size < docTypeDao.getAllDocTypes().size());
		
		docTypeDao.delete(type);
		assertTrue("Object wasn't removed.", 
				size == docTypeDao.getAllDocTypes().size());
	}
}
