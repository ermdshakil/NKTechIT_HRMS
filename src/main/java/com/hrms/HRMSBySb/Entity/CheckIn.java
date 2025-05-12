package com.hrms.HRMSBySb.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CheckIn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long srNo;
	private long id;
	private String name;
	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;

	public long getSrNo() {
		return srNo;
	}

	public void setSrNo(long srNo) {
		this.srNo = srNo;
	}

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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}

	public LocalTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}

}
