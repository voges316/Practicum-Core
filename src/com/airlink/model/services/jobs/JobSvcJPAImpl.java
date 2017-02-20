package com.airlink.model.services.jobs;

import java.util.List;

import javax.persistence.Query;

import com.airlink.model.domain.Employee;
import com.airlink.model.domain.Job;
import com.airlink.model.services.AbstractDBSvc;
import com.airlink.model.services.employees.EmployeeSvcJPAImpl;

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
	public Job deleteJob(Job job) {
		Job j = em.find(Job.class, job.getId());
		
		if (j == null) return null;
		
		em.getTransaction().begin();
		if (j.getEmployees().size() > 0) {
			//EmployeeSvcJPAImpl empSvc = new EmployeeSvcJPAImpl(); 
			for (Employee e : j.getEmployees()) {
				e.getJobs().remove(j);
				// empSvc.updateEmployee(e);
			}
			j.getEmployees().clear();
			em.merge(j);
		}
		
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
		
		em.getTransaction().begin();
		j.getEmployees().add(e);
		e.getJobs().add(j);
		em.merge(j);
		
		em.getTransaction().commit();
		
		System.out.println("addEmployee()");
		return j;
	}

	@Override
	public Job removeEmployee(Job j, Employee e) {

		em.getTransaction().begin();
		j.getEmployees().remove(e);
		e.getJobs().remove(j);
		em.merge(j);
		em.getTransaction().commit();
		
		System.out.println("removeEmployee()");
		return j;
	}

}
