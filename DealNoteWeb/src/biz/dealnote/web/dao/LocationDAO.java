package biz.dealnote.web.dao;

import java.util.List;

import org.dtrader.web.beans.LocationBean;

public interface LocationDAO {
	/**
	 * �������� ������ ��������� ������ �� ����.
	 * @param agentID ��� ������
	 * @param byDate ����, � ������� dd.MM.YYYY
	 * @return
	 * @throws DAOException
	 */
	public List<LocationBean> getLocationList(Integer agentID, String byDate) throws DAOException;
	
	/**
	 * �������� ������ ��������� ������ �� ������.
	 * @param agentID ��� ������
	 * @param startDate ���� ������ �������, � ������� dd.MM.YYYY
	 * @param endDate ���� ����� �������, � ������� dd.MM.YYYY
	 * @return
	 * @throws DAOException
	 */	
	public List<LocationBean> getLocationList(Integer agentID, String startDate, String endtDate) throws DAOException;
}
