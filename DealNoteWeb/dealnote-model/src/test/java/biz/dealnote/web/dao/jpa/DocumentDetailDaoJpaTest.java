package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.DocumentDao;
import biz.dealnote.web.dao.DocumentDetailDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Document;
import biz.dealnote.web.model.DocumentDetail;
import biz.dealnote.web.model.Goods;

public class DocumentDetailDaoJpaTest extends AbstractDaoJpaTest{
	
	private static final Integer TEST_DOCUMENT_ID = 1;
	private static final Integer TEST_GOODS_ID = 5;
	private static final Double NEW_ITEM_COUNT = 5.0;
	
	@Autowired
	private DocumentDetailDao documentDetailDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private DocumentDao documentDao;
	
	private Document document;
	private Goods goods;
	
	private void init(){
		document = documentDao.getDocById(TEST_DOCUMENT_ID);
		assertNotNull(document);
		goods = goodsDao.getGoodsById(TEST_GOODS_ID);
		assertNotNull(goods);
	}
	
	@Test
	public void testGetDocumentDetailByDocumentId() {
		assertEquals(3, documentDetailDao
				.getDocumentDetailByDocumentId(TEST_DOCUMENT_ID)
				.size());
	}

	@Test
	public void testSave() {
		init();
		DocumentDetail detail = DefaultObjectsFactory
				.createDefaultDocumentDetail(null, goods, document);
		documentDetailDao.save(detail);
		assertNotNull("Object wasn't saved. Id isn't created.", detail.getId());
		int id = detail.getId();
		detail.setItemcount(NEW_ITEM_COUNT);
		documentDetailDao.save(detail);
		
		detail = documentDetailDao.getDocumentDetailByDocumentId(TEST_DOCUMENT_ID)
				.stream()
				.filter((det) -> det.getId() == id)
				.collect(Collectors.toList())
				.get(0);
		assertEquals("Object wasn't updated.", NEW_ITEM_COUNT, detail.getItemcount());
		
		documentDetailDao.delete(detail);
	}

	@Test
	public void testDelete() {
		init();
		DocumentDetail detail = DefaultObjectsFactory
				.createDefaultDocumentDetail(null, goods, document);
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
}
