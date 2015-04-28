package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.JobDao;
import biz.dealnote.web.dao.PartDao;
import biz.dealnote.web.dao.PartJobDao;
import biz.dealnote.web.dao.UserDao;
import biz.dealnote.web.model.PartJob;
import biz.dealnote.web.model.User;

public class PartJobDaoJpaTest extends AbstractDaoJpaTest{

	private static final Integer TEST_USER_ID = 100;
	private static final Integer TEST_PART_JOB_ID = 4;
	private static final Integer TEST_PART_ID = 10;
	private static final Integer TEST_JOB_ID = 10;
	private static final Short TEST_ISACTIVE = 1;
	private static final Short TEST_ISACTIVE_EDIT = 0;
	
	@Autowired
	private PartJobDao partJobDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PartDao partDao;
	
	@Autowired
	private JobDao jobDao;
	
	@Test
	public void testGetPartJobs() {
		assertEquals(4, partJobDao.getPartJobs().size());
	}

	@Test
	public void testGetActivePartJobsByUser() {
		User user = userDao.getUserById(TEST_USER_ID);
		assertNotNull("User not found.", user);
		assertEquals(2, partJobDao.getActivePartJobsByUser(user).size());
	}

	@Test
	public void testGetPartJobById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_PART_JOB_ID),
				partJobDao.getPartJobById(TEST_PART_JOB_ID));
	}

	@Test
	public void testSave() {
		PartJob partJob = createTestPartJob();
		partJobDao.save(partJob);
		assertNotNull("Object wasn't saved. Id isn't created.", partJob.getId());
		
		partJob.setIsActive(TEST_ISACTIVE_EDIT);
		partJobDao.save(partJob);
		
		partJob = partJobDao.getPartJobById(partJob.getId());
		assertEquals("Object wasn't updated.", TEST_ISACTIVE_EDIT, partJob.getIsActive());
		
		partJobDao.delete(partJob);
	}

	@Test
	public void testDelete() {
		PartJob partJob = createTestPartJob();
		int size = partJobDao.getPartJobs().size();
		partJobDao.save(partJob);
		assertTrue("Object wasn't added.", 
				size < partJobDao.getPartJobs().size());
		
		partJobDao.delete(partJob);
		assertTrue("Object wasn't removed.", 
				size == partJobDao.getPartJobs().size());
	}

	public PartJob createTestPartJob(){
		PartJob test = new PartJob();
		test.setJob(jobDao.getJobById(TEST_JOB_ID));
		test.setPart(partDao.getPartById(TEST_PART_ID));
		test.setUser(userDao.getUserById(TEST_USER_ID));
		test.setIsActive(TEST_ISACTIVE);
		return test;
	}
}
