package com.airlink.model.services.jobs;

import java.util.List;

import com.airlink.model.domain.Job;

public class JobSvcTest {

	public static void main(String[] args) {
		// Create job
		Job job = new Job("Training", 5);
//		System.out.println(job.toString());
//		System.out.println(job.validate());
		
		IJobSvc jobSvc = new JobSvcJavaImpl();
		jobSvc.createJob(job);
		
		printJobs("After storing", jobSvc.getJobs());
		
		Job job2 = new Job("Scheduling", 4);
		Job job3 = new Job("Staff", 2);
		jobSvc.createJob(job2);
		jobSvc.createJob(job3);
		
		printJobs("After 2 more updates", jobSvc.getJobs());
		
		Job jobX = jobSvc.getJob(2);
		System.out.println("Retrieved job");
		System.out.println(jobX);
		
		jobX.setNumberNeeded(20);
		jobSvc.updateJob(jobX);
		
		printJobs("After updating", jobSvc.getJobs());
		
		// Deleting
		jobSvc.deleteJob(1);
		printJobs("After deleting", jobSvc.getJobs());
	}

	private static void printJobs(String string, List<Job> jobs) {
		System.out.println(string);
		for (Job j : jobs) {
			System.out.println(j);
		}
	}

}
