package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.Measure;

public class TestGoodsDaoJpa extends AbstractDaoJpaTest{

	private static final Integer TEST_GOODS_ID = 3;
	private static final String NEW_GOODS_NAME = "test name";
	private static final Integer TEST_GOODS_MEASURE_ID = 1;
	
    @Autowired
	private GoodsDao goodsDao;
    
    @Autowired
    private MeasureDao measureDao;
    
    @Autowired
    private AgentDAO agentDao;
	
    private Measure measure;
    
    @Before
    public void initBefore(){
    	measure = measureDao.getMeasureById(TEST_GOODS_MEASURE_ID);
    	assertNotNull(measure);
    }
    
	@Test
	public void testGetAllGoods() {
		assertEquals(5, goodsDao.getAllGoods().size());
	}

	@Test
	public void testGetGoodsById() {
		assertNotNull(goodsDao.getGoodsById(TEST_GOODS_ID));
	}

	@Test
	public void testSave() {
		Goods goods = DefaultObjectsFactory.createDefaultGoods(null, measure);
		goodsDao.save(goods);
		assertNotNull("Object wasn't saved. Id isn't created.", goods.getId());
		
		goods.setName(NEW_GOODS_NAME);
		goodsDao.save(goods);
		
		goods = goodsDao.getGoodsById(goods.getId());
		assertEquals("Object wasn't updated.", NEW_GOODS_NAME, goods.getName());
		
		goodsDao.delete(goods);
	}

	@Test
	public void testDelete() {
		int size = goodsDao.getAllGoods().size();
		Goods goods = DefaultObjectsFactory.createDefaultGoods(null, measure);
		goodsDao.save(goods);
		assertTrue("Object wasn't added.", 
				size < goodsDao.getAllGoods().size());
		
		goodsDao.delete(goods);
		assertTrue("Object wasn't removed.", 
				size == goodsDao.getAllGoods().size());
	}

	@Test
	public void testGetGoodsByAgent() {
		Agent agent = agentDao.getAgentById(DefaultObjectsFactory.DEFAULT_AGENT_ID);
		assertNotNull(String.format("Haven't found agent with ID: %d", 
				DefaultObjectsFactory.DEFAULT_AGENT_ID), agent);
		
		assertEquals(3, goodsDao.getGoodsByAgent(agent).size());
	}
}
