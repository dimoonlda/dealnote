package biz.dealnote.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dtrader.web.beans.LocationBean;

public class LocationDAOJDBC implements LocationDAO {
	
	public static final String colLocation_AgentID = "AGENTID";
	public static final String colLocation_Longitude = "LONGITUDE"; 
	public static final String colLocation_Latitude = "LATITUDE";
	public static final String colLocation_Clock = "CLOCK";
	public static final String colLocation_Provider = "PROVIDER";
	public static final String colLocation_Accuracy = "ACCURACY";
	public static final String colLocation_SearchTime = "SEARCHTIME";
	public static final String colLocation_SaveState = "SAVESTATE";
	public static final String colLocation_Check = "CHECKINS";
	public static final String colLocation_Battery = "BATTERY";

	private final String SQL_LIST_LOCATION_BY_AGENT_AND_DAY = "SELECT * FROM LOCATION WHERE AGENTID=? AND CLOCK>=? and CLOCK<? ORDER BY CLOCK";
	private DAOFactory factory;
	
	public LocationDAOJDBC(DAOFactory factory){
		this.factory = factory;
	}
	
	@Override
	public List<LocationBean> getLocationList(Integer agentID, String _startDate, String _endDate) throws DAOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rsList = null;
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		List<LocationBean> locList = new ArrayList<LocationBean>();
		try{
			java.util.Date _byDate = df.parse(_startDate);
			java.util.Date _byDate2 = df.parse(_endDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(_byDate2);
			cal.add(Calendar.DATE, 1);
			
			java.sql.Date beginDate = new java.sql.Date(_byDate.getTime());
			java.sql.Date endDate = new java.sql.Date(cal.getTime().getTime());

			conn = factory.getConnection();
			ps = conn.prepareStatement(SQL_LIST_LOCATION_BY_AGENT_AND_DAY);
			ps.setInt(1, agentID);
			ps.setDate(2, beginDate);
			ps.setDate(3, endDate);
			rsList = ps.executeQuery();
			while(rsList.next()){
				LocationBean loc = new LocationBean();
				loc.setAgentID(rsList.getInt(colLocation_AgentID));
				loc.setAccuracy(rsList.getInt(colLocation_Accuracy));
				loc.setBattery(rsList.getInt(colLocation_Battery));
				loc.setClock(rsList.getString(colLocation_Clock));
				loc.setLatitude(rsList.getDouble(colLocation_Latitude));
				loc.setLongitude(rsList.getDouble(colLocation_Longitude));
				loc.setProvider(rsList.getString(colLocation_Provider));
				loc.setSavestate(rsList.getInt(colLocation_SaveState));
				loc.setSearchtime(rsList.getInt(colLocation_SearchTime));
				locList.add(loc);
			}
		}catch(SQLException e){
			throw new DAOException(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}finally{
			DAOUtil.close(conn, ps, rsList);
		}
		return locList;
	}

	@Override
	public List<LocationBean> getLocationList(Integer agentID, String byDate) throws DAOException {
		return getLocationList(agentID, byDate, byDate);
	}

}
