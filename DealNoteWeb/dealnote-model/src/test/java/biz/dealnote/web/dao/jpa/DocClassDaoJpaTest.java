package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.DocClassDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.DocClass;

public class DocClassDaoJpaTest extends AbstractDaoJpaTest{
	
	private static final Integer TEST_DOC_CLASS_ID = 1;
	private static final String NEW_CLASS_NAME = "Test name";
	
	@Autowired
	private DocClassDao docClassDao;
	
	@Test
	public void testGetDocClasses() {
		assertEquals(2, docClassDao.getDocClasses().size());
	}

	@Test
	public void testGetDocClassById() {
		assertNotNull(docClassDao.getDocClassById(TEST_DOC_CLASS_ID).get());
	}

	@Test
	public void testSave() {
		DocClass docClass = DefaultObjectsFactory.createDefaultDocClass(null);
		docClassDao.save(docClass);
		assertNotNull("Object wasn't saved. Id isn't created.", docClass.getId());
		
		docClass.setName(NEW_CLASS_NAME);
		docClassDao.save(docClass);
		
		docClass = docClassDao.getDocClassById(docClass.getId()).get();
		assertEquals("Object wasn't updated.", NEW_CLASS_NAME, docClass.getName());
	}

	@Test
	public void testDelete() {
		int size = docClassDao.getDocClasses().size();
		DocClass docClass = DefaultObjectsFactory.createDefaultDocClass(null);
		docClassDao.save(docClass);
		assertTrue("Object wasn't added.", 
				size < docClassDao.getDocClasses().size());
		
		docClassDao.delete(docClass);
		assertTrue("Object wasn't removed.", 
				size == docClassDao.getDocClasses().size());
	}

}
