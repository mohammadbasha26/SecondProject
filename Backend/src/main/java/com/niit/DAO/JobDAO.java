package com.niit.DAO;

import java.util.List;

import com.niit.model.Forum;
import com.niit.model.Job;

public interface JobDAO {
	
	public boolean addJob(Job job);

	public boolean deleteJob(int jobId);

	public boolean updateJob(int jobId);

	public List<Job> listJobs(String username);

	public boolean approvalJob(Job job);

	public boolean rejectJob(Job job);

	public Job getJob(int jobId);

	public List<Job> listAllJobs();



}
