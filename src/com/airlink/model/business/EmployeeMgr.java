package com.airlink.model.business;

import java.util.List;

import com.airlink.model.domain.Employee;
import com.airlink.model.services.employees.EmployeeSvcJavaImpl;
import com.airlink.model.services.employees.IEmployeeSvc;

public class EmployeeMgr extends ManagerSupertype {

	private static IEmployeeSvc employeeSvc = new EmployeeSvcJavaImpl();
	private static EmployeeMgr instance;
	
	private EmployeeMgr() {}
	
	public static synchronized EmployeeMgr getInstance() {
		if (instance == null) {
			instance = new EmployeeMgr();
			
			// TODO remove. for dummy testing
			employeeSvc.createEmployee(
					new Employee("Bart", "Simpson", "bart@gmail.com", "12345"));
			employeeSvc.createEmployee(
					new Employee("Lisa", "Simpson", "lisa@gmail.com", "12345"));
			employeeSvc.createEmployee(
					new Employee("Homer", "Simpson", "homer@duff.com", "12345"));
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
	
	public Employee deleteEmployee(int id) {
		Employee result = employeeSvc.deleteEmployee(id);
		return result;
	}
	
	public List<Employee> getEmployees() {
		List<Employee> result = employeeSvc.getEmployees();
		return result;
	}
}
