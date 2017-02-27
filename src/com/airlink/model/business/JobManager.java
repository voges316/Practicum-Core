package com.airlink.model.business;

import java.util.List;

import com.airlink.model.domain.Job;
import com.airlink.model.services.jobs.IJobSvc;
import com.airlink.model.services.jobs.JobSvcJPAImpl;

public class JobManager extends ManagerSupertype {
	
	private static IJobSvc jobSvc = new JobSvcJPAImpl();
	private static JobManager instance;
	
	private JobManager() {}
	
	public static synchronized JobManager getInstance() {
		if (instance == null) {
			instance = new JobManager();
		}
		return instance;
	}
	
	public Job registerJob(Job job) {
		Job result = jobSvc.createJob(job);
		return result;
	}
	
	public Job retrieveJob(int id) {
		Job result = jobSvc.getJob(id);
		return result;
	}
	
	public Job updateJob(Job job) {
		Job result = jobSvc.updateJob(job);
		return result;
	}
	
	public Job deleteJob(Job job) {
		Job result = jobSvc.deleteJob(job);
		return result;
	}
	
	public List<Job> getJobs() {
		List<Job> result = jobSvc.getJobs();
		return result;
	}
}
