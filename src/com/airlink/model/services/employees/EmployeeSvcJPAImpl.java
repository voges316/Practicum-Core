package com.airlink.model.services.employees;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airlink.model.domain.Employee;
import com.airlink.model.domain.Job;
import com.airlink.model.services.AbstractDBSvc;

public class EmployeeSvcJPAImpl extends AbstractDBSvc implements IEmployeeSvc {

	static final Logger logger = LoggerFactory.getLogger(EmployeeSvcJPAImpl.class);
	
	@Override
	public Employee createEmployee(Employee employee) {
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		
		logger.info("createEmployee()");
		return employee;
	}

	@Override
	public Employee getEmployee(int id) {
		Employee e = em.find(Employee.class, id);
		
		logger.info("getEmployee()");
		return e;
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		em.getTransaction().begin();
		em.merge(employee);
		em.getTransaction().commit();
		
		logger.info("updateEmployee()");
		return employee;
	}
	
	@Override
	public Employee deleteEmployee(Employee employee) {
		Employee e = em.find(Employee.class, employee.getId());
		
		if (e == null) return null;
		
		em.getTransaction().begin();
		if (e.getJobs().size() > 0) {
			// Delete from the jobs
			for (Job j : e.getJobs()) {
				j.getEmployees().remove(e);
			}
			// Delete employee's job list
			e.getJobs().clear();
			// Should persist job changes
			em.merge(e);
		}
		
		// Delete the job
		em.remove(e);
		em.getTransaction().commit();
		
		logger.info("deleteEmployee()");
		return e;
	}

	@Override
	public List<Employee> getEmployees() {
		
		Query query = em.createQuery("SELECT e FROM Employee e");
		logger.info("getEmployees()");
		
		return (List<Employee>)query.getResultList();
	}
}
