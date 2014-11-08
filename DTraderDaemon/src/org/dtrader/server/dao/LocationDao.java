package org.dtrader.server.dao;

import java.util.Date;
import java.util.List;

import org.dtrader.server.beans.Location;

public interface LocationDao {
	public List<Location> getLocationForPeriodByAgentId(Date dateBegin, Date dateEnd, int agentId);
}
