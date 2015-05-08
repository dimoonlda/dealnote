package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.ClientDAO;
import biz.dealnote.web.dao.DocTypeDao;
import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.DocumentDetailDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Client;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.DocType;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.DocumentDetail;

public class DocumentDaoJpaTest extends AbstractDaoJpaTest{

	private static final Integer TEST_DOCUMENT_ID = 1;
	private static final DateTime TEST_DOCUMENT_DATE = new DateTime(2015, 4, 2, 12, 33);
	private static final String TEST_DOCUMENT_DESCRIPTION = "Test description";
	
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
	
	private Agent agent;
	private Client client;
	private DocType docType;
	private List<DocumentDetail> details = new ArrayList<DocumentDetail>();
	
	private void init(){
		agent = agentDao.getAgentById(DefaultObjectsFactory.DEFAULT_AGENT_ID);
		assertNotNull(agent);
		client = clientDao.getClietnById(DefaultObjectsFactory.DEFAULT_CLIENT_ID);
		assertNotNull(client);
		docType = docTypeDao.getDocTypeById(DefaultObjectsFactory.DEFAULT_DOC_TYPE_ID);
		assertNotNull(docType);
		
		DocumentDetail detail = new DocumentDetail();
		detail.setGoods(goodsDao.getGoodsById(DefaultObjectsFactory.DEFAULT_GOODS_ID));
		detail.setItemcount(2.0);
		details.add(detail);
	}
	
	@Test
	public void testSave() {
		init();
		Document doc = DefaultObjectsFactory.createDefaultDocument(null,
				TEST_DOCUMENT_DATE, agent, client, docType, details);
		documentDao.save(doc);
		assertNotNull("Object wasn't saved. Id isn't created.", doc.getId());
		assertNotNull("Document detail wasn't saved. Id isn't created.", 
				doc.getDetails().iterator().next().getId());
		
		doc.setDescript(TEST_DOCUMENT_DESCRIPTION);
		documentDao.save(doc);
		
		doc = documentDao.getDocById(doc.getId());
		assertEquals("Object wasn't updated.", TEST_DOCUMENT_DESCRIPTION, doc.getDescript());
		
		documentDao.delete(doc);
	}

	@Test
	public void testGetDocumentsByAgentForPeriod() {
		init();
		Document doc = DefaultObjectsFactory.createDefaultDocument(null,
				TEST_DOCUMENT_DATE, agent, client, docType, details);
		documentDao.save(doc);
		assertNotNull("Object wasn't saved. Id isn't created.", doc.getId());
		
		assertEquals(1, documentDao
				.getDocumentsByAgentForPeriod(TEST_DOCUMENT_DATE, 
						TEST_DOCUMENT_DATE, 
						doc.getAgent()).size());
	}

	@Test
	public void testGetDocumentsByAgent() {
		assertEquals(
				3,
				documentDao
						.getDocumentsByAgent(
								agentDao.getAgentById(DefaultObjectsFactory.DEFAULT_AGENT_ID))
						.size());
	}
	
	@Test
	public void testGetDocById() {
		assertNotNull(documentDao.getDocById(TEST_DOCUMENT_ID));
	}

	@Test
	public void testDelete() {
		init();
		Document doc = DefaultObjectsFactory.createDefaultDocument(null,
				TEST_DOCUMENT_DATE, agent, client, docType, details);
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
}
