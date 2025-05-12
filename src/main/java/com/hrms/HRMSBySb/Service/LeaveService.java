package com.hrms.HRMSBySb.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.EmpLeave;
import com.hrms.HRMSBySb.Repository.LeaveRepo;

@Service
public class LeaveService {

	@Autowired
	LeaveRepo repo;

	public EmpLeave getLeave(long id) {
		return repo.findById(id).get();
	}

	public int leaveDay(Long id) {
		return repo.leaveDay(id);
	}

	public int updateLeave(LocalDate leaveFrom, LocalDate leaveTo, String reason, Long id) {
		return repo.UpdateLeave(leaveFrom, leaveTo, reason,"Pending",null,null, id);
	}

	public String saveLeave(EmpLeave leave) {
		repo.save(leave);
		return "Saved Successfully";
	}

	public List<EmpLeave> pendingLeave() {
		return repo.pendingLeave();
	}

	public int updateByAdmin(LocalDate leaveFrom, LocalDate leaveTo, String reason, String status, String approveBy,
			LocalDate approveAt, long id) {
		int leaveDays = leaveDay(id);
		int daysBetween = (int) (ChronoUnit.DAYS.between(leaveFrom, leaveTo) + 1);
		int updatedLeave = 0;
		System.out.println("Reason in updateByAdmin method: "+reason);
		if (status.equalsIgnoreCase("Approve")) {
			updatedLeave = leaveDays + daysBetween;
			return repo.updateByAdmin(leaveFrom, leaveTo, reason, updatedLeave, status, approveBy, approveAt, id);
		} else {
			updatedLeave = leaveDays + 0;
			return repo.updateByAdmin(leaveFrom, leaveTo, reason, updatedLeave, status, approveBy, approveAt, id);
		}
	}
}
