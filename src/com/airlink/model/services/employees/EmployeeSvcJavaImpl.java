package com.airlink.model.services.employees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airlink.model.domain.Employee;

public class EmployeeSvcJavaImpl implements IEmployeeSvc {
	
	private static Map<Integer, Employee> employees;
	private static int employeesId;
	final static Logger logger = LoggerFactory.getLogger(EmployeeSvcJavaImpl.class);

	static {
		employees = new HashMap<>();
		employeesId = 0;
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		// Validate input
		if (employee == null || !employee.validate()) {
			logger.warn("Bad input provided to createEmployee");
			return null;
		}
		
		// Check for stored employee
		int id = employee.getId();
		if (id >=0 && employees.get(id) != null) {
			logger.warn("Employee already exists with id: " + id);
			return null;
		}
		
		// Store new employee
		employee.setId(employeesId);
		employees.put(employeesId, employee);
		employeesId++;
		
		logger.debug("Stored employee");
		//printEmployees();
		return new Employee(employee);
	}

	@Override
	public Employee getEmployee(int id) {
		Employee result = employees.get(id);
		if (result == null) return null;
		
		return new Employee(result);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// Validate input
		if (employee == null || !employee.validate()) {
			logger.warn("Bad input provided to updateEmployee");
			return null;
		}
		
		// Simple logic. If id exists update entry
		int id = employee.getId();
		if (employees.containsKey(id)) {
			employees.put(id, employee);
			return new Employee(employee);
		} else {
			logger.warn("No entry for id: " + id + ", nothing to update.");
			return null;
		}
	}

	@Override
	public Employee deleteEmployee(int id) {
		// Remove from list, so return actual object, not a copy
		return employees.remove(id);
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO deep copy
		List<Employee> result = new ArrayList<Employee>(employees.values());
		return result;
	}
}
