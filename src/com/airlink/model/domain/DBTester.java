package com.airlink.model.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBTester {

	private static EntityManagerFactory emfactory;
	private static EntityManager em;
	
	static {
		emfactory = Persistence.createEntityManagerFactory( "Airlink-Core" );
		em = emfactory.createEntityManager();
	}
	
	public static void shutdown() {
		em.close();
		emfactory.close();
	}
	
	public Job createJob(Job job) {
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
		
		System.out.println("createJob()");
		return job;
	}
	
	public Job getJob(int id) {
		Job j = em.find(Job.class, id);
		
		System.out.println("getJob()");
		return j;
	}
	
	public Job updateJob(Job job) {
		em.getTransaction().begin();
		// Hm. Job is not the owner of the employee/job relationship
		// Need to figure out how to update many to many status
		em.merge(job);
		em.getTransaction().commit();
		
		System.out.println("updateJob()");
		return job;
	}
	
	public Job deleteJob(Job job) {
		em.getTransaction().begin();
		em.remove(job);
		em.getTransaction().commit();
		
		System.out.println("deleteJob()");
		return job;
	}
	
	public Employee createEmployee(Employee employee) {
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		
		System.out.println("createEmployee()");
		return employee;
	}
	
	public Employee getEmployee(int id) {
		Employee e = em.find(Employee.class, id);
		
		System.out.println("getEmployee()");
		return e;
	}
	
	public Employee updateEmployee(Employee employee) {
		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
		
		System.out.println("updateEmployee()");
		return employee;
	}
	
	public Employee deleteEmployee(Employee employee) {
		em.getTransaction().begin();		
		em.remove(employee);
		em.getTransaction().commit();
		
		System.out.println("deleteEmployee()");
		return employee;
	}
	
	public static void main(String[] args) {
		DBTester svc = new DBTester();
		
		testBoth(svc);
		//empStuff(svc);
		// jobStuff(svc);
		
		DBTester.shutdown();

	}
	
	private static void testBoth(DBTester svc) {
		Employee e = svc.getEmployee(103);
		System.out.println(e);
		for (Job j : e.getJobs()) {
			System.out.println("\t" + j);
		}
		
		Job j = svc.getJob(201);
		System.out.println(j);
		for (Employee e1 : j.getEmployees()) {
			System.out.println("\t" + e1);
		}
		
		// This works fine, since Job's are the 'owner'
//		j.getEmployees().add(e);
//		// Job is the 'owner', so this should persist
//		svc.updateJob(j);
		
//		j.getEmployees().clear();
//		svc.updateJob(j);
	}
	
	private static void empStuff(DBTester svc) {
//		Employee e1 = new Employee("Bart", "Simpson", "bart@yahoo.com", "1234567");
//		e1.setGender(Gender.MALE);
//		
//		Employee e2 = new Employee("Lisa", "Simpson", "lisa@yale.edu", "1234567");
//		e2.setGender(Gender.FEMALE);
//		e2.setRank(Rank.OFFICER);
//		
//		Employee e3 = new Employee("Homer", "Simpson", "homer@duff.com", "1234567");
//		e3.setGender(Gender.MALE);
//		e3.setRank(Rank.ENLISTED);
//		
//		svc.createEmployee(e1);
//		svc.createEmployee(e2);
//		svc.createEmployee(e3);		
		
		// Get an employee
//		System.out.println(svc.getEmployee(103));
		
		// Update an employee
//		Employee e = svc.getEmployee(102);
//		e.setEmail("lisa@harvard.edu");
//		System.out.println(svc.updateEmployee(e));
		
		// Delete an employee
//		Employee e = svc.getEmployee(102);
//		System.out.println(svc.deleteEmployee(e));
		
	}
	
	private static void jobStuff(DBTester svc) {
		
//		Job j1 = new Job("Training", 7);
//		j1.setDescription("The training shoppe.");
//		
//		Job j2 = new Job("Scheduling", 3);
//		j2.setDescription("The scheduling shop.");
//		
//		Job j3 = new Job("Snacko", 1);
//		j3.setDescription("Snack Officer & errand boy.");
//		
//		svc.createJob(j1);
//		svc.createJob(j2);
//		svc.createJob(j3);
		
		// Get job
//		System.out.println(svc.getJob(51));
		
		// Update job(s)
//		Job j4 = svc.getJob(51);
//		j4.setDescription("This is a new descrition");
//		svc.updateJob(j4);
		
		// Delete job
		Job j5 = svc.getJob(52);
		System.out.println(svc.deleteJob(j5));		
		
	}

}
