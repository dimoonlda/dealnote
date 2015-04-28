package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.DocumentDetailDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.DocumentDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class DocumentDetailDaoJpaTest {
	
	private static final Integer TEST_DOCUMENT_ID = 1;
	private static final Integer TEST_GOODS_ID = 5;
	private static final Double TEST_ITEM_COUNT = 5.0;
	
	@Autowired
	private DocumentDetailDao documentDetailDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private DocumentDao documentDao;
	
	@Test
	@Transactional
	public void testGetDocumentDetailByDocumentId() {
		assertEquals(3, documentDetailDao.getDocumentDetailByDocumentId(TEST_DOCUMENT_ID).size());
	}

	@Test
	@Transactional
	public void testSave() {
		DocumentDetail detail = createTestDocumentDetail();
		documentDetailDao.save(detail);
		assertNotNull("Object wasn't saved. Id isn't created.", detail.getId());
		int id = detail.getId();
		detail.setItemcount(TEST_ITEM_COUNT);
		documentDetailDao.save(detail);
		
		detail = documentDetailDao.getDocumentDetailByDocumentId(TEST_DOCUMENT_ID)
				.stream()
				.filter((det) -> det.getId() == id)
				.collect(Collectors.toList())
				.get(0);
		assertEquals("Object wasn't updated.", TEST_ITEM_COUNT, detail.getItemcount());
		
		documentDetailDao.delete(detail);
	}

	@Test
	@Transactional
	public void testDelete() {
		DocumentDetail detail = createTestDocumentDetail();
		int size = documentDetailDao
				.getDocumentDetailByDocumentId(TEST_DOCUMENT_ID).size();
		documentDetailDao.save(detail);
		assertTrue("Object wasn't added.", 
				size < documentDetailDao
					.getDocumentDetailByDocumentId(TEST_DOCUMENT_ID).size());
		
		documentDetailDao.delete(detail);
		assertTrue("Object wasn't removed.", 
				size == documentDetailDao
					.getDocumentDetailByDocumentId(TEST_DOCUMENT_ID).size());
	}

	public DocumentDetail createTestDocumentDetail(){
		DocumentDetail detail = new DocumentDetail();
		detail.setGoods(goodsDao.getGoodsById(TEST_GOODS_ID));
		detail.setItemcount(2.0);
		detail.setDocument(documentDao.getDocById(TEST_DOCUMENT_ID));
		return detail;
	}
}
