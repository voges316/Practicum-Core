package com.airlink.model.services;

import com.airlink.model.domain.Employee;
import com.airlink.model.domain.Job;
import com.airlink.model.services.employees.EmployeeSvcJPAImpl;
import com.airlink.model.services.jobs.JobSvcJPAImpl;

public class DBJobEmployeeTester {

	private static JobSvcJPAImpl jobsvc = new JobSvcJPAImpl();
	private static EmployeeSvcJPAImpl empsvc = new EmployeeSvcJPAImpl();
	
	public static void main(String[] args) {
//		Employee e = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
//		Job j1 = new Job("Snack Officer", 1);
//		j1.setDescription("Stock the snack bar and other random tasks");
//		
//		// Create them
//		e = empsvc.createEmployee(e);
//		j1 = jobsvc.createJob(j1);
//		
//		System.out.println("\tAfter:");
//		System.out.println(e1);
//		System.out.println(j1);
		
		// Update them
//		Employee e1 = empsvc.getEmployee(2851);
//		Job j1 = jobsvc.getJob(2901);
//		
//		jobsvc.addEmployee(j1, e1);
//		//jobsvc.removeEmployee(j1, e1);
//		
//		System.out.println("\tAfter:");
//		System.out.println(e1);
//		for (Job j : e1.getJobs()) {
//			System.out.println("\t" + j);
//		}
//		
//		System.out.println(j1);
//		for (Employee e : j1.getEmployees()) {
//			System.out.println("\t" + e);
//		}
		
		// Now test deleting
//		Employee e1 = empsvc.getEmployee(2851);
		Job j1 = jobsvc.getJob(2901);
		System.out.println("\tBefore:");
		for (Employee e : j1.getEmployees()) {
			System.out.println(j1);
			System.out.println("\t" + e);
		}
		
		// e1 = empsvc.deleteEmployee(e1.getId());
		j1 = jobsvc.deleteJob(j1);
		Employee e1 = empsvc.getEmployee(2851);
		
		System.out.println("\tAfter:");
		System.out.println(e1);
		for (Job j : e1.getJobs()) {
			System.out.println("\t" + j);
		}
		
		//Job j1 = jobsvc.getJob(2702);
		System.out.println(j1);
		for (Employee e : j1.getEmployees()) {
			System.out.println("\t" + e);
		}
		
		// finally
		jobsvc.shutdown();
		empsvc.shutdown();
	}

}
