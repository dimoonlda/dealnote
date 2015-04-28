package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.GoodsGroupDao;
import biz.dealnote.web.model.GoodsGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestGoodsGroupDaoJpa {

	private static final Integer TEST_GROUP_ID = 2;
	private static final Integer TEST_GROUP_OUTER_ID = 0;
	private static final Integer TEST_GROUP_PARENT_ID = 0;
	private static final String TEST_GROUP_NAME = "Test name";
	
	@Autowired
	private GoodsGroupDao goodsGroupDao;
	
	@Test
	@Transactional
	public void testGetAllGoodsGroups() {
		assertEquals(3, goodsGroupDao.getAllGoodsGroups().size());
	}

	@Test
	@Transactional
	public void testGetGoodsGroupById() {
		assertNotNull(goodsGroupDao.getGoodsGroupById(TestGoodsGroupDaoJpa.TEST_GROUP_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		GoodsGroup group = createTestGoodsGroup();
		goodsGroupDao.save(group);
		assertNotNull("Object wasn't saved. Id isn't created.", group.getId());
		
		group.setName(TEST_GROUP_NAME);
		goodsGroupDao.save(group);
		
		group = goodsGroupDao.getGoodsGroupById(group.getId());
		assertEquals("Object wasn't updated.", TEST_GROUP_NAME, group.getName());
		
		goodsGroupDao.delete(group);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = goodsGroupDao.getAllGoodsGroups().size();
		GoodsGroup group = createTestGoodsGroup();
		goodsGroupDao.save(group);
		assertTrue("Object wasn't added.", 
				size < goodsGroupDao.getAllGoodsGroups().size());
		
		goodsGroupDao.delete(group);
		assertTrue("Object wasn't removed.", 
				size == goodsGroupDao.getAllGoodsGroups().size());
	}
	
	public GoodsGroup createTestGoodsGroup(){
		GoodsGroup test = new GoodsGroup();
		test.setName(TEST_GROUP_NAME);
		test.setOuterId(TEST_GROUP_OUTER_ID);
		test.setParentId(TEST_GROUP_PARENT_ID);
		return test;
	}

}
