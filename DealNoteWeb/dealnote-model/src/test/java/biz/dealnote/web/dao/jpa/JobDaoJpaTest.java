package biz.dealnote.web.dao.jpa;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import biz.dealnote.web.dao.JobDao;
import biz.dealnote.web.model.DefaultObjectsFactory;
import biz.dealnote.web.model.Job;

public class JobDaoJpaTest extends AbstractDaoJpaTest{

	private static final Integer TEST_JOB_ID = 10;
	private static final String TEST_JOB_ROLE_NAME = "TESTROLE";
	
	@Autowired
	private JobDao jobDao;
	
	@Test
	public void testGetJobs() {
		assertEquals(5, jobDao.getJobs().size());
	}

	@Test
	public void testGetJobById() {
		assertNotNull(String.format("Couldn't find object with id = %d", TEST_JOB_ID),
				jobDao.getJobById(TEST_JOB_ID));
	}

	@Test
	public void testSave() {
		Job job = DefaultObjectsFactory.createDefaultJob(null);
		jobDao.save(job);
		assertNotNull("Object wasn't saved. Id isn't created.", job.getId());
		
		job.setRoleName(TEST_JOB_ROLE_NAME);
		jobDao.save(job);
		
		job = jobDao.getJobById(job.getId());
		assertEquals("Object wasn't updated.", TEST_JOB_ROLE_NAME, job.getRoleName());
	}

	@Test
	public void testDelete() {
		Job job = DefaultObjectsFactory.createDefaultJob(null);
		int size = jobDao.getJobs().size();
		jobDao.save(job);
		assertTrue("Object wasn't added.", 
				size < jobDao.getJobs().size());
		
		jobDao.delete(job);
		assertTrue("Object wasn't removed.", 
				size == jobDao.getJobs().size());
	}
}
