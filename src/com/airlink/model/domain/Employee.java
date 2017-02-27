package com.airlink.model.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Employee {
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String midName;
	private String lastName;
	private String email;
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private Rank rank;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval=true)
	private Address address;
	
	@ManyToMany(mappedBy="employees")
	private Set<Job> jobs;
	
	public Employee() {
		this.jobs = new HashSet<>();
	}

	public Employee(String firstName, String lastName, String email, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.jobs = new HashSet<>();
	}
	
	public Employee(Employee employee) {
		this.id = employee.getId();
		this.firstName = employee.getFirstName();
		this.midName = employee.getMidName();
		this.lastName = employee.getLastName();
		this.email = employee.getEmail();
		this.phone = employee.getPhone();
		this.rank = employee.getRank();
		this.gender = employee.getGender();
		this.address = employee.getAddress();
		// Work up to this
		// this.jobs = employee.getJobs();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
	
	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/**
	 * Employees are considered equal if their first & last names and email match
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", midName=" + midName + ", lastName=" + lastName + ", email="
				+ email + "]";
	}
	
	/**
	 * Employee is valid if it has first name, last name, and email
	 * 
	 * @return
	 */
	public boolean validate() {
		if (firstName == null || firstName.equals("")) return false;
        if (lastName == null || lastName.equals("")) return false;
        if (email == null || email.equals("")) return false;

        return true;
	}
	
}
