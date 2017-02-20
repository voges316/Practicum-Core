package com.airlink.model.services.employees;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.airlink.model.domain.Employee;

public class EmployeeSvcJPAImplTest {
	
	// Setup
	private static EmployeeSvcJPAImpl svc;
	private static Employee homer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		svc = new EmployeeSvcJPAImpl();
		homer = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// shutdown
		svc.shutdown();
	}
	
	@Before
	public void setUp() throws Exception {
		homer = svc.createEmployee(homer);
	}

	@After
	public void tearDown() throws Exception {
		Employee e = svc.deleteEmployee(homer);
		if (e != null) homer = e;
	}

	@Test
	public void testCreateEmployee() {
		// Homer was added on setup, so should be in db
		Employee e = null;
		e = svc.getEmployee(homer.getId());
		assertNotNull("Should have retreived a valid employee from db", e);
	}

	@Test
	public void testGetEmployee() {
		Employee e = svc.getEmployee(homer.getId());
		assertTrue("DB employee and homer should be equal", e.equals(homer));
	}

	@Test
	public void testUpdateEmployee() {
		String update = "newemail@regis.edu";
		homer.setEmail(update);
		svc.updateEmployee(homer);
		
		Employee e = svc.getEmployee(homer.getId());
		assertTrue("Emails should match after update", e.getEmail().equals(update));
	}

	@Test
	public void testDeleteEmployee() {
		Employee e = svc.deleteEmployee(homer);
		
		Employee f = svc.getEmployee(e.getId());
		assertTrue("Employee should have been removed from db", f == null);
	}

	@Test
	public void testGetEmployees() {
		List<Employee> employees = svc.getEmployees();
		// Should have at least 1 employee
		boolean isEmpty = employees.isEmpty();
		
		assertFalse("Employee list shouldn't be empty", isEmpty);
	}
}
