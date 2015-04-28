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
import biz.dealnote.web.dao.AgentGoodsDao;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.AgentGoods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestAgentGoodsDaoJpa {

	private static final Integer TEST_AGENT_ID = 444;
	private static final Integer TEST_AGENT_GOODS_ID = 8;
	private static final Integer TEST_GOODS_ID = 6;
	private static final Double TEST_AGENT_GOODS_PRICE = 60.0;
	
	@Autowired
	private AgentGoodsDao agentGoodsDao;
	@Autowired
	private AgentDAO agentDao;
	@Autowired
	private GoodsDao goodsDao;
	
	@Test
	@Transactional
	public void testGetAllAgentGoods() {
		assertEquals(3, agentGoodsDao
				.getAllAgentGoods().size());
	}

	@Test
	@Transactional
	public void testGetAgentGoodsById() {
		assertNotNull(agentGoodsDao.getAgentGoodsById(TEST_AGENT_GOODS_ID));
	}

	@Test
	@Transactional
	public void testSave() {
		AgentGoods agentGoods = createTestAgentGoods();
		agentGoodsDao.save(agentGoods);
		assertNotNull("Object wasn't saved. Id isn't created.", agentGoods.getId());
		
		agentGoods.setPrice(TEST_AGENT_GOODS_PRICE);
		agentGoodsDao.save(agentGoods);
		
		agentGoods = agentGoodsDao.getAgentGoodsById(agentGoods.getId());
		assertEquals("Object wasn't updated.", TEST_AGENT_GOODS_PRICE, agentGoods.getPrice());
		
		agentGoodsDao.delete(agentGoods);
	}

	@Test
	@Transactional
	public void testDelete() {
		int size = agentGoodsDao.getAllAgentGoods().size();
		AgentGoods agentGoods = createTestAgentGoods();
		agentGoodsDao.save(agentGoods);
		assertTrue("Object wasn't added.", 
				size < agentGoodsDao.getAllAgentGoods().size());
		
		agentGoodsDao.delete(agentGoods);
		assertTrue("Object wasn't removed.", 
				size == agentGoodsDao.getAllAgentGoods().size());
	}

	@Test
	@Transactional
	public void testGetAgentGoodsByAgent() {
		assertEquals(3, agentGoodsDao
				.getAgentGoodsByAgent(agentDao.getAgentById(TEST_AGENT_ID)).size());
	}

	public AgentGoods createTestAgentGoods(){
		AgentGoods test = new AgentGoods();
		test.setAgent(agentDao.getAgentById(TEST_AGENT_ID));
		test.setGoods(goodsDao.getGoodsById(TEST_GOODS_ID));
		test.setAvailable(0.0);
		test.setPrice(0.0);
		return test;
	}
}
