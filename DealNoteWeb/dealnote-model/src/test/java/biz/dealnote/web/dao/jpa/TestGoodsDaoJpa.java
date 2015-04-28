package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestGoodsDaoJpa {

	private static final Integer TEST_GOODS_ID = 3;
	private static final String TEST_GOODS_NAME = "test name";
	private static final Integer TEST_GOODS_MEASURE_ID = 1;
	private static final Integer TEST_AGENT_ID = 444;
	
    @Autowired
	private GoodsDao goodsDao;
    
    @Autowired
    private MeasureDao measureDao;
    
    @Autowired
    private AgentDAO agentDao;
	
	@Test
	@Transactional
	public void testGetAllGoods() {
		assertEquals(5, goodsDao.getAllGoods().size());
	}

	@Test
	@Transactional
	public void testGetGoodsById() {
		assertNotNull(goodsDao.getGoodsById(TEST_GOODS_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		Goods goods = createTestGoods();
		goodsDao.save(goods);
		assertNotNull("Object wasn't saved. Id isn't created.", goods.getId());
		
		goods.setName(TEST_GOODS_NAME);
		goodsDao.save(goods);
		
		goods = goodsDao.getGoodsById(goods.getId());
		assertEquals("Object wasn't updated.", TEST_GOODS_NAME, goods.getName());
		
		goodsDao.delete(goods);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = goodsDao.getAllGoods().size();
		Goods goods = createTestGoods();
		goodsDao.save(goods);
		assertTrue("Object wasn't added.", 
				size < goodsDao.getAllGoods().size());
		
		goodsDao.delete(goods);
		assertTrue("Object wasn't removed.", 
				size == goodsDao.getAllGoods().size());
	}

	@Test
	@Transactional
	public void testGetGoodsByAgent() {
		Agent agent = agentDao.getAgentById(TEST_AGENT_ID);
		assertNotNull(String.format("Haven't found agent with ID: %d", TEST_AGENT_ID), agent);
		
		assertEquals(3, goodsDao.getGoodsByAgent(agent).size());
	}

	public Goods createTestGoods(){
		Goods goods = new Goods();
		goods.setName(TEST_GOODS_NAME);
		goods.setfName(TEST_GOODS_NAME);
		goods.setMeasure(measureDao.getMeasureById(TEST_GOODS_MEASURE_ID));
		return goods;
	}
}
