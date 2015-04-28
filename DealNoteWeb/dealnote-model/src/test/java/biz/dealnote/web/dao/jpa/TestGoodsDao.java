package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestGoodsDao {

	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private AgentDAO agentDao;
	
	@Test
	@Transactional
	public void testGetGoodsByAgent(){
		Agent agent = agentDao.getAgentById(444);
		Assert.assertNotNull(agent);
		
		Collection<Goods> result = goodsDao.getGoodsByAgent(agent);
		Assert.assertNotNull(result);
		Assert.assertEquals(3, result.size());
	}
}
