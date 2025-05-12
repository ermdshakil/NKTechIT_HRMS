package com.hrms.HRMSBySb.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.Events;
import com.hrms.HRMSBySb.Repository.EventRepo;

@Service
public class EventService {
	
	@Autowired
	private EventRepo eventRepo;
	
	public String saveEvent(Events event) {
		eventRepo.save(event);
		return "submitted";
	}
}
