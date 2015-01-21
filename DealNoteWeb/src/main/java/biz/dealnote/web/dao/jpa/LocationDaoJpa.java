package biz.dealnote.web.dao.jpa;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import biz.dealnote.web.dao.DAOException;
import biz.dealnote.web.dao.LocationDAO;
import biz.dealnote.web.model.Location;

public class LocationDaoJpa extends BaseDaoJpa implements LocationDAO {
	
	public LocationDaoJpa(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Location> getLocationList(Integer agentID, Date byDate)
			throws DAOException {
		return getLocationList(agentID, byDate, byDate);
	}

	@Override
	public List<Location> getLocationList(Integer agentID, Date startDate,
			Date endDate) throws DAOException {
		List<Location> result = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDate);
			cal.add(Calendar.DATE, 1);

			java.sql.Date _beginDate = new java.sql.Date(startDate.getTime());
			java.sql.Date _endDate = new java.sql.Date(cal.getTime().getTime());
			Query q = getSession()
					.createQuery("from Location location "
							+ " where location.agent.id=:agentId and "
							+ " location.clock >= :beginDate and location.clock < :endDate"
							+ " order by location.clock");
			q.setInteger("agentId", agentID);
			q.setTimestamp("beginDate", _beginDate);
			q.setTimestamp("endDate", _endDate);
			result = (List<Location>) q.list();
		} finally {
			//TODO: Log
		}

		return result;
	}

}
