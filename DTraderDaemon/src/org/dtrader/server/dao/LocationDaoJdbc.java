package org.dtrader.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dtrader.server.beans.Location;

public class LocationDaoJdbc implements LocationDao{
	private Connection conn;
	private static final Logger logger = Logger.getLogger(LocationDaoJdbc.class);
	
	public LocationDaoJdbc(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public List<Location> getLocationForPeriodByAgentId(Date dateBegin, Date dateEnd, int agentId){
		List<Location> resList = null;
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		try {
			String sql = "select CLOCK, LATITUDE, LONGITUDE from LOCATION where AGENTID=? "
					+ "and CLOCK>='" + df.format(dateBegin) + " 00:00:00' "
					+ "and CLOCK<='" + df.format(dateEnd) + " 23:59:59' "
					+ "and LONGITUDE>0 order by CLOCK";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, agentId);
			ResultSet rs = ps.executeQuery();
			resList = new ArrayList<Location>(rs.getFetchSize());
			df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			while(rs.next()){
				Location loc = new Location();
				loc.setClock(df.format(rs.getTimestamp("CLOCK")));
				loc.setLatitude(rs.getDouble("LATITUDE"));
				loc.setLongitude(rs.getDouble("LONGITUDE"));
				resList.add(loc);
			}
			rs.close();
			
		} catch (SQLException e) {
			logger.error("Error when get agent GPS points: " + e);
		}

		return resList;
	}
}
