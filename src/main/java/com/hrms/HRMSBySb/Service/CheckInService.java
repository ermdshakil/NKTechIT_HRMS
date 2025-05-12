package com.hrms.HRMSBySb.Service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.CheckIn;
import com.hrms.HRMSBySb.Repository.CheckInRepo;

@Service
public class CheckInService {

	@Autowired
	CheckInRepo checkRepo;
	
	public String checkIn(CheckIn checkIn) {
		checkRepo.save(checkIn);
		return "Added successfully";
	}
	
	public CheckIn entryExist(long id,LocalDate date) {
		return checkRepo.AttendenceExist(id, date);
	}
	
	public int checkOut(LocalTime outTime,Long id,LocalDate date) {
		return checkRepo.CheckOut(outTime,id,date);
	}
	
	public CheckIn chckOutExist(long id,LocalDate date) {
		return checkRepo.checkOutExist(id, date);
	}
}
