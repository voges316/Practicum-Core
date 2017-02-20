package com.airlink.model.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeTest {
	
	Employee badEmployee;
	Employee goodEmployee;
	Employee sameEmployee;

	@Before
	public void setUp() throws Exception {
		badEmployee = new Employee();
		badEmployee.setFirstName("Bart");
		badEmployee.setLastName("Simpson");
		
		goodEmployee = new Employee("Lisa", "Simpson", "lisa@gmail.com", "12345");
		
		sameEmployee = new Employee("Lisa", "Simpson", "lisa@gmail.com", "12345");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsObject() {
		assertFalse("Employees should not be equal", goodEmployee.equals(badEmployee));
		assertTrue("Employees should equal one another", goodEmployee.equals(sameEmployee));
	}

	@Test
	public void testValidate() {
		assertFalse("Employee should not be valid", badEmployee.validate());
		assertTrue("Should be a valid employee", goodEmployee.validate());
	}

}
