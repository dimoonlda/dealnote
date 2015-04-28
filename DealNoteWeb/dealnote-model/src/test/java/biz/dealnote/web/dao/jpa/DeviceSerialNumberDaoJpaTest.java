package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.DeviceSerialNumberDao;
import biz.dealnote.web.dao.UserDao;
import biz.dealnote.web.model.DeviceSerialNumber;
import biz.dealnote.web.model.User;

public class DeviceSerialNumberDaoJpaTest extends AbstractDaoJpaTest{

	private static final Integer TEST_DEVICE_SN_ID = 1;
	private static final Integer TEST_USER_ID = 100;
	private static final String TEST_SERIAL_NUMBER = "100";
	private static final String TEST_DESCRIPTION = "test";
	
	@Autowired
	private DeviceSerialNumberDao deviceSerialNumberDao;
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testGetDeviceSerialNumbers() {
		assertEquals(1, deviceSerialNumberDao
				.getDeviceSerialNumbers().size());
	}

	@Test
	public void testGetDeviceSerialNumbersByUser() {
		User user = userDao.getUserById(TEST_USER_ID);
		assertEquals(1, deviceSerialNumberDao
				.getDeviceSerialNumbersByUser(user).size());
	}

	@Test
	public void testGetDeviceSerialNumberById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_DEVICE_SN_ID), 
				deviceSerialNumberDao.getDeviceSerialNumberById(TEST_DEVICE_SN_ID));
	}

	@Test
	public void testSave() {
		DeviceSerialNumber sn = createTestDeviceSn();
		deviceSerialNumberDao.save(sn);
		assertNotNull("Object wasn't saved. Id isn't created.", sn.getId());
		
		sn.setDescription(TEST_DESCRIPTION);
		deviceSerialNumberDao.save(sn);
		
		sn = deviceSerialNumberDao.getDeviceSerialNumberById(sn.getId());
		assertEquals("Object wasn't updated.", TEST_DESCRIPTION, sn.getDescription());
		assertNotNull("User wasn't saved.", sn.getUser());
		
		deviceSerialNumberDao.delete(sn);
	}

	@Test
	public void testDelete() {
		DeviceSerialNumber sn = createTestDeviceSn();
		int size = deviceSerialNumberDao.getDeviceSerialNumbers().size();
		deviceSerialNumberDao.save(sn);
		assertTrue("Object wasn't added.", 
				size < deviceSerialNumberDao.getDeviceSerialNumbers().size());
		
		deviceSerialNumberDao.delete(sn);
		assertTrue("Object wasn't removed.", 
				size == deviceSerialNumberDao.getDeviceSerialNumbers().size());
	}

	public DeviceSerialNumber createTestDeviceSn(){
		DeviceSerialNumber test = new DeviceSerialNumber();
		test.setSerialNumber(TEST_SERIAL_NUMBER);
		test.setUser(userDao.getUserById(TEST_USER_ID));
		return test;
	}
}
