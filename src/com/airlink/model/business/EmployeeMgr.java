package com.airlink.model.business;

import java.util.List;

import com.airlink.model.domain.Employee;
import com.airlink.model.services.employees.EmployeeSvcJPAImpl;
import com.airlink.model.services.employees.IEmployeeSvc;

public class EmployeeMgr extends ManagerSupertype {

	private static IEmployeeSvc employeeSvc = new EmployeeSvcJPAImpl();
	private static EmployeeMgr instance;
	
	private EmployeeMgr() {}
	
	public static synchronized EmployeeMgr getInstance() {
		if (instance == null) {
			instance = new EmployeeMgr();
		}
		return instance;
	}
	
	public Employee registerEmployee(Employee employee) {
		Employee result = employeeSvc.createEmployee(employee);
		return result;
	}
	
	public Employee retrieveEmployee(int id) {
		Employee result = employeeSvc.getEmployee(id);
		return result;
	}
	
	public Employee updateEmployee(Employee employee) {
		Employee result = employeeSvc.updateEmployee(employee);
		return result;
	}
	
	public Employee deleteEmployee(Employee employee) {
		Employee result = employeeSvc.deleteEmployee(employee);
		return result;
	}
	
	public List<Employee> getEmployees() {
		List<Employee> result = employeeSvc.getEmployees();
		return result;
	}
}
