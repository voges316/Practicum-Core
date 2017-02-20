package com.airlink.model.services.jobs;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.airlink.model.domain.Employee;
import com.airlink.model.domain.Job;
import com.airlink.model.services.employees.EmployeeSvcJPAImpl;

public class JobSvcJPAImplTest {
	
	// Setup
	private static JobSvcJPAImpl svc;
	private static Job snacko;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		svc = new JobSvcJPAImpl();
		snacko = new Job("Snack Officer", 1);
		snacko.setDescription("Stock the snack bar and other random tasks");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		svc.shutdown();
	}

	@Before
	public void setUp() throws Exception {
		snacko = svc.createJob(snacko);
	}

	@After
	public void tearDown() throws Exception {
		Job j = svc.deleteJob(snacko);
		if (j != null) snacko = j;
	}

	@Test
	public void testCreateJob() {
		// Snacko was added on setup, so should be in db
		Job j = null;
		j = svc.getJob(snacko.getId());
		assertNotNull("Should have retreived a valid job from db", j);
		
	}

	@Test
	public void testGetJob() {
		Job j = svc.getJob(snacko.getId());
		assertTrue("DB employee and snacko should be equal", j.equals(snacko));
	}

	@Test
	public void testUpdateJob() {
		String update = "New job description";
		snacko.setDescription(update);
		svc.updateJob(snacko);
		
		Job j = svc.getJob(snacko.getId());
		assertTrue("Descriptions should match after update", 
				j.getDescription().equals(update));
	}

	@Test
	public void testDeleteJob() {
		Job j = svc.deleteJob(snacko);
		
		Job k = svc.getJob(j.getId());
		assertTrue("Job should have been removed from db", k == null);
	}

	@Test
	public void testGetJobs() {
		List<Job> jobs = svc.getJobs();
		// Should have at least 1 job
		boolean isEmpty = jobs.isEmpty();
		
		assertFalse("Job list should't be empty", isEmpty);
	}

	@Test
	public void testAddEmployee() {
		EmployeeSvcJPAImpl empSvc = new EmployeeSvcJPAImpl(); 
		Employee homer = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
		homer = empSvc.createEmployee(homer);
		
		assertTrue("homer shouldn't have any jobs", 
				homer.getJobs().size() == 0);
		assertTrue("snacko shouldn't have any employees", snacko.getEmployees().size() == 0);
		
		snacko = svc.addEmployee(snacko, homer);
		homer = empSvc.getEmployee(homer.getId());
		
		assertTrue("homer should be assigned to snacko job", homer.getJobs().size() == 1);
		assertTrue("snacko should have homer as an employee", snacko.getEmployees().size() == 1);
		
		empSvc.deleteEmployee(homer);
		empSvc.shutdown();
	}

	@Test
	public void testRemoveEmployee() {
		EmployeeSvcJPAImpl empSvc = new EmployeeSvcJPAImpl(); 
		Employee homer = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
		homer = empSvc.createEmployee(homer);
		
		snacko = svc.addEmployee(snacko, homer);
		homer = empSvc.getEmployee(homer.getId());
		
		assertTrue("homer should be assigned to snacko job", homer.getJobs().size() == 1);
		assertTrue("snacko should have homer as an employee", snacko.getEmployees().size() == 1);
		
		snacko = svc.removeEmployee(snacko, homer);
		homer = empSvc.getEmployee(homer.getId());
		
		assertTrue("homer shouldn't have any jobs", 
				homer.getJobs().size() == 0);
		assertTrue("snacko shouldn't have any employees", snacko.getEmployees().size() == 0);
		
		empSvc.deleteEmployee(homer);
		empSvc.shutdown();
	}

	@Test
	public void testDeleteEmployeeAssignedToJob() {
		EmployeeSvcJPAImpl empSvc = new EmployeeSvcJPAImpl(); 
		Employee homer = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
		homer = empSvc.createEmployee(homer);
		
		snacko = svc.addEmployee(snacko, homer);
		homer = empSvc.getEmployee(homer.getId());
		
//		System.out.println("===========Before===========");
//		System.out.println(homer);
//		for (Job j : homer.getJobs()) {
//			System.out.println("\t" + j);
//		}
//		
//		System.out.println(snacko);
//		for (Employee e : snacko.getEmployees()) {
//			System.out.println("\t" + e);
//		}
		
		assertTrue("homer should be assigned to snacko job", homer.getJobs().size() == 1);
		assertTrue("snacko should have homer as an employee", snacko.getEmployees().size() == 1);
		
		// Now delete the employee
		homer = empSvc.deleteEmployee(homer);
		assertTrue("homer shouldn't have any jobs", 
				homer.getJobs().size() == 0);
		assertTrue("snacko shouldn't have any employees", snacko.getEmployees().size() == 0);
		
//		System.out.println("===========After===========");
//		System.out.println(f);
//		for (Job j : f.getJobs()) {
//			System.out.println("\t" + j);
//		}
//		
//		System.out.println(snacko);
//		for (Employee e : snacko.getEmployees()) {
//			System.out.println("\t" + e);
//		}
		
		empSvc.shutdown();
	}
	
	@Test
	public void testDeleteJobContainingEmployees() {
		EmployeeSvcJPAImpl empSvc = new EmployeeSvcJPAImpl(); 
		Employee homer = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
		homer = empSvc.createEmployee(homer);
		
		snacko = svc.addEmployee(snacko, homer);
		homer = empSvc.getEmployee(homer.getId());
		
		System.out.println("===========Before===========");
		System.out.println(homer);
		for (Job j : homer.getJobs()) {
			System.out.println("\t" + j);
		}
		
		System.out.println(snacko);
		for (Employee e : snacko.getEmployees()) {
			System.out.println("\t" + e);
		}
		
		snacko = svc.deleteJob(snacko);
		homer = empSvc.getEmployee(homer.getId());
		
		System.out.println("===========After===========");
		System.out.println(homer);
		for (Job j : homer.getJobs()) {
			System.out.println("\t" + j);
		}
		
		System.out.println(snacko);
		for (Employee e : snacko.getEmployees()) {
			System.out.println("\t" + e);
		}
		
		//homer = empSvc.deleteEmployee(homer.getId());
		empSvc.shutdown();
	}
}
