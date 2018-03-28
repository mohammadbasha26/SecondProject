package com.niit.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.DAO.JobDAO;
import com.niit.model.Forum;
import com.niit.model.Job;

@Repository(value = "jobDAO")
public class JobDAOImpl implements JobDAO {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;

		} catch (Exception e) {
			return false;
		}
	
	}
	@Transactional
	public boolean deleteJob(int jobId) {	
		try {
			Job job = (Job) sessionFactory.getCurrentSession().get(Job.class, jobId);
		sessionFactory.getCurrentSession().delete(job);
		return true;
	} catch (Exception e) {
		return false;

	}
}
	
@Transactional
	public boolean updateJob(int jobId) {
		try {
			Job job = (Job) sessionFactory.getCurrentSession().get(Job.class, jobId);
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	public List<Job> listJobs(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean approvalJob(Job job) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rejectJob(Job job) {
		// TODO Auto-generated method stub
		return false;
	}

	public Job getJob(int jobId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Job> listAllJobs() {
		// TODO Auto-generated method stub
		return null;
	}

}
