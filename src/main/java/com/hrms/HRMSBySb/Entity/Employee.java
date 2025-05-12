package com.hrms.HRMSBySb.Entity;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String department;
	private String location;
	private Date joiningDate;
	private String blood;
	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true)
	private long primaryNum;
	private long secondryNum;
	private String contactAdd;
	private String permanentAdd;
	private String username;
	private String password;
	private String newPass;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPrimaryNum() {
		return primaryNum;
	}

	public void setPrimaryNum(long primaryNum) {
		this.primaryNum = primaryNum;
	}

	public long getSecondryNum() {
		return secondryNum;
	}

	public void setSecondryNum(long secondryNum) {
		this.secondryNum = secondryNum;
	}

	public String getContactAdd() {
		return contactAdd;
	}

	public void setContactAdd(String contactAdd) {
		this.contactAdd = contactAdd;
	}

	public String getPermanentAdd() {
		return permanentAdd;
	}

	public void setPermanentAdd(String permanentAdd) {
		this.permanentAdd = permanentAdd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

}
