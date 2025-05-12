package com.hrms.HRMSBySb.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class JobSeeker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long srNo;
	private Long jobId;
	private Long empId;
	private String empName;
	private String email;
	private Long number;
	private String department;
	private String gender;
	private String status;
	@ManyToOne
	Career career;

	public Long getSrNo() {
		return srNo;
	}

	public void setSrNo(Long srNo) {
		this.srNo = srNo;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
