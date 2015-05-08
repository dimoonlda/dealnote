package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import biz.dealnote.web.dao.AgentDAO;
import biz.dealnote.web.dao.GoodsDao;
import biz.dealnote.web.dao.MeasureDao;
import biz.dealnote.web.dao.MeasureLinkDao;
import biz.dealnote.web.model.Agent;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Goods;
import biz.dealnote.web.model.Measure;
import biz.dealnote.web.model.MeasureLink;

public class TestMeasureLinkDao extends AbstractDaoJpaTest{

	private static final int TEST_MEASURE_LINK_ID = 2;
	private static final int TEST_GOODS_ID = 3;
	private static final int TEST_MEASURE_ID = 3;
	private static final Double NEW_SRC_VALUE = 100.0;
	
	@Autowired
	private MeasureLinkDao measureLinkDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private AgentDAO agentDAO;
	
	@Autowired
	private MeasureDao measureDao;
	
	private Measure measure;
	private Agent agent;
	private Goods goods;
	
	@Before
	public void beforeInit(){
		goods = goodsDao.getGoodsById(TEST_GOODS_ID);
		assertNotNull(goods);
		agent = agentDAO.getAgentById(DefaultObjectsFactory.DEFAULT_AGENT_ID);
		assertNotNull(agent);
		measure = measureDao.getMeasureById(TEST_MEASURE_ID);
		assertNotNull(measure);
	}
	
	@Test
	public void testGetAllMeasureLinks(){
		assertEquals(11, measureLinkDao.getAllMeasureLinks().size());
	}
	
	@Test
	public void testGetMeasureLinkByGoods(){
		Collection<MeasureLink> result = measureLinkDao.getMeasureLinkByGoods(goods);
		
		assertNotNull(result);
		assertEquals(4, result.size());
	}
	
	@Test
	public void testGetMeasureLinkById(){
		assertNotNull(measureLinkDao
				.getMeasureLinkById(TEST_MEASURE_LINK_ID));
	}
	
	@Test
	public void testGetMeasureLinkByAgent(){
		Collection<MeasureLink> result = measureLinkDao.getMeasureLinkByAgent(agent);
		
		assertNotNull(result);
		assertEquals(11, result.size());
	}
	
	@Test
	public void testSave(){
		MeasureLink link = DefaultObjectsFactory
				.createDefaultMeasureLink(null, goods, measure, measure);
		measureLinkDao.save(link);
		assertNotNull(link.getId());
		
		link.setSrcValue(TestMeasureLinkDao.NEW_SRC_VALUE);
		measureLinkDao.save(link);
		
		link = measureLinkDao.getMeasureLinkById(link.getId());
		assertEquals(TestMeasureLinkDao.NEW_SRC_VALUE, link.getSrcValue());
		
		measureLinkDao.delete(link.getId());
	}
	
	@Test
	public void testDelete(){
		int size = measureLinkDao.getAllMeasureLinks().size();
		MeasureLink link = DefaultObjectsFactory
				.createDefaultMeasureLink(null, goods, measure, measure);
		measureLinkDao.save(link);
		assertTrue(size < measureLinkDao.getAllMeasureLinks().size());
		
		measureLinkDao.delete(link.getId());
		assertTrue(size == measureLinkDao.getAllMeasureLinks().size());
	}
}
