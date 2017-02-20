package com.airlink.model.services.employees;

import java.util.List;

import com.airlink.model.domain.Employee;

public class EmployeeTest {

	public static void main(String[] args) {
		// Ceate employee
		Employee emp = new Employee("Bart", "Simpson", "bart@gmail.com", "12345");
		// System.out.println(emp.toString());
		// System.out.println(emp.validate());
		
		IEmployeeSvc empSvc = new EmployeeSvcJavaImpl();
		empSvc.createEmployee(emp);
				
		Employee emp2 = new Employee("Lisa", "Simpson", "lisa@gmail.com", "12345");
		Employee emp3 = new Employee("Homer", "Simpson", "homer@duff.com", "12345");
		empSvc.createEmployee(emp2);
		empSvc.createEmployee(emp3);
		
		// Get values
		List<Employee> emps = empSvc.getEmployees();
		for (Employee e : emps) {
			System.out.println(e);
		}
		
		Employee empX = empSvc.getEmployee(2);
		System.out.println("Retrieved employee");
		System.out.println(empX);
		
		empX.setEmail("homer@homersimpson.com");
		empSvc.updateEmployee(empX);
		
		System.out.println("After updating");
		emps = empSvc.getEmployees();
		for (Employee e : emps) {
			System.out.println(e);
		}
		
		// Deleting
		empSvc.deleteEmployee(1);
		System.out.println("After deleting");
		emps = empSvc.getEmployees();
		for (Employee e : emps) {
			System.out.println(e);
		}
	}

}
