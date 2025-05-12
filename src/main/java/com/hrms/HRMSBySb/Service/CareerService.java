package com.hrms.HRMSBySb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.Career;
import com.hrms.HRMSBySb.Repository.CareerRepo;

@Service
public class CareerService {
	@Autowired
	private CareerRepo repo;
	
	public Career saveJob(Career career) {
		return repo.save(career);
	}
	
	public List<Career> allJobs(){
		return repo.findAll();
	}
}
