package biz.dealnote.web.dao;

import java.util.Collection;

import biz.dealnote.web.model.Job;

public interface JobDao {
	public Collection<Job> getJobs();
	public Job getJobById(Integer id);
	public void save(Job job);
	public void delete(Job job);
}
