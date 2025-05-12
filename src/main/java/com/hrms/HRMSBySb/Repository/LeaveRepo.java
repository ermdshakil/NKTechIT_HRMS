package com.hrms.HRMSBySb.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.EmpLeave;

import jakarta.transaction.Transactional;

@Repository
public interface LeaveRepo extends JpaRepository<EmpLeave, Long>{

	@Transactional
	@Modifying
	@Query("update EmpLeave e set e.leaveFrom=?1,e.leaveTo=?2,e.reason=?3,status=?4,appovedBy=?5,approvedAt=?6 where e.id=?7")
	public int UpdateLeave(LocalDate leaveFrom,LocalDate leaveTo,String reason,String status,String appovedBy,LocalDate approvedAt,Long id);
	
	@Query("select e.leaveDay from EmpLeave e where e.id=?1")
	public int leaveDay(Long id);
	
	@Query("select l from EmpLeave l where l.status='Pending'")
	public List<EmpLeave> pendingLeave();
	
	@Transactional
	@Modifying
	@Query("update EmpLeave e set e.leaveFrom=?1, e.leaveTo=?2, e.reason=?3, e.leaveDay=?4, e.status=?5 ,e.appovedBy=?6, e.approvedAt=?7 where e.id=?8")
	public int updateByAdmin(LocalDate leaveFrom,LocalDate leaveTo,String reason,int leaveDay,String status,String approveBy, LocalDate approveAt,long id);
	
}
