package com.airlink.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table
public class Job {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private int numberNeeded;
	
	@ManyToMany
	@JoinTable(
			name="JOB_EMP",
			joinColumns=@JoinColumn(name="JOB_ID", referencedColumnName="ID"),
			inverseJoinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="ID"))
	private Set<Employee> employees;
	
	public Job() {
		this.employees = new HashSet<>();
	}
	
	public Job(String name, int numberNeeded) {
		super();
		this.name = name;
		this.numberNeeded = numberNeeded;
		this.employees = new HashSet<>();
	}

	public Job(Job job) {
		this.id = job.getId();
		this.name = job.getName();
		this.description = job.getDescription();
		this.numberNeeded = job.getNumberNeeded();
		// Need to work up to this
		// this.employees = job.getEmployees();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberNeeded() {
		return numberNeeded;
	}

	public void setNumberNeeded(int numberNeeded) {
		this.numberNeeded = numberNeeded;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Jobs are equal if the names match
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + ", numberNeeded=" + numberNeeded + "]";
	}
	
	/**
	 * Job is valid if it has a name
	 * 
	 * @return
	 */
	public boolean validate() {
		if (name == null || name.equals("")) return false;
		
		return true;
	}
}
