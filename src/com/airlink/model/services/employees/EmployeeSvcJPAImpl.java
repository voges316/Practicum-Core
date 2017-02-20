package com.airlink.model.services.employees;

import java.util.List;

import javax.persistence.Query;

import com.airlink.model.domain.Employee;
import com.airlink.model.services.AbstractDBSvc;

public class EmployeeSvcJPAImpl extends AbstractDBSvc implements IEmployeeSvc {

	@Override
	public Employee createEmployee(Employee employee) {
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		
		System.out.println("createEmployee()");
		return employee;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee e = em.find(Employee.class, id);
		
		System.out.println("getEmployee()");
		return e;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
		
		System.out.println("updateEmployee()");
		return employee;
	}

	@Override
	public Employee deleteEmployee(int id) {
		Employee e = em.find(Employee.class, id);
		
		if (e == null) return null;
		
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		
		System.out.println("deleteEmployee()");
		return e;
	}

	@Override
	public List<Employee> getEmployees() {
		
		Query query = em.createQuery("SELECT e FROM Employee e");
		System.out.println("getEmployees()");
		
		return (List<Employee>)query.getResultList();
	}
}
