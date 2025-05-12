package com.hrms.HRMSBySb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.JobSeeker;
import com.hrms.HRMSBySb.Repository.JobSeekerRepo;

@Service
public class JobSeekerService {
	@Autowired
	private JobSeekerRepo repo;

	public String saveData(JobSeeker seeker) {
		repo.save(seeker);
		return "Saved Data";
	}
	
	public List<JobSeeker> jobSeekerList(){
		return repo.findAll();
	}
	
	public List<JobSeeker> pendingList(){
		return repo.pendingList();
	}
	
	public JobSeeker getById(long id) {
		return repo.findById(id).get();
	}
	
	public JobSeeker findByEmpIdAndJobId(Long empId, Long jobId) {
		return repo.findByEmpIdAndJobId(empId, jobId);
	}
	
	public String updateStatus(String status, Long jobId, Long empId) {
		int num=repo.updateStatus(status, jobId,empId);
		if(num>0)
			return "Updated";
		else 
			return "Not Updted";
	}
}
