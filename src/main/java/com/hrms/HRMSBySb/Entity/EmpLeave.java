package com.hrms.HRMSBySb.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class EmpLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	private LocalDate leaveFrom;
	private LocalDate leaveTo;
	private String reason;
	private int totalLeave;
	private int takenLeave;
	private int leaveDay;
	private String status;
	private String appovedBy;
	private LocalDate approvedAt;
	@ManyToOne
	Employee emp;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getLeaveFrom() {
		return leaveFrom;
	}

	public void setLeaveFrom(LocalDate leaveFrom) {
		this.leaveFrom = leaveFrom;
	}

	public LocalDate getLeaveTo() {
		return leaveTo;
	}

	public void setLeaveTo(LocalDate leaveTo) {
		this.leaveTo = leaveTo;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getTotalLeave() {
		return totalLeave;
	}

	public void setTotalLeave(int totalLeave) {
		this.totalLeave = totalLeave;
	}

	public int getTakenLeave() {
		return takenLeave;
	}

	public void setTakenLeave(int takenLeave) {
		this.takenLeave = takenLeave;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public int getLeaveDay() {
		return leaveDay;
	}

	public void setLeaveDay(int leaveDay) {
		this.leaveDay = leaveDay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppovedBy() {
		return appovedBy;
	}

	public void setAppovedBy(String appovedBy) {
		this.appovedBy = appovedBy;
	}

	public LocalDate getApprovedAt() {
		return approvedAt;
	}

	public void setApprovedAt(LocalDate approvedAt) {
		this.approvedAt = approvedAt;
	}

}
