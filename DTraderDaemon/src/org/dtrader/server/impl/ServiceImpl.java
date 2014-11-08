package org.dtrader.server.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;
import org.dtrader.server.beans.AgentInfo;
import org.dtrader.server.beans.BPOptions;
import org.dtrader.server.beans.Client;
import org.dtrader.server.beans.ClientGroup;
import org.dtrader.server.beans.DebtDoc;
import org.dtrader.server.beans.DocType;
import org.dtrader.server.beans.Document;
import org.dtrader.server.beans.Goods;
import org.dtrader.server.beans.GoodsGroup;
import org.dtrader.server.beans.KeyFile;
import org.dtrader.server.beans.Location;
import org.dtrader.server.beans.LocationWrapper;
import org.dtrader.server.beans.Measure;
import org.dtrader.server.beans.Routes;
import org.dtrader.server.beans.TradeAgent;
import org.dtrader.server.dao.LocationDao;
import org.dtrader.server.dao.LocationDaoJdbc;
import org.dtrader.server.interfaces.Service;
import org.dtrader.server.utils.FtpUploader;
import org.dtrader.server.utils.KmlGenerator;

@WebService(endpointInterface = "org.dtrader.server.interfaces.Service")
public class ServiceImpl implements Service {

	@Resource 
	private static WebServiceContext context;
	
	private static final Logger logger = Logger.getLogger(ServiceImpl.class);
	private ServiceSettings serviceSettings = null;
	
	private Connection conn;

	private void initServiceSettings(){
		ServletContext servletContext = null;
		servletContext = (ServletContext) context.getMessageContext().get(
				MessageContext.SERVLET_CONTEXT);
		serviceSettings = ServiceSettings.getInstance(servletContext);
	}
	
	private String getClientIpAdress(){
	    MessageContext mc = context.getMessageContext();
	    HttpServletRequest req = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
	    return "Client IP: " + req.getRemoteAddr() + "; "; 
	}

	private boolean isSessionTrue(int agentID){
		org.dtrader.server.beans.AgentInfo aInfo = new org.dtrader.server.beans.AgentInfo();
		aInfo.setAgentID(agentID);
		return isSessionTrue(aInfo);
	}
	
	private boolean isSessionTrue(org.dtrader.server.beans.AgentInfo aInfo){
		MessageContext mc = context.getMessageContext();
	    HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
	    if (session == null){
	    	logger.error("AgentID: " + aInfo.getAgentID() + "; Session is null");
	    	return false;
	    }else {
	    	try{
	    		if(!((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).isRequestedSessionIdValid()){
		    		logger.error("AgentID: " + aInfo.getAgentID() 
		    				+ "; Session is invalid!!! Session: " 
		    				+ session.getId());
		    		session.invalidate();
		    		return false;	    			
	    		}
	    		if((Integer)session.getAttribute("agentID") == aInfo.getAgentID()){
	    			logger.info("AgentID: " + session.getAttribute("agentID") + "; Session: " + session.getId());
	    			return true;
	    		}else{
	    			logger.error("AgentID: " + session.getAttribute("agentID") 
	    					+ "; AgentID in request!=agentID in session!!! Session: " 
	    					+ session.getId());
	    			return false;
	    		}
	    	}catch(Exception e){
	    		logger.error("AgentID: " + aInfo.getAgentID() 
	    				+ "; AgentID in session is null!!! Session: " 
	    				+ session.getId());
	    		session.invalidate();
	    		return false;
	    	}
	    }
	}

	public Client[] getClients(int agentID) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		Client[] clientArr = null;
		List<Client> clientList = new ArrayList<Client>();
		try {
			conn = DataBase.getConnection(context);
			String sql = "select * from EPRGETAGENTCLIENT(" + agentID + ")";
			logger.info("AgentID: " + agentID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Client cl = new Client();
				cl.setId(rs.getInt("ID"));
				cl.setAgentId(rs.getInt("AGENTID"));
				cl.setParentId(rs.getInt("PARENTID"));
				cl.setClientTypeId(rs.getInt("CLIENTTYPEID"));
				cl.setName((rs.getString("NAME")));
				cl.setAddressLocation((rs.getString("ADDRESSLOCATION")));
				cl.setPhone((rs.getString("PHONE")));
				cl.setTaxCode((rs.getString("TAXCODE")));
				cl.setTaxNum((rs.getString("TAXNUM")));
				cl.setOkpo((rs.getString("OKPO")));
				cl.setMfo((rs.getString("MFO")));
				cl.setBankName((rs.getString("BANKNAME")));
				cl.setBankAccount((rs.getString("BANKACCOUNT")));
				cl.setDogNum((rs.getString("DOGNUM")));
				cl.setFName((rs.getString("FNAME")));
				cl.setAddressLaw((rs.getString("ADDRESSLAW")));
				cl.setDebtSumm1(rs.getInt("DEBTSUMM1"));
				cl.setDebtDays1(rs.getInt("DEBTDAYS1"));
				cl.setDebtSumm2(rs.getInt("DEBTSUMM2"));
				cl.setDebtDays2(rs.getInt("DEBTDAYS2"));
				cl.setStopShipment(rs.getShort("STOPSHIPMENT"));
				cl.setRouteId(rs.getShort("ROUTEID"));
				cl.setDefaultDiscount(rs.getInt("DEFAULTDISCOUNT"));
				cl.setLongitude(rs.getDouble("LONGITUDE"));
				cl.setLatitude(rs.getDouble("LATITUDE"));
				cl.setOuterId(rs.getInt("OUTERID"));
				clientList.add(cl);
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; " + " Geting clients error!!! " + e);
		}
		clientArr = new Client[clientList.size()];
		for(int j=0; j < clientList.size(); j++){
			clientArr[j] = clientList.get(j);
		}
		logger.info("AgentID: " + agentID + "; " + "Send clients: " + clientArr.length);
		return clientArr;
	}

	@Override
	public Client[] getClients(org.dtrader.server.beans.AgentInfo aInfo) {
		return checkAccess(aInfo) ? getClients(aInfo.getAgentID()) : null;
	}

	public Goods[] getGoods(int agentID) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		Goods[] goodsArr = null;
		List<Goods> goodsList = new ArrayList<Goods>();
		try {
			conn = DataBase.getConnection(context);
			String sql = "select * from SPRGOODSFORAGENT(" + agentID + ")";
			logger.info("AgentID: " + agentID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Goods good = new Goods();
				good.setId(rs.getInt("ID"));
				good.setCategoryID(rs.getInt("CATEGORYID"));
				good.setSortPos(rs.getInt("SORTPOS"));
				good.setName((rs.getString("NAME")));
				good.setCaseSize(rs.getInt("CASESIZE"));
				good.setPrice((rs.getInt("PRICE")));
				good.setAvailable((rs.getInt("AVAILABLE")));
				good.setAssotrment(rs.getInt("ASSOTRMENT"));
				good.setVatType(rs.getInt("VATTYPE"));
				good.setSertificat(rs.getString("SERTIFICAT"));
				good.setEkka(rs.getString("EKKA"));
				good.setDopCod(rs.getString("DOPCOD"));
				good.setfName(rs.getString("FNAME"));
				good.setPrioritetID(rs.getInt("PRIORITETID"));
				good.setClientTypeMask(rs.getInt("CLIENTTYPEMASK"));
				good.setClass1(rs.getString("CLASS1"));
				good.setWeight(rs.getDouble("WEIGHT"));
				good.setColorcode(rs.getString("COLORCODE"));
				good.setMeasureId(rs.getInt("MEASUREID"));
				good.setOuterId(rs.getInt("OUTERID"));
				
				goodsList.add(good);
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; " + " Geting goods error!!! " + e);
		}
		goodsArr = new Goods[goodsList.size()];
		for(int j=0;j<goodsList.size();j++){
			goodsArr[j] = goodsList.get(j);
		}
		logger.info("AgentID: " + agentID + "; " + "Send goods : " + goodsArr.length);
		return goodsArr;
	}

	@Override
	public Goods[] getGoods(org.dtrader.server.beans.AgentInfo aInfo) {
		return checkAccess(aInfo) ? getGoods(aInfo.getAgentID()) : null;
	}

	public GoodsGroup[] getGoodsGroup(int agentID) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		GoodsGroup[] ggArr = null;
		List<GoodsGroup> ggList = new ArrayList<GoodsGroup>();
		try {
			conn = DataBase.getConnection(context);
			String sql = "select * from SPRGETGUDGROUPS";
			logger.info("AgentID: " + agentID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GoodsGroup goodsGroup = new GoodsGroup();
				goodsGroup.setId(rs.getInt("ID"));
				goodsGroup.setParentID(rs.getInt("PARENTID"));
				goodsGroup.setName((rs.getString("NAME")));
				goodsGroup.setGroupidset((rs.getString("GROUPIDSET")));
				ggList.add(goodsGroup);
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; " + " Geting goods group error!!! " + e);
		}
		ggArr = new GoodsGroup[ggList.size()];
		for(int j=0;j<ggList.size();j++){
			ggArr[j] = ggList.get(j);
		}
		logger.info("AgentID: " + agentID + "; " + "Send Goods groups : " + ggArr.length);
		return ggArr;
	}

	@Override
	public GoodsGroup[] getGoodsGroup(org.dtrader.server.beans.AgentInfo aInfo) {
		return checkAccess(aInfo) ? getGoodsGroup(aInfo.getAgentID()) : null;
	}

	public DocType[] getDocTypes(int agentID) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		DocType[] dtArr = null;
		List<DocType> dtList = new ArrayList<DocType>();
		try {
			conn = DataBase.getConnection(context);
			String sql = "select * from doctype";
			logger.info("AgentID: " + agentID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DocType docType = new DocType();
				docType.setId(rs.getInt("ID"));
				docType.setSname(rs.getString("SNAME"));
				docType.setDays(rs.getInt("DAYS"));
				docType.setDiscount(rs.getInt("DISCOUNT"));
				docType.setPayclass(rs.getInt("PAYCLASS"));
				docType.setReppattern(rs.getInt("REPPATTERN"));
				docType.setAccincrnovat(rs.getInt("ACCINCRNOVAT"));
				docType.setAccincrwithvat(rs.getInt("ACCINCRWITHVAT"));
				docType.setPaytypeid(rs.getInt("PAYTYPEID"));
				docType.setDiscountFirst(rs.getString("DISCOUNTFIRST"));
				docType.setVatOverSum(rs.getString("VATOVERSUM"));
				dtList.add(docType);
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; " + " Geting document type error!!! " + e);
		}
		dtArr = new DocType[dtList.size()];
		for(int j=0;j<dtList.size();j++){
			dtArr[j] = dtList.get(j);
		}
		logger.info("AgentID: " + agentID + "; " + "Send document types count : " + dtArr.length);
		return dtArr;
	}

	@Override
	public DocType[] getDocTypes(org.dtrader.server.beans.AgentInfo aInfo) {
		return checkAccess(aInfo) ? getDocTypes(aInfo.getAgentID()) : null;
	}

	public DebtDoc[] getDebtDocs(int agentID) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		DebtDoc[] ddArr = null;
		List<DebtDoc> ddList = new ArrayList<DebtDoc>();
		try {
			conn = DataBase.getConnection(context);
			String sql = "select * from debtdocs where agentid = " + agentID;
			logger.info("AgentID: " + agentID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			while (rs.next()) {
				DebtDoc debtDoc = new DebtDoc();
				debtDoc.setId(rs.getInt("ID"));
				debtDoc.setClientID(rs.getInt("CLIENTID"));
				debtDoc.setPayclass(rs.getInt("PAYCLASS"));
				debtDoc.setDocTypesID(rs.getInt("DOCTYPESID"));
				debtDoc.setRegNumber(rs.getString("REGNUMBER"));
				debtDoc.setDocDate(df.format(rs.getDate("DOCDATE")));
				debtDoc.setPayTypeID(rs.getInt("PAYTYPEID"));
				debtDoc.setPayTypeSName(rs.getString("PAYTYPESNAME"));
				debtDoc.setResponsiblePerson(rs.getString("RESPONSIBLEPERSON"));
				debtDoc.setMainSum(rs.getDouble("MAINSUMM")/100.0);
				debtDoc.setTillPayDays(rs.getInt("TILLPAYDAYS"));
				debtDoc.setPayPercent(rs.getInt("PAYPERCENT"));
				debtDoc.setPayTermDate(df.format(rs.getDate("PAYTERMDATE")));
				debtDoc.setDebt(rs.getDouble("DEBT")/100.0);
				ddList.add(debtDoc);
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; " + " Geting debet docs error!!! " + e);
		}
		ddArr = new DebtDoc[ddList.size()];
		for(int j=0;j<ddList.size();j++){
			ddArr[j] = ddList.get(j);
		}
		logger.info("AgentID: " + agentID + "; " + "Send debt docs : " + ddArr.length);
		return ddArr;
	}

	@Override
	public DebtDoc[] getDebtDocs(org.dtrader.server.beans.AgentInfo aInfo) {
		return checkAccess(aInfo) ? getDebtDocs(aInfo.getAgentID()) : null;
	}

	public BPOptions getBPOptions(int agentID) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		BPOptions bpOpt = null;
		conn = DataBase.getConnection(context);
		try {
			String sql = "select * from EPRGETAGENTOPT(" + agentID + ")";
			logger.info("AgentID: " + agentID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			if(rs.isFirst()){
				bpOpt = new BPOptions();
				bpOpt.setAccdiv(rs.getInt("ACCDIV"));
				bpOpt.setMyclientuid(rs.getInt("MYCLIENTUID"));
				bpOpt.setVatrate(rs.getInt("VATRATE"));
				bpOpt.setStrictstopship(rs.getInt("STRICTSTOPSHIP"));
				bpOpt.setRegnumprefix1(rs.getString("REGNUMPREFIX1"));
				bpOpt.setRegnumnext1(rs.getInt("REGNUMNEXT1"));
				bpOpt.setRegnumprefix2(rs.getString("REGNUMPREFIX2"));
				bpOpt.setRegnumnext2(rs.getInt("REGNUMNEXT2"));
				bpOpt.setVsandps(rs.getInt("VSANDPS"));
				bpOpt.setFio((rs.getString("FIO")));
				bpOpt.setAutoDiscount(rs.getInt("AUTODISCOUNT"));
				bpOpt.setAdmin_pass(rs.getString("ADMIN_PASS"));
				bpOpt.setMoneyname(rs.getString("MONEYNAME"));
				bpOpt.setMoneyformat(rs.getString("MONEYFORMAT"));
				bpOpt.setQtyformat(rs.getString("QTYFORMAT"));
				bpOpt.setDayDelDoc(rs.getInt("DAYDELDOC"));
				bpOpt.setPathToImg(rs.getString("PATHTOIMG"));
				bpOpt.setIsSuperviserGetAllAgents(rs.getInt("ISSUPERVISERGETALLAGENTS"));
				bpOpt.setIsGPS(rs.getInt("ISGPS"));
				bpOpt.setFrequencyGetGPS(rs.getInt("FREQUENCYGETGPS"));
				bpOpt.setFrequencySendGPS(rs.getInt("FREQUENCYSENDGPS"));
				bpOpt.setGpsFromHour(rs.getInt("GPSFROMHOUR"));
				bpOpt.setGpsToHour(rs.getInt("GPSTOHOUR"));
				bpOpt.setGpsByDay(rs.getString("GPSBYDAY"));
				bpOpt.setWsServiceName(rs.getString("WSSERVICENAME"));
				bpOpt.setWsUserName(rs.getString("WSUSERNAME"));
				bpOpt.setWsUserPass(rs.getString("WSUSERPASS"));
				bpOpt.setIsAppStat(rs.getInt("ISAPPSTAT"));
				bpOpt.setIsGPSBeforeOrder(rs.getInt("ISGPSBEFOREORDER"));
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; " + " Geting BP options error!!! " + e);
		}
		logger.info("AgentID: " + agentID + "; " + "Send BP Options: " + (bpOpt != null ? 1 : 0));
		return bpOpt;
	}

	@Override
	public BPOptions getBPOptions(org.dtrader.server.beans.AgentInfo aInfo) {

		return checkAccess(aInfo) ? getBPOptions(aInfo.getAgentID()) : null;
	}

	public int insertDocument(int agentID, Document doc) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		logger.info("AgentID: " + agentID + "; Inserting document... " + doc.toString());
		int newKey = -1;
		try {
			conn = DataBase.getConnection(context);
			String sql = "INSERT INTO DOCUMENT (CLIENTID, " +
					"AGENTID, DOCDATE, DISCOUNT, CASECOUNT, MAINSUMM, DESCRIPT, " +
					"DOCSTATE, PREORVAN, TERMDATE, CTPAYTYPE, SUMWITHOUTVAT, SUMWITHVAT, " +
					"REGNUM, DOCTIME, BEEPRESLINKID, LONGITUDE, LATITUDE) " +
					"VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning ID";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doc.getClientID());
			ps.setInt(2, agentID);
			ps.setString(3, doc.getOrdDate());
			ps.setDouble(4, doc.getDiscount());
			ps.setDouble(5, doc.getCaseCount());
			ps.setDouble(6, doc.getMainSumm());
			ps.setString(7, doc.getDescript());
			ps.setInt(8, doc.getOrdState());
			ps.setInt(9, doc.getPreOrVan());
			ps.setString(10, doc.getTermDate());
			ps.setInt(11, doc.getCtPayType());
			ps.setDouble(12, doc.getSumWithoutVat());
			ps.setDouble(13, doc.getSumWithVat());
			ps.setString(14, doc.getRegNum());
			ps.setString(15, doc.getOrdTime());
			ps.setInt(16, doc.getBeePresLinkID());
			ps.setDouble(17, doc.getLongitude());
			ps.setDouble(18, doc.getLatitude());			
			final ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				newKey = rs.getInt(1);
				rs.close();
				ps.close();
				logger.info("AgentID: " + agentID + "; DocID: " + newKey + "; Inserted document whith LinkID: "
						+ doc.getBeePresLinkID());
				//Вставка деталей документа
				if(insertDocumentDet(agentID, newKey, doc) < 0){
					deleteDoc(newKey);
					newKey = -1;
				}
			} else {
				logger.info("AgentID: " + agentID + "; DocID: " + newKey + "; Filed to insert document whith LinkID: "
						+ doc.getBeePresLinkID());
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; DocID: " + newKey + "; Filed to insert document whith LinkID: "
					+ doc.getBeePresLinkID() + "; " + e);
		}
		return newKey;
	}

	@Override
	public int insertDocument(org.dtrader.server.beans.AgentInfo aInfo, Document doc) {
		return checkAccess(aInfo) ? insertDocument(aInfo.getAgentID(), doc) : -1;
	}
	
	public int insertDocumentDet(int agentID, int docID, Document doc) {
		List<org.dtrader.server.beans.DocumentDet> docDetVec = doc.getDocDetVector();
				
		String sql = "INSERT INTO DOCUMENTDET (" +
				"GOODSID, SHIP, PRICEWITHOUTVAT, PRICEWITHVAT, DOCID) " +
				"VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		int inserted = 0;
		try {
			conn = DataBase.getConnection(context);
			ps = conn.prepareStatement(sql);
			for(org.dtrader.server.beans.DocumentDet docDet : docDetVec){
				logger.info("AgentID: " + agentID + "; DocID: " + docID 
							+ "; Inserting document detail... " + docDet.toString());
				
				ps.setInt(1, docDet.getGoodsID());
				ps.setDouble(2, docDet.getShip());
				ps.setDouble(3, docDet.getPriceWithoutVat());
				ps.setDouble(4, docDet.getPriceWithVat());
				ps.setInt(5, docID);
				final int resCode = ps.executeUpdate();

				if (resCode > 0) {
					logger.info("AgentID: " + agentID + "; Inserted document detail: "
							+ docDet.toString());
					inserted++;
				} else {
					logger.info("AgentID: " + agentID
							+ "; Filed to insert document detail: " + docDet.toString());
				}
			}
			ps.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID
					+ "; Filed to insert document details: " + doc.toString() + "; " + e);
		}
		if (inserted == docDetVec.size())
			return 1;
		else
			return -1;
	}	
	
	public void deleteDoc(int docID){
		try {
			conn = DataBase.getConnection(context);
			String sql = "DELETE FROM DOCUMENT WHERE ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, docID);
			ps.executeUpdate();
			ps.close();
			//conn.close();
		} catch (SQLException e) {
			logger.info("DocID: " + docID + "; Filed to delete document whith docID: "
					+ docID);
			logger.error(e);
		}
	}

	public int insertLocations(int agentID, org.dtrader.server.beans.LocationWrapper locWrapper) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		conn = DataBase.getConnection(context);
		
		List<org.dtrader.server.beans.Location> locVec = locWrapper.getLocVector();
				
		String sql = "execute procedure EPRLOCATIONINSERT( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		int inserted = 0;
		try {
			ps = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			logger.error("AgentID: " + agentID + "; Inserting locations... "
					+ e1.toString());
		}
		for(Location loc : locVec){
			try {
				logger.info("AgentID: " + agentID + "; Inserting locations... "
						+ loc.toString());

				ps.setDouble(1, loc.getLongitude());
				ps.setDouble(2, loc.getLatitude());
				ps.setString(3, loc.getClock());
				ps.setString(4, loc.getProvider());
				ps.setInt(5, loc.getAccuracy());
				ps.setInt(6, loc.getSearchtime());
				ps.setInt(7, loc.getSavestate());
				ps.setInt(8, loc.getCheckins());
				ps.setInt(9, agentID);
				ps.execute();
				logger.info("AgentID: " + agentID + "; Inserted location: "	+ loc.toString());
				inserted++;
			} catch (SQLException e) {
				logger.info("AgentID: " + agentID + "; Filed to insert location: " + loc.toString() + "; " + e);
			}
		}
		try {
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (inserted==locVec.size())
			return 1;
		else
			return -1;
	}
	
	@Override
	public int insertLocations(org.dtrader.server.beans.AgentInfo aInfo, org.dtrader.server.beans.LocationWrapper locWrapper) {
		return checkAccess(aInfo) ? insertLocations(aInfo.getAgentID(), locWrapper) : -1;
	}

	public TradeAgent[] getTradeAgents(int svID) {
		logger.info("AgentID: " + svID + "; " + getClientIpAdress());
		TradeAgent[] agentsArr = null;
		List<TradeAgent> agentsList = new ArrayList<TradeAgent>();
		TradeAgent agent = null;
		conn = DataBase.getConnection(context);
		try {
			String sql;
			if(svID==-1)
				sql = "select * from AGENTS where isactive = 1";
			else
				//TODO: Доделать, если супервайзеру нужны только его агенты
				sql = "select * from AGENTS where isactive = 1";
			logger.info("AgentID: " + svID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				agent = new TradeAgent();
				agent.setId(rs.getInt("ID"));
				agent.setName(rs.getString("SNAME"));
				agentsList.add(agent);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + svID + "; " + " Geting Agents list error!!! " + e);
		}
		agentsArr = new TradeAgent[agentsList.size()];
		for(int j=0;j<agentsList.size();j++){
			agentsArr[j] = agentsList.get(j);
		}
		logger.info("AgentID: " + svID + "; " + "Send Agents: " + agentsArr.length);
		return agentsArr;
	}
	
	@Override
	public TradeAgent[] getTradeAgents(org.dtrader.server.beans.AgentInfo aInfo) {
		return checkAccess(aInfo) ? getTradeAgents(aInfo.getAgentID()) : null;
	}

	@Override
	public String getAgentGPSRoute(org.dtrader.server.beans.AgentInfo aInfo, int agentID, String date) {
		conn = DataBase.getConnection(context);
		Date locDate = null;
		if (!isSessionTrue(aInfo)) {
			return null;
		}
		if (Objects.isNull(date)){
			logger.error("AgentID: " + aInfo.getAgentID() 
					+ "; Date is NULL in attempting to obtain agent GPS route.");
			return null;
		}
		SimpleDateFormat dfLoc = new SimpleDateFormat("dd.MM.yyyy");
		try {
			locDate = dfLoc.parse(date);
		} catch (ParseException e1) {
			logger.error("AgentID: " + aInfo.getAgentID() + "; " + e1);
			return null;
		}
		
		ServletContext servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		String ftpAdress = servletContext.getInitParameter("ftpAdress");
		String ftpLogin = servletContext.getInitParameter("ftpLogin");
		String ftpPassword = servletContext.getInitParameter("ftpPassword");
		String kmlUrlAdress = servletContext.getInitParameter("kmlUrlAdress");
		
		LocationDao locationDao = new LocationDaoJdbc(conn);
		List<Location> agentLocationList = locationDao.getLocationForPeriodByAgentId(locDate, locDate, agentID);
		
		KmlGenerator kml = new KmlGenerator(conn);
		ByteArrayOutputStream out = kml.genKmlToByteArrayOutputStream(agentLocationList, agentID);
		
		FtpUploader ftpUploader = new FtpUploader(out);
		
		Calendar cal = GregorianCalendar.getInstance();
		DateFormat df = new SimpleDateFormat("ddMMyyyy_HHmmss");
		String addStr = df.format(cal.getTime());
		
		String kmlFileName = "agent_" + agentID + "_" + date + "_" + addStr + ".kml";
		String url = null;
		
		if (ftpUploader.uploadToFtp(kmlFileName, ftpAdress, ftpLogin, ftpPassword)){
			url = "https://maps.google.com/maps?q=" 
					+ kmlUrlAdress 
					+ kmlFileName;
		}
		
		logger.info("AgentID: " + aInfo.getAgentID() + "; " + "Sending url: " + url);
		try {
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + aInfo.getAgentID() + "; " + e);
		}
		return url;
	}

	public String checkUpdate(int agentID, int agentApkVer) {
		ServletContext servletContext = null;
		servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		logger.info("AgentID: " + agentID + "; agentApkVer: " + agentApkVer);
		if(agentApkVer < Integer.valueOf(servletContext.getInitParameter("apkVer")).intValue()) {
			logger.info("AgentID: " + agentID + "; Sending apkUrlForUpdate: " 
					+ servletContext.getInitParameter("apkUrlForUpdate"));
			return servletContext.getInitParameter("apkUrlForUpdate");
		}else 
			return "-1";
	}

	@Override
	public String checkUpdate(org.dtrader.server.beans.AgentInfo aInfo, int agentApkVer) {
		initServiceSettings();
		/*if(!serviceSettings.useKeyFile() || !serviceSettings.getKeyFileSN().inKeyFile(aInfo))
			return null;*/
		return checkUpdate(aInfo.getAgentID(), agentApkVer);
	}


	private boolean uploadFile(KeyFile keyFile, String fileName) {
		DataHandler handler = keyFile.getDfile();
		try {
		    InputStream is = handler.getInputStream();

		    OutputStream os = new FileOutputStream(new File(fileName));
		    byte[] b = new byte[100000];
		    int bytesRead = 0;
		    while ((bytesRead = is.read(b)) != -1) {
		    	os.write(b, 0, bytesRead);
		    }
		    os.flush();
		    os.close();
		    is.close();

		} catch (IOException e) {
			logger.error("Upload key file errorr. Filed to save file " + fileName + ". " + e);
		    return false;
		}
		return true;
	}

	@Override
	public boolean uploadKeyFile(org.dtrader.server.beans.AgentInfo aInfo, KeyFile keyFile) {
		if (!isSessionTrue(aInfo)) {
			return false;
		}
		ServletContext servletContext = null;
		servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		String fileName = servletContext.getInitParameter("keyFile");
		if(fileName==null){
			logger.error("Upload key file errorr. Plase set ''keyFile'' parameter in web.xml");
			return false;
		}
		return uploadFile(keyFile, fileName);
	}

	@Override
	public boolean uploadKeyFileSN(org.dtrader.server.beans.AgentInfo aInfo, KeyFile keyFile) {
		if (!isSessionTrue(aInfo)) {
			return false;
		}
		ServletContext servletContext = null;
		servletContext = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
		String fileName = servletContext.getInitParameter("keyFileSN");
		if(fileName==null){
			logger.error("Upload key file errorr. Plase set ''keyFileSN'' parameter in web.xml");
			return false;
		}
		return uploadFile(keyFile, fileName);
	}

	@Override
	public boolean checkKey(org.dtrader.server.beans.AgentInfo aInfo) {
		initServiceSettings();
		if (!isSessionTrue(aInfo)) {
			return false;
		}
		return (!serviceSettings.useKeyFile() 
				|| serviceSettings.getKeyFileSN().inKeyFile(aInfo.getImei()));
	}

	@Override
	public LocationWrapper getAgentGPSPointsByDay(org.dtrader.server.beans.AgentInfo aInfo, int agentID, String byDate) {
		if (!isSessionTrue(aInfo)) {
			return null;
		}
		LocationWrapper locWrap = new LocationWrapper();
		try{
			conn = DataBase.getConnection(context);
			String sql = "select * from LOCATION where AGENTID=? and CLOCK>='" 
					+ byDate+" 00:00:00' and CLOCK<='" 
					+ byDate + " 23:59:59' order by CLOCK";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, agentID);
			ResultSet rs = ps.executeQuery();
			List<Location> locVector = new ArrayList<Location>();
			while(rs.next()){
				Location loc = new Location();
				loc.setAccuracy(rs.getInt("ACCURACY"));
				loc.setCheckins(rs.getInt("CHECKINS"));
				loc.setClock(rs.getString("CLOCK"));
				loc.setLatitude(rs.getDouble("LATITUDE"));
				loc.setLongitude(rs.getDouble("LONGITUDE"));
				loc.setProvider(rs.getString("PROVIDER"));
				loc.setSavestate(rs.getInt("SAVESTATE"));
				loc.setSearchtime(rs.getInt("SEARCHTIME"));
				locVector.add(loc);
			}
			locWrap.setLocVector(locVector);
			ps.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("Error when get agent GPS points: " + e);
		}
		return locWrap;
	}

	@Override
	public Routes[] getRoutes(AgentInfo aInfo) {
		if(!checkAccess(aInfo))
			return null;
		logger.info("AgentID: " + aInfo.getAgentID() + "; " + getClientIpAdress());
		Routes[] routesArr = null;
		List<Routes> routesList = new ArrayList<Routes>();
		Routes route = null;
		try {
			conn = DataBase.getConnection(context);
			String sql;
			sql = "select * from ROUTES";
			logger.info("AgentID: " + aInfo.getAgentID() + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				route = new Routes();
				route.setId(rs.getInt("ID"));
				route.setName(rs.getString("SNAME"));
				routesList.add(route);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + aInfo.getAgentID() + ": " + " Geting Routes list error!!! " + e);
		}
		routesArr = new Routes[routesList.size()];
		for(int j=0;j<routesList.size();j++){
			routesArr[j] = routesList.get(j);
		}
		logger.info("AgentID: " + aInfo.getAgentID() + ": " + "Send Routes: " + routesArr.length);
		return routesArr;

	}

	@Override
	public Boolean checkAgentInfoUpdate(AgentInfo aInfo) {
		initServiceSettings();
		if (!isSessionTrue(aInfo)) {
			return false;
		}
		int resSql = 0;
		boolean needUpdate = false;
		if(!checkKey(aInfo))
			return null;
		logger.info("AgentID: " + aInfo.getAgentID() + "; " + getClientIpAdress());
		try {
			conn = DataBase.getConnection(context);
			String sql;
			sql = "select datediffresult from ISNEEDAGENTUPDATE(?)";
			logger.info("AgentID: " + aInfo.getAgentID() + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, aInfo.getAgentID());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				resSql = rs.getInt("datediffresult");
			}
			if(resSql < 0){
				needUpdate = true;
				ps.executeUpdate("update BPOPTIONS set UPDATEDATETIME=current_timestamp where agentid = " + aInfo.getAgentID());
			}else
				needUpdate = false;
			ps.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + aInfo.getAgentID() + ": " 
					+ " Checking agent info update error!!! " + e);
		}
		logger.info("AgentID: " + aInfo.getAgentID() + ": " 
				+ "Need agent info update: " + String.valueOf(needUpdate));
		return needUpdate;
	}

	@Override
	public Boolean login(AgentInfo aInfo, String username, String pass) {
		initServiceSettings();
		logger.info("AgentID: " + aInfo.getAgentID() + "; " + getClientIpAdress());
		logger.info("AgentID: " + aInfo.getAgentID() + "; " 
				+ "Log on to service; username: " + username 
				+ " ;pass: " + pass );
		if(isSessionTrue(aInfo)){
/*			MessageContext mc = context.getMessageContext();
			HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
			HttpServletResponse res = (HttpServletResponse)mc.get(MessageContext.SERVLET_RESPONSE);
			res.addHeader("JSESSIONID", "" + session.getId());*/
		    //Map http_headers = (Map) mc.get(MessageContext.HTTP_RESPONSE_HEADERS);
		    //List cook = (List) http_headers.get("Cookie");
		    //logger.info(cook);
			return true;
		}
		if(username.equals(serviceSettings.getServiceUserName()) 
				&& pass.equals(serviceSettings.getServiceUserPass())){
		    MessageContext mc = context.getMessageContext();
/*		    Map http_headers = (Map) mc.get(MessageContext.HTTP_REQUEST_HEADERS);
		    logger.info((List) http_headers.get("Cookie"));
*/		    
		    HttpSession session = 
		    		((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
		    if (session == null)
		    	logger.error("AgentID: " + aInfo.getAgentID() + "; " + "Session is null");
		    else {
		    	session.setAttribute("agentID", aInfo.getAgentID());
		    	logger.info("AgentID: " + aInfo.getAgentID() + "; " 
		    			+ "Session :" + session.getId());
		    	return true;
		    }
		}else{
			logger.error("AgentID: " + aInfo.getAgentID() + "; " 
					+ "Log on to service error; username: " 
					+ username + " ;pass: " + pass );
		}
		return false;
	}

	@Override
	public ClientGroup[] getClientGroups(AgentInfo aInfo) {
		if(!checkAccess(aInfo))
			return null;
		logger.info("AgentID: " + aInfo.getAgentID() + "; " + getClientIpAdress());
		ClientGroup[] groupsArr = null;
		List<ClientGroup> groupsList = new ArrayList<ClientGroup>();
		ClientGroup group = null;
		try {
			conn = DataBase.getConnection(context);
			String sql;
			sql = "select * from CLIENTGROUP";
			logger.info("AgentID: " + aInfo.getAgentID() + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				group = new ClientGroup();
				group.setId(rs.getInt("ID"));
				group.setName(rs.getString("SNAME"));
				groupsList.add(group);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + aInfo.getAgentID() + ": " + " Geting ClientGroups list error!!! " + e);
		}
		groupsArr = new ClientGroup[groupsList.size()];
		for(int j=0;j<groupsList.size();j++){
			groupsArr[j] = groupsList.get(j);
		}
		logger.info("AgentID: " + aInfo.getAgentID() + ": " + "Send ClientGroups: " + groupsArr.length);
		return groupsArr;
	}
	
	public Measure[] getMeasures(int agentID) {
		logger.info("AgentID: " + agentID + "; " + getClientIpAdress());
		Measure[] ggArr = null;
		List<Measure> ggList = new ArrayList<Measure>();
		try {
			conn = DataBase.getConnection(context);
			String sql = "select * from MEASURES";
			logger.info("AgentID: " + agentID + "; " + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Measure measure = new Measure();
				measure.setId(rs.getInt("ID"));
				measure.setName((rs.getString("NAME")));
				ggList.add(measure);
			}
			conn.close();
		} catch (SQLException e) {
			logger.error("AgentID: " + agentID + "; " + " Geting measures error!!! " + e);
		}
		ggArr = new Measure[ggList.size()];
		for(int j=0;j<ggList.size();j++){
			ggArr[j] = ggList.get(j);
		}
		logger.info("AgentID: " + agentID + "; " + "Send Measures : " + ggArr.length);
		return ggArr;
	}

	@Override
	public Measure[] getMeasures(org.dtrader.server.beans.AgentInfo aInfo) {
		return checkAccess(aInfo) ? getMeasures(aInfo.getAgentID()) : null;
	}
	
	private boolean checkAccess(org.dtrader.server.beans.AgentInfo aInfo) {
		boolean res = false;
		initServiceSettings();
		if(checkKey(aInfo) && isSessionTrue(aInfo))
			res = true;
		return res;
	}

}
