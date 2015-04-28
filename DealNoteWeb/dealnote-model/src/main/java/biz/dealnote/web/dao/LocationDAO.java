package biz.dealnote.web.dao;

import java.util.Collection;
import org.joda.time.DateTime;

import biz.dealnote.web.model.Location;

public interface LocationDAO {
	/**
	 * Получить список координат агента за дату.
	 * @param agentID код агента
	 * @param byDate дата, в формате dd.MM.YYYY
	 * @return
	 * @throws DAOException
	 */
	public Collection<Location> getLocationList(Integer agentID, DateTime byDate);
	
	/**
	 * Получить список координат агента за период.
	 * @param agentID код агента
	 * @param startDate дата начала периода, в формате dd.MM.YYYY
	 * @param endDate дата конца периода, в формате dd.MM.YYYY
	 * @return
	 * @throws DAOException
	 */	
	public Collection<Location> getLocationList(Integer agentID, DateTime startDate, DateTime endtDate);
	
	public void save(Location location);
	public void delete(Location location);
}