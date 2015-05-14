package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.GoodsGroupDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.GoodsGroup;

public class TestGoodsGroupDaoJpa extends AbstractDaoJpaTest{

	private static final String NEW_GROUP_NAME = "Test name";
	
	@Autowired
	private GoodsGroupDao goodsGroupDao;
	
	@Test
	public void testGetAllGoodsGroups() {
		assertEquals(3, goodsGroupDao.getAllGoodsGroups().size());
	}

	@Test
	public void testGetGoodsGroupById() {
		assertNotNull(goodsGroupDao.getGoodsGroupById(DefaultObjectsFactory.DEFAULT_GOODS_GROUP_ID));
	}

	@Test
	public void testSave() {
		GoodsGroup group = DefaultObjectsFactory.createDefaultGoodsGroup(null);
		goodsGroupDao.save(group);
		assertNotNull("Object wasn't saved. Id isn't created.", group.getId());
		
		group.setName(NEW_GROUP_NAME);
		goodsGroupDao.save(group);
		
		group = goodsGroupDao.getGoodsGroupById(group.getId());
		assertEquals("Object wasn't updated.", NEW_GROUP_NAME, group.getName());
	}

	@Test
	public void testDelete() {
		int size = goodsGroupDao.getAllGoodsGroups().size();
		GoodsGroup group = DefaultObjectsFactory.createDefaultGoodsGroup(null);
		goodsGroupDao.save(group);
		assertTrue("Object wasn't added.", 
				size < goodsGroupDao.getAllGoodsGroups().size());
		
		goodsGroupDao.delete(group);
		assertTrue("Object wasn't removed.", 
				size == goodsGroupDao.getAllGoodsGroups().size());
	}
}
