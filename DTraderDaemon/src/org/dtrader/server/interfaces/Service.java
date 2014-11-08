package org.dtrader.server.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import org.dtrader.server.beans.BPOptions;
import org.dtrader.server.beans.Client;
import org.dtrader.server.beans.ClientGroup;
import org.dtrader.server.beans.DebtDoc;
import org.dtrader.server.beans.DocType;
import org.dtrader.server.beans.Goods;
import org.dtrader.server.beans.GoodsGroup;
import org.dtrader.server.beans.KeyFile;
import org.dtrader.server.beans.LocationWrapper;
import org.dtrader.server.beans.Measure;
import org.dtrader.server.beans.Routes;
import org.dtrader.server.beans.TradeAgent;

@WebService
@SOAPBinding(style = Style.RPC)
public interface Service {
	/**
	 * 	Obtain clients
	 * @param aInfo - instance of AgentInfo
	 * @return
	 */
	@WebMethod Client[] getClients(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain array of goods
	 * @param aInfo - instance of AgentInfo
	 * @return array of Goods
	 */
	@WebMethod Goods[] getGoods(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain array of goods groups
	 * @param aInfo - instance of AgentInfo
	 * @return array of GoodsGroup
	 */
	@WebMethod GoodsGroup[] getGoodsGroup(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain array of Measures
	 * @param aInfo - instance of AgentInfo
	 * @return array of Measure
	 */
	@WebMethod Measure[] getMeasures(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain array of document types
	 * @param aInfo - instance of AgentInfo
	 * @return array of DocType
	 */
	@WebMethod DocType[] getDocTypes(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain debt documents
	 * @param aInfo - instance of AgentInfo
	 * @return array of DebtDoc
	 */
	@WebMethod DebtDoc[] getDebtDocs(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain options of mobile client
	 * @param aInfo - instance of AgentInfo
	 * @return
	 */
	@WebMethod BPOptions getBPOptions(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Insert document(order)
	 * @return document ID after success insert 
	 * (-1 - error)
	 */
	@WebMethod int insertDocument(org.dtrader.server.beans.AgentInfo aInfo, org.dtrader.server.beans.Document doc);
	/**
	 * insert location coordinates
	 */
	@WebMethod int insertLocations(org.dtrader.server.beans.AgentInfo aInfo, org.dtrader.server.beans.LocationWrapper locWrapper);
	/**
	 * Obtain sales reps list(for supervisor)
	 * @param aInfo - instance of AgentInfo
	 * @return
	 */
	@WebMethod TradeAgent[] getTradeAgents(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain URL to sales rep's route for date 
	 * @param aInfo - instance of AgentInfo
	 * @param agentID - sales rep ID
	 * @param date - route date
	 * @return
	 */
	@WebMethod String getAgentGPSRoute(org.dtrader.server.beans.AgentInfo aInfo, int agentID, String date);
	/**
	 * Obtain URL for APK-file update
	 * @param aInfo - instance of AgentInfo
	 * @param agentApkVer - APK version on mobile client
	 * @return
	 */
	@WebMethod String checkUpdate(org.dtrader.server.beans.AgentInfo aInfo, int agentApkVer);
	/**
	 * Upload key-file for encoding on server
	 * @param aInfo - instance of AgentInfo
	 * @param keyFile - file to upload
	 * @return if success then return true, else -  false
	 */
	@WebMethod boolean uploadKeyFile(org.dtrader.server.beans.AgentInfo aInfo, KeyFile keyFile);
	/**
	 * Upload file with encoded serial numbers on server
	 * @param aInfo - instance of AgentInfo
	 * @param keyFile - file to upload
	 * @return if success then return true, else -  false
	 */
	@WebMethod boolean uploadKeyFileSN(org.dtrader.server.beans.AgentInfo aInfo, KeyFile keyFile);
	/**
	 * Check client's serial number
	 * @param key - client's serial number
	 * @return if success then return true, else -  false
	 */	
	@WebMethod boolean checkKey(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Obtain GPS coordinates of sales rep for date 
	 * @param aInfo - instance of AgentInfo
	 * @param agentID - sales rep ID
	 * @param date - route's date
	 * @return
	 */	
	@WebMethod LocationWrapper getAgentGPSPointsByDay(org.dtrader.server.beans.AgentInfo aInfo, int agentID, String date);
	/**
	 * Obtain routes
	 * @param aInfo - instance of AgentInfo
	 * @return array of Routes
	 */
	@WebMethod Routes[] getRoutes(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Check if mobile client has new information for update.
	 * If information on server wasn't updated it is not necessary to do update
	 * @param aInfo - instance of AgentInfo
	 * @return if need then true else false
	 */
	@WebMethod Boolean checkAgentInfoUpdate(org.dtrader.server.beans.AgentInfo aInfo);
	/**
	 * Login to service and create new session
	 * @param username 
	 * @param pass 
	 * @return true - if all is ok, else false
	 */
	@WebMethod Boolean login(org.dtrader.server.beans.AgentInfo aInfo, String username, String pass);
	/**
	 * Obtain client's groups
	 * @return array of ClientGroup
	 */
	@WebMethod ClientGroup[] getClientGroups(org.dtrader.server.beans.AgentInfo aInfo);
}
