package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.DocumentDetailDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.DocumentDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class DocumentDaoJpaTest {

	private static final Integer TEST_DOCUMENT_ID = 1;
	private static final Integer TEST_AGENT_ID = 444;
	private static final Integer TEST_CLIENT_ID = 2;
	private static final Integer TEST_DOCUMENT_TYPE_ID = 1;
	private static final DateTime TEST_DOCUMENT_DATE = new DateTime(2015, 4, 2, 12, 33);
	private static final Short TEST_DOCUMENT_SELE_TYPE = 2;
	private static final String TEST_DOCUMENT_DESCRIPTION = "Test description";
	private static final Integer TEST_GOODS_ID = 2;
	
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private DocumentDetailDao documentDetailDao;
	@Autowired
	private AgentDAO agentDao;
	@Autowired
	private ClientDAO clientDao;
	@Autowired
	private DocTypeDao docTypeDao;
	@Autowired
	private GoodsDao goodsDao;
	
	@Test
	@Transactional
	public void testSave() {
		Document doc = createTestDocument();
		documentDao.save(doc);
		assertNotNull("Object wasn't saved. Id isn't created.", doc.getId());
		assertNotNull("Document detail wasn't saved. Id isn't created.", doc.getDetails().iterator().next().getId());
		
		doc.setDescript(TEST_DOCUMENT_DESCRIPTION);
		documentDao.save(doc);
		
		doc = documentDao.getDocById(doc.getId());
		assertEquals("Object wasn't updated.", TEST_DOCUMENT_DESCRIPTION, doc.getDescript());
		
		documentDao.delete(doc);
	}

	@Test
	@Transactional
	public void testGetDocumentsByAgentForPeriod() {
		Document doc = createTestDocument();
		documentDao.save(doc);
		assertNotNull("Object wasn't saved. Id isn't created.", doc.getId());
		
		assertEquals(1, documentDao
				.getDocumentsByAgentForPeriod(TEST_DOCUMENT_DATE, 
						TEST_DOCUMENT_DATE, 
						doc.getAgent()).size());
	}

	@Test
	@Transactional
	public void testGetDocumentsByAgent() {
		assertEquals(3, documentDao
				.getDocumentsByAgent(agentDao.getAgentById(TEST_AGENT_ID)).size());
	}
	
	@Test
	@Transactional
	public void testGetDocById() {
		assertNotNull(documentDao.getDocById(TEST_DOCUMENT_ID));
	}

	@Test
	@Transactional
	public void testDelete() {
		Document doc = createTestDocument();
		int size = documentDao.getDocumentsByAgent(
				doc.getAgent()).size();
		documentDao.save(doc);
		assertTrue("Object wasn't added.", 
				size < documentDao.getDocumentsByAgent(
						doc.getAgent()).size());
		
		documentDao.delete(doc);
		assertTrue("Object wasn't removed.", 
				size == documentDao.getDocumentsByAgent(
						doc.getAgent()).size());
	}

	public Document createTestDocument(){
		Document test = new Document();
		test.setAgent(agentDao.getAgentById(TEST_AGENT_ID));
		test.setClient(clientDao.getClietnById(TEST_CLIENT_ID));
		test.setDocDate(TEST_DOCUMENT_DATE);
		test.setDocType(docTypeDao.getDocTypeById(TEST_DOCUMENT_TYPE_ID));
		test.setSaleType(TEST_DOCUMENT_SELE_TYPE);
		DocumentDetail detail = new DocumentDetail();
		detail.setGoods(goodsDao.getGoodsById(TEST_GOODS_ID));
		detail.setItemcount(2.0);
		detail.setDocument(test);
		test.getDetails().add(detail);
		return test;
	}
}
