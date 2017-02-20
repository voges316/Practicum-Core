package com.airlink.model.services.jobs;

import java.util.List;

import javax.persistence.Query;

import com.airlink.model.domain.Employee;
import com.airlink.model.domain.Job;
import com.airlink.model.services.AbstractDBSvc;

public class JobSvcJPAImpl extends AbstractDBSvc implements IJobSvc {

	@Override
	public Job createJob(Job job) {
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
		
		System.out.println("createJob()");
		return job;
	}

	@Override
	public Job getJob(int id) {
		Job j = em.find(Job.class, id);
		
		System.out.println("getJob()");
		return j;
	}

	@Override
	public Job updateJob(Job job) {
		em.getTransaction().begin();
		em.merge(job);
		em.getTransaction().commit();
		
		System.out.println("updateJob()");
		return job;
	}

	@Override
	public Job deleteJob(int id) {
		Job j = em.find(Job.class, id);
		
		if (j == null) return null;
		
		em.getTransaction().begin();
		em.remove(j);
		em.getTransaction().commit();
		
		System.out.println("deleteJob()");
		return j;
	}

	@Override
	public List<Job> getJobs() {
		Query query = em.createQuery("SELECT j FROM Job j");
		System.out.println("getJobs()");
		
		return (List<Job>)query.getResultList();
	}

	@Override
	public Job addEmployee(Job j, Employee e) {
		j.getEmployees().add(e);
		
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		
		System.out.println("addEmployee()");
		return j;
	}

	@Override
	public Job removeEmployee(Job j, Employee e) {
		j.getEmployees().remove(e);
		
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		
		System.out.println("removeEmployee()");
		return j;
	}

}
