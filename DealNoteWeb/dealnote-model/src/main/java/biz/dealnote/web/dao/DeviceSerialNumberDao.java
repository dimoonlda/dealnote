package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.DeviceSerialNumber;
import biz.dealnote.web.model.User;

public interface DeviceSerialNumberDao {
	
	public Collection<DeviceSerialNumber> getDeviceSerialNumbers();
	public Collection<DeviceSerialNumber> getDeviceSerialNumbersByUser(User user);
	public DeviceSerialNumber getDeviceSerialNumberById(Integer id);
	public void save(DeviceSerialNumber sn);
	public void delete(DeviceSerialNumber sn);
}
