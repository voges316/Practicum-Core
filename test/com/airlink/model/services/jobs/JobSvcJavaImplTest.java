package com.airlink.model.services.jobs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.airlink.model.domain.Job;

public class JobSvcJavaImplTest {

	IJobSvc jobSvc; // Testing interface behavior
	Job job;
	
	
	@Before
	public void setUp() throws Exception {
		jobSvc = new JobSvcJavaImpl();
		job = new Job("Training", 2);
	}

	@After
	public void tearDown() throws Exception {
		jobSvc.deleteJob(job.getId());
	}

	@Test
	public void testCreateJob() {
		// Test that job doesn't exist
		assertNull("getJob should return null, searching for bad id", 
				jobSvc.getJob(job.getId()));
		
		// Save and catch result		
		job = jobSvc.createJob(job);
		// Search for job just saved
		Job storedJob = jobSvc.getJob(job.getId());
		assertTrue(storedJob.equals(job));
		
	}

	@Test
	public void testGetJob() {
		assertNull("getJob should return null, searching for bad id", 
				jobSvc.getJob(job.getId()));
		
		// Save and catch result		
		job = jobSvc.createJob(job);
		// Search for job just saved
		Job storedJob = jobSvc.getJob(job.getId());
		assertTrue(storedJob.equals(job));
	}

	@Test
	public void testUpdateJob() {
		// Create the job
		job = jobSvc.createJob(job);
		
		// Get a copy
		Job copy = jobSvc.getJob(job.getId());		
		assertTrue(copy.equals(job));
		
		// Make a change
		copy.setName("Scheduling");
		jobSvc.updateJob(copy);
		
		// Retrieve result and confirm it's different
		Job copy2 = jobSvc.getJob(job.getId());
		assertFalse(copy2.equals(job));
	}

	@Test
	public void testDeleteJob() {
		// Create the job
		job = jobSvc.createJob(job);		
		assertNotNull(jobSvc.getJob(job.getId()));
		
		// Remove the job
		jobSvc.deleteJob(job.getId());		
		assertNull(jobSvc.getJob(job.getId()));
	}

}
