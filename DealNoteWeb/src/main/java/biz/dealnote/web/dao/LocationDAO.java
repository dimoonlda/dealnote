package biz.dealnote.web.dao;

import java.util.Date;
import java.util.List;

import biz.dealnote.web.beans.Location;

public interface LocationDAO {
	/**
	 * Получить список координат агента за дату.
	 * @param agentID код агента
	 * @param byDate дата, в формате dd.MM.YYYY
	 * @return
	 * @throws DAOException
	 */
	public List<Location> getLocationList(Integer agentID, Date byDate) throws DAOException;
	
	/**
	 * Получить список координат агента за период.
	 * @param agentID код агента
	 * @param startDate дата начала периода, в формате dd.MM.YYYY
	 * @param endDate дата конца периода, в формате dd.MM.YYYY
	 * @return
	 * @throws DAOException
	 */	
	public List<Location> getLocationList(Integer agentID, Date startDate, Date endtDate) throws DAOException;
}