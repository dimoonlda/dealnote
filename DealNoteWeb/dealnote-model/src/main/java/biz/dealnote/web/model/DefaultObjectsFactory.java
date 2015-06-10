package biz.dealnote.web.model;

import java.util.List;

import org.joda.time.DateTime;

/**
 * Class for creation default model's objects, for test.
 * @author Lutay D.A.
 *
 */
public final class DefaultObjectsFactory {
	
	public static final String DEFAULT_DESCRIPTION = "Default description";
	public static final String DEFAULT_NAME = "Default name";
	public static final Short DEFAULT_IS_ACTIVE = 1;
	public static final Integer DEFAULT_USER_ID = 100;
	public static final Integer DEFAULT_PAY_FORM_ID = 1;
	public static final Integer DEFAULT_MEASURE_ID = 1;
	public static final Integer DEFAULT_AGENT_ID = 444;
	public static final Integer DEFAULT_GOODS_GROUP_ID = 2;
	public static final Integer DEFAULT_CLIENT_ID = 2;
	public static final Integer DEFAULT_GOODS_ID = 2;
	public static final Integer DEFAULT_DOC_TYPE_ID = 2;
	
	public static PriorityColor createDefaultPriorityColor(Integer id){
		PriorityColor test = new PriorityColor();
		test.setId(id);
		test.setColorCode(DEFAULT_DESCRIPTION);
		test.setDescription("#ffffff");
		return test;
	}
	
	public static User createDefaultUser(Integer id){
		User test = new User();
		test.setId(id);
		test.setActive(DEFAULT_IS_ACTIVE);
		test.setFio("DEFAULT_USER_FIO");
		test.setName("DEFAULT_USER");
		test.setPasswd("DEFAULT_USER_PASSWD");
		return test;
	}
	
	public static PayForm createDefaultPayForm(Integer id){
		PayForm test = new PayForm();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setOuterId(0);
		return test;
	}
	
	public static Measure createDefaultMeasure(Integer id){
		Measure test = new Measure();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setOuterId(0);
		return test;
	}
	
	public static Agent createDefaultAgent(Integer id){
		Agent agent = new Agent();
		agent.setId(id);
		agent.setName(DEFAULT_NAME);
		agent.setActive(1);
		agent.setFio(DEFAULT_NAME);
		agent.setAdminPass("pass");
		agent.setMoneyname("money");
		agent.setMoneyformat("moneyformat");
		agent.setQtyformat("qtyformat");
		agent.setGpsByDay("gpsByDay");
		return agent;
	}
	
	public static Agent createDefaultAgent(Integer id, User user){
		Agent agent = createDefaultAgent(id);
		agent.setUser(user);
		return agent;
	}
	
	public static Location createDefaultLocation(Integer id, Agent agent){
		Location loc = new Location();
		loc.setCreationDate(new DateTime(2013, 8, 20, 0, 0));
		loc.setProvider("Provider");
		loc.setAgent(agent);
		return loc;
	}
	
	public static GoodsGroup createDefaultGoodsGroup(Integer id){
		GoodsGroup test = new GoodsGroup();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setOuterId(0);
		test.setParentId(0);
		return test;
	}
	
	public static Goods createDefaultGoods(Integer id, Measure measure){
		Goods goods = new Goods();
		goods.setId(id);
		goods.setName(DEFAULT_NAME);
		goods.setfName(DEFAULT_NAME);
		goods.setMeasure(measure);
		return goods;
	}
	
	public static MeasureLink createDefaultMeasureLink(Integer id, 
			Goods goods, 
			Measure measureDst, 
			Measure measureSrc){
		MeasureLink test = new MeasureLink();
		test.setId(id);
		test.setGoods(goods);
		test.setMeasureDst(measureDst);
		test.setMeasureSrc(measureSrc);
		test.setDstValue(1.0);
		test.setSrcValue(1.0);
		return test;
	}
	
	public static DocType createDefaultDocType(Integer id){
		DocType test = new DocType();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setPayForm(createDefaultPayForm(DEFAULT_PAY_FORM_ID));
		return test;
	}
	
	public static ClientGroup createDefaultClientGroup(Integer id){
		ClientGroup test = new ClientGroup();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setOuterId(0);
		return test;
	}
	
	public static Client createDefaultClient(Integer id, Agent agent, ClientGroup group){
		Client test = new Client();
		test.setId(id);
		test.setAddressLocation("TEST_CLIENT_ADDRESS_LOCATION");
		test.setAgent(agent);
		test.setGroup(group);
		test.setIsNotActive(0);
		test.setName(DEFAULT_NAME);
		return test;
	}
	
	public static AgentGoods createDefaultAgentGoods(Integer id, Agent agent, Goods goods){
		AgentGoods test = new AgentGoods();
		test.setId(id);
		test.setAgent(agent);
		test.setGoods(goods);
		test.setAvailable(0.0);
		test.setPrice(0.0);
		return test;
	}
	
	public static PartJob createDefaultPartJob(Integer id, 
			Job job, Part part, User user){
		PartJob test = new PartJob();
		test.setId(id);
		test.setJob(job);
		test.setPart(part);
		test.setUser(user);
		test.setIsActive(DEFAULT_IS_ACTIVE);
		return test;
	}
	
	public static Part createDefaultPart(Integer id, Part parent){
		Part test = new Part();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setRoleName("TEST_PART_ROLE_NAME");
		test.setParent(parent);
		return test;
	}
	
	public static Job createDefaultJob(Integer id){
		Job test = new Job();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setRoleName("ROLE_NAME");
		return test;
	}
	
	public static DocumentDetail createDefaultDocumentDetail(Integer id, 
			Goods goods, 
			Document doc){
		DocumentDetail detail = new DocumentDetail();
		detail.setId(id);
		detail.setGoods(goods);
		detail.setItemcount(2.0);
		detail.setDocument(doc);
		return detail;
	}
	public static Document createDefaultDocument(Integer id){
		return createDefaultDocument(id, null, null, null, null, null);
	}
			
	public static Document createDefaultDocument(Integer id, 
			DateTime docDate,
			Agent agent, 
			Client client,
			DocType docType,
			List<DocumentDetail> details){
		Document test = new Document();
		test.setId(id);
		test.setAgent(agent);
		test.setClient(client);
		test.setDocDate(docDate != null ? docDate : DateTime.now());
		test.setDocType(docType);
		test.setSaleType((short)2);
		if(details != null){
			details.forEach(detail -> detail.setDocument(test));
			test.getDetails().addAll(details);
		}
		return test;
	}
	
	public static DeviceSerialNumber createDefaultDeviceSn(Integer id, User user){
		DeviceSerialNumber test = new DeviceSerialNumber();
		test.setId(id);
		test.setSerialNumber("TEST_SERIAL_NUMBER");
		test.setUser(user);
		return test;
	}
	
	public static Route createDefaultRoute(Integer id){
		Route test = new Route();
		test.setId(id);
		test.setName(DEFAULT_NAME);
		test.setOuterId(0);
		return test;
	}
	
	public static WsServer createDefaultWsServer(Integer id){
		WsServer test = new WsServer();
		test.setId(id);
		test.setServerAddress(DEFAULT_NAME);
		test.setDescription(DEFAULT_DESCRIPTION);
		test.setIsDefault(DEFAULT_IS_ACTIVE);
		return test;
	}
	
	public static SystemSets createDefaultSystemSets(Integer id){
		SystemSets sets = new SystemSets();
		sets.setId(id);
		sets.setDbType("dbType");
		sets.setDbVersion("dbVersion");
		sets.setMobileSwapVersion(10);
		sets.setWebServiceAdderess("webServiceAdderess");
		return sets;
	}
	
	public static ServiceClient createDefaultServiceClient(Integer id){
		ServiceClient client = new ServiceClient();
		client.setId(id);
		client.setName(DEFAULT_NAME);
		client.setTypeCode(1);
		client.setUrl("url");
		client.setVersion("version");
		return client;
	}
}
