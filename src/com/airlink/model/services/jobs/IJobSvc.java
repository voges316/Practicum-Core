package com.airlink.model.services.jobs;

import java.util.List;

import com.airlink.model.domain.Employee;
import com.airlink.model.domain.Job;

public interface IJobSvc {

	public Job createJob(Job job);
	
	public Job getJob(int id);
	
	public Job updateJob(Job job);
	
	public Job deleteJob(int id);
	
	public Job deleteJob(Job job);
	
	public List<Job> getJobs();
	
	public Job addEmployee(Job j, Employee e);
	
	public Job removeEmployee(Job j, Employee e);
}
