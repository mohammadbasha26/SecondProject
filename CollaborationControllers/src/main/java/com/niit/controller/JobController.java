package com.niit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.ApplyJobDAO;
import com.niit.DAO.JobDAO;
import com.niit.model.ApplyJob;
import com.niit.model.Blog;
import com.niit.model.Job;

@RestController

public class JobController 
{
	@Autowired
	JobDAO jobDAO;
	ApplyJobDAO applyjobDAO;
	

	@GetMapping("/getAllJobs")
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> jobs = jobDAO.listAllJobs();

		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);

	}
	
	@PostMapping(value="/publishJob")
	public ResponseEntity<Job> publishJob(@RequestBody Job job)
	{
		//job.setCreatedDate(new java.util.Date());
		if(jobDAO.publishJob(job))
		{
			return new ResponseEntity<Job>(job, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Job>(job, HttpStatus.NOT_FOUND);
			
		}
	}
	
	@GetMapping(value="/deleteJob/{jobId}")
	public ResponseEntity<String> deleteJob(@PathVariable("jobId") int jobId)
	{
		Job job=jobDAO.getJob(jobId);
		if(jobDAO.deleteJob(jobId))
		{
			return new ResponseEntity<String>("success", HttpStatus.OK);	
		}
		else
		{
			return new ResponseEntity<String>("Failure", HttpStatus.NOT_FOUND);
			
			
		}
	}

	@GetMapping(value="/showjobs")
	public ResponseEntity<List<Job>> listAllJob()
	{
		List<Job> listJobs=jobDAO.listAllJobs();
		
		return new ResponseEntity<List<Job>>(listJobs, HttpStatus.OK);
		
	}






	@GetMapping("/applyjob/{jobId}")
	public ResponseEntity<Job>approveBlog(@PathVariable("jobId")int jobId)
	{
		Job job=(Job)jobDAO.getJob(jobId);
		if(jobDAO.applyJob(job))
		{
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Job>(job,HttpStatus.NOT_FOUND);
			
			
		}
	}

/*
@GetMapping("/rejectBlog/{blogId}")
public ResponseEntity<Blog>rejectBlog(@PathVariable("blogId")int blogId)
{
	Blog blog=blogDAO.getBlog(blogId);
	if(blogDAO.rejectBlog(blog))
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		
		
	}
}
*//*@GetMapping("/deleteBlog/{blogId}")
public ResponseEntity<Blog>deleteBlog(@PathVariable("blogId")int blogId)
{ Blog blog=blogDAO.getBlog(blogId);

	
	if(blogDAO.deleteBlog(blogId))
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		
		
	}
}
*/}



