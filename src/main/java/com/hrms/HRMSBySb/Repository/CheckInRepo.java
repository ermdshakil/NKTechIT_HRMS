package com.hrms.HRMSBySb.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.CheckIn;

import jakarta.transaction.Transactional;

@Repository
public interface CheckInRepo extends JpaRepository<CheckIn, Long>{
	
	@Query("select c from CheckIn c where c.id=?1 And c.date=?2")
	public CheckIn AttendenceExist(long id,LocalDate date);
	
	@Transactional
	@Modifying
	@Query("update CheckIn c set c.outTime=?1 where c.id=?2 and date=?3")
	public int CheckOut(LocalTime outTime,Long id,LocalDate date);
	
	@Query("select c from CheckIn c where c.id = ?1 and c.date = ?2 and c.outTime is not null")
	public CheckIn checkOutExist(long id, LocalDate date);

}
