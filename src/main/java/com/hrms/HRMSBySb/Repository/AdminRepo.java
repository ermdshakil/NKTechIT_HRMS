package com.hrms.HRMSBySb.Repository;


import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.Admin;

import jakarta.transaction.Transactional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{

	@Query("select a from Admin a where a.number=?1 Or a.email=?2")
	public Admin getByEmail(Long number,String email);
	
	@Query("select a from Admin a where a.username=?1 And a.password=?2")
	public Admin adminLogin(String username,String password);
	
	@Transactional
	@Modifying
	@Query("update Admin a set a.firstName=?1,a.lastName=?2,a.dob=?3 where a.id=?4")
	public int updateEmpProfile(String firstName,String lastName,Date dob,int id);
}
