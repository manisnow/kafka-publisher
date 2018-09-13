package com.thamiz.message;

import java.util.Date;

public class Employee {

	/*
	 * emp.setEmpId(101); emp.setFirstName("Ninad"); emp.setLastName("Ingole");
	 * emp.setJoiningDate(new Date());
	 */
	Long empId;
	String firstName;
	String LastName;
	Date joiningDate;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

}
