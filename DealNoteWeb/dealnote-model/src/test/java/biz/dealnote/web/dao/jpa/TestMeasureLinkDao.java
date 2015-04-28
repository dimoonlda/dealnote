package biz.dealnote.web.dao.jpa;

import java.util.Collection;

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
import biz.dealnote.web.dao.MeasureLinkDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
@ActiveProfiles("test")
public class TestMeasureLinkDao {

	private static final int TEST_MEASURE_LINK_ID = 2;
	private static final int TEST_AGENT_ID = 444;
	private static final int TEST_GOODS_ID = 3;
	private static final int TEST_MEASURE_ID = 3;
	private static final Double TEST_SRC_VALUE = 100.0;
	
	@Autowired
	private MeasureLinkDao measureLinkDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private AgentDAO agentDAO;
	
	@Autowired
	private MeasureDao measureDao;
	
	@Test
	@Transactional
	public void testGetAllMeasureLinks(){
		assertEquals(11, measureLinkDao.getAllMeasureLinks().size());
	}
	
	@Test
	@Transactional
	public void testGetMeasureLinkByGoods(){
		Goods goods = goodsDao.getGoodsById(TestMeasureLinkDao.TEST_GOODS_ID);
		assertNotNull(goods);
		
		Collection<MeasureLink> result = measureLinkDao.getMeasureLinkByGoods(goods);
		
		assertNotNull(result);
		assertEquals(4, result.size());
	}
	
	@Test
	@Transactional
	public void testGetMeasureLinkById(){
		assertNotNull(measureLinkDao
				.getMeasureLinkById(TestMeasureLinkDao.TEST_MEASURE_LINK_ID));
	}
	
	@Test
	@Transactional
	public void testGetMeasureLinkByAgent(){
		Agent agent = agentDAO.getAgentById(TestMeasureLinkDao.TEST_AGENT_ID);
		assertNotNull(agent);
		
		Collection<MeasureLink> result = measureLinkDao.getMeasureLinkByAgent(agent);
		
		assertNotNull(result);
		assertEquals(11, result.size());
	}
	
	@Test
	@Transactional
	public void testSave(){
		MeasureLink link = createTestMeasureLink();
		measureLinkDao.save(link);
		assertNotNull(link.getId());
		
		link.setSrcValue(TestMeasureLinkDao.TEST_SRC_VALUE);
		measureLinkDao.save(link);
		
		link = measureLinkDao.getMeasureLinkById(link.getId());
		assertEquals(TestMeasureLinkDao.TEST_SRC_VALUE, link.getSrcValue());
		
		measureLinkDao.delete(link.getId());
	}
	
	@Test
	@Transactional
	public void testDelete(){
		int size = measureLinkDao.getAllMeasureLinks().size();
		MeasureLink link = createTestMeasureLink();
		measureLinkDao.save(link);
		assertTrue(size < measureLinkDao.getAllMeasureLinks().size());
		
		measureLinkDao.delete(link.getId());
		assertTrue(size == measureLinkDao.getAllMeasureLinks().size());
	}
	
	public MeasureLink createTestMeasureLink(){
		MeasureLink test = new MeasureLink();
		Goods goods = goodsDao.getGoodsById(TestMeasureLinkDao.TEST_GOODS_ID);
		Measure measure = measureDao.getMeasureById(TestMeasureLinkDao.TEST_MEASURE_ID);
		test.setGoods(goods);
		test.setMeasureDst(measure);
		test.setMeasureSrc(measure);
		test.setDstValue(1.0);
		test.setSrcValue(1.0);
		return test;
	}
	
}
