package com.airlink.model.services.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airlink.model.domain.Employee;
import com.airlink.model.domain.Job;

public class JobSvcJavaImpl implements IJobSvc {

	private static Map<Integer, Job> jobs;
	private static int jobsId;
	final static Logger logger = LoggerFactory.getLogger(JobSvcJavaImpl.class);
	
	static {
		jobs = new HashMap<>();
		jobsId = 0;
	}
	
	@Override
	public Job createJob(Job job) {
		// Validate input
		if (job == null || !job.validate()) {
			logger.warn("Bad input provided to createJob");
			return null;
		}
		
		// Check for stored job
		int id = job.getId();
		if (id >=0 && jobs.get(id) != null) {
			logger.warn("Job already exists with id: " + id);
			return null;
		}
		
		// Store new job
		job.setId(jobsId);
		jobs.put(jobsId, job);
		jobsId++;
		
		logger.debug("Stored job");
		return new Job(job);
	}

	@Override
	public Job getJob(int id) {
		Job result = jobs.get(id);
		if (result == null) return null;
		
		return new Job(result);
	}

	@Override
	public Job updateJob(Job job) {
		// Validate input
		if (job == null || !job.validate()) {
			logger.warn("Bad input provided to updateJob");
			return null;
		}
		
		// Simple logic. If id exists update entry
		int id = job.getId();
		if (jobs.containsKey(id)) {
			jobs.put(id, job);
			return new Job(job);
		} else {
			logger.warn("No entry for id: " + id + ", nothing to update.");
			return null;
		}
	}

	@Override
	public Job deleteJob(int id) {
		logger.debug("Removing job");
		// Removing job from the list, so return actual object, not a copy
		return jobs.remove(id);
	}
	
	@Override
	public Job deleteJob(Job job) {
		return deleteJob(job.getId());
	}

	@Override
	public List<Job> getJobs() {
		// TODO make deep copy if needed
		List<Job> result = new ArrayList<Job>(jobs.values());
		return result;
	}

	@Override
	public Job addEmployee(Job j, Employee e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Job removeEmployee(Job j, Employee e) {
		// TODO Auto-generated method stub
		return null;
	}

}
