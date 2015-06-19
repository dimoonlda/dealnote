package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.DocClassDetDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.DocClass;
import biz.dealnote.web.model.DocClassDet;

public class DocClassDetDaoJpaTest extends AbstractDaoJpaTest{

	private static final Integer TEST_DOC_CLASS_DET_ID = 1;
	private static final Integer TEST_AGENT_ID = 444;
	private static final String NEW_PREFIX = "RR";
	
	@Autowired
	private DocClassDetDao docClassDetDao;
	
	@Test
	public void testGetDocClassDets() {
		assertEquals(2, docClassDetDao.getDocClassDets().size());
	}

	@Test
	public void testGetDocClassDetById() {
		assertNotNull(docClassDetDao.getDocClassDetById(TEST_DOC_CLASS_DET_ID).get());
	}

	@Test
	public void testGetDocClassDetsByAgent() {
		Agent agent = DefaultObjectsFactory.createDefaultAgent(TEST_AGENT_ID);
		assertEquals(2, docClassDetDao.getDocClassDetsByAgent(agent).size());
	}

	@Test
	public void testSave() {
		DocClassDet docClassDet = DefaultObjectsFactory.createDefaultDocClassDet(null);
		docClassDetDao.save(docClassDet);
		assertNotNull("Object wasn't saved. Id isn't created.", docClassDet.getId());
		
		docClassDet.setRegNumPrefix(NEW_PREFIX);
		docClassDetDao.save(docClassDet);
		
		docClassDet = docClassDetDao.getDocClassDetById(docClassDet.getId()).get();
		assertEquals("Object wasn't updated.", NEW_PREFIX, docClassDet.getRegNumPrefix());
	}

	@Test
	public void testDelete() {
		int size = docClassDetDao.getDocClassDets().size();
		DocClassDet docClassDet = DefaultObjectsFactory.createDefaultDocClassDet(null);
		docClassDetDao.save(docClassDet);
		assertTrue("Object wasn't added.", 
				size < docClassDetDao.getDocClassDets().size());
		
		docClassDetDao.delete(docClassDet);
		assertTrue("Object wasn't removed.", 
				size == docClassDetDao.getDocClassDets().size());
	}

}
