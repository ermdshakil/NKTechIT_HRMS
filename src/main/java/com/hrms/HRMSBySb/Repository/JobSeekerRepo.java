package com.hrms.HRMSBySb.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.JobSeeker;

import jakarta.transaction.Transactional;

@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Long> {
	@Query("select s from JobSeeker s where s.empId=?1 and s.jobId=?2")
	public JobSeeker findByEmpIdAndJobId(Long empId, Long jobId);
	
	@Transactional
	@Modifying
	@Query("update JobSeeker s set s.status=?1 where s.jobId=?2 and s.empId=?3")
	public int updateStatus(String status,Long jobId,Long empId);
	
	@Query("select s from JobSeeker s where s.status='Pending'")
	public List<JobSeeker> pendingList();
}
