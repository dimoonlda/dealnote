package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.AgentGoodsDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.AgentGoods;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Goods;

public class TestAgentGoodsDaoJpa extends AbstractDaoJpaTest{

	private static final Integer TEST_AGENT_GOODS_ID = 8;
	private static final Integer TEST_GOODS_ID = 6;
	private static final Double NEW_AGENT_GOODS_PRICE = 60.0;
	
	@Autowired
	private AgentGoodsDao agentGoodsDao;
	@Autowired
	private AgentDAO agentDao;
	@Autowired
	private GoodsDao goodsDao;
	
	private Agent agent;
	private Goods goods;
	
	private void init(){
		agent = agentDao.getAgentById(DefaultObjectsFactory.DEFAULT_AGENT_ID);
		assertNotNull(agent);
		goods = goodsDao.getGoodsById(TEST_GOODS_ID);
		assertNotNull(goods);
	}
	
	@Test
	public void testGetAllAgentGoods() {
		assertEquals(3, agentGoodsDao
				.getAllAgentGoods().size());
	}

	@Test
	public void testGetAgentGoodsById() {
		assertNotNull(agentGoodsDao.getAgentGoodsById(TEST_AGENT_GOODS_ID));
	}

	@Test
	public void testSave() {
		init();
		AgentGoods agentGoods = DefaultObjectsFactory
				.createDefaultAgentGoods(null, agent, goods);
		agentGoodsDao.save(agentGoods);
		assertNotNull("Object wasn't saved. Id isn't created.", agentGoods.getId());
		
		agentGoods.setPrice(NEW_AGENT_GOODS_PRICE);
		agentGoodsDao.save(agentGoods);
		
		agentGoods = agentGoodsDao.getAgentGoodsById(agentGoods.getId());
		assertEquals("Object wasn't updated.", NEW_AGENT_GOODS_PRICE, agentGoods.getPrice());
	}

	@Test
	public void testDelete() {
		init();
		int size = agentGoodsDao.getAllAgentGoods().size();
		AgentGoods agentGoods = DefaultObjectsFactory
				.createDefaultAgentGoods(null, agent, goods);
		agentGoodsDao.save(agentGoods);
		assertTrue("Object wasn't added.", 
				size < agentGoodsDao.getAllAgentGoods().size());
		
		agentGoodsDao.delete(agentGoods);
		assertTrue("Object wasn't removed.", 
				size == agentGoodsDao.getAllAgentGoods().size());
	}

	@Test
	public void testGetAgentGoodsByAgent() {
		init();
		assertEquals(3, agentGoodsDao
				.getAgentGoodsByAgent(agent).size());
	}
}
