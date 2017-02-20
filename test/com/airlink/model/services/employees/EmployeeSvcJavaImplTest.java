package com.airlink.model.services.employees;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.airlink.model.domain.Employee;

public class EmployeeSvcJavaImplTest {
	
	IEmployeeSvc empSvc; // Testing interface behavior
	Employee emp;

	@Before
	public void setUp() throws Exception {
		empSvc = new EmployeeSvcJavaImpl();
		emp = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
	}

	@After
	public void tearDown() throws Exception {
		empSvc.deleteEmployee(emp.getId());
	}

	@Test
	public void testCreateEmployee() {
		// Test employee doesn't exist
		assertNull("getEmployee should return null, searching for bad id",
				empSvc.getEmployee(emp.getId()));
		
		// Save and catch result
		emp = empSvc.createEmployee(emp);
		// Search for employee just saved
		Employee storedEmp = empSvc.getEmployee(emp.getId());
		assertTrue(storedEmp.equals(emp));
	}

	@Test
	public void testGetEmployee() {
		assertNull("getEmployee should return null, searchinf for bad id",
				empSvc.getEmployee(emp.getId()));
		
		// Save and catch result
		emp = empSvc.createEmployee(emp);
		// Search for employee just saved
		Employee storedEmp = empSvc.getEmployee(emp.getId());
		assertTrue(storedEmp.equals(emp));
	}

	@Test
	public void testUpdateEmployee() {
		// Create employee
		emp = empSvc.createEmployee(emp);
		
		// Get a copy
		Employee copy = empSvc.getEmployee(emp.getId());
		assertTrue(copy.equals(emp));
		
		// Make a change
		copy.setFirstName("Marge");
		empSvc.updateEmployee(copy);
		
		Employee copy2 = empSvc.getEmployee(emp.getId());
		assertFalse(copy2.equals(emp));
	}

	@Test
	public void testDeleteEmployee() {
		// Create employee
		emp = empSvc.createEmployee(emp);
		assertNotNull(empSvc.getEmployee(emp.getId()));
		
		// Remove employee
		empSvc.deleteEmployee(emp.getId());
		assertNull(empSvc.getEmployee(emp.getId()));
		
	}

}
