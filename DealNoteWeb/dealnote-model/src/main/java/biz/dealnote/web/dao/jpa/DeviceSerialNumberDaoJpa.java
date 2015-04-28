package biz.dealnote.web.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import biz.dealnote.web.dao.DeviceSerialNumberDao;
import biz.dealnote.web.model.DeviceSerialNumber;
import biz.dealnote.web.model.User;

@Repository
public class DeviceSerialNumberDaoJpa implements DeviceSerialNumberDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Collection<DeviceSerialNumber> getDeviceSerialNumbers() {
		return em.createQuery("from DeviceSerialNumber d", DeviceSerialNumber.class)
				.getResultList();
	}

	@Override
	public Collection<DeviceSerialNumber> getDeviceSerialNumbersByUser(User user) {
		return em.createQuery("from DeviceSerialNumber d where d.user = :user", DeviceSerialNumber.class)
				.setParameter("user", user)
				.getResultList();
	}

	@Override
	public DeviceSerialNumber getDeviceSerialNumberById(Integer id) {
		return em.createQuery("from DeviceSerialNumber d where d.id = :id", DeviceSerialNumber.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void save(DeviceSerialNumber sn) {
		if(sn.isNew()){
			em.persist(sn);
		}else{
			em.merge(sn);
		}
	}

	@Override
	public void delete(DeviceSerialNumber sn) {
		em.remove(sn);
	}

}
