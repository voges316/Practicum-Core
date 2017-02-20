package com.airlink.model.services.employees;

import java.util.List;

import com.airlink.model.domain.Employee;

public interface IEmployeeSvc {

	public Employee createEmployee(Employee employee);
	
	public Employee getEmployee(int id);
	
	public Employee updateEmployee(Employee employee);
	
	public Employee deleteEmployee(Employee employee);
	
	public List<Employee> getEmployees();
}
