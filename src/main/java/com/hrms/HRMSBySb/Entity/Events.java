package com.hrms.HRMSBySb.Entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Events {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int srNo;
	private long id;
	private String name;
	private long number;
	@ElementCollection
	private List<String> eventsName;

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
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

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public List<String> getEventsName() {
		return eventsName;
	}

	public void setEventsName(List<String> eventsName) {
		this.eventsName = eventsName;
	}

}
