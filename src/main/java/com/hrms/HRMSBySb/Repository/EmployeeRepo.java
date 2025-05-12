package com.hrms.HRMSBySb.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrms.HRMSBySb.Entity.Employee;
import com.hrms.HRMSBySb.Service.DepartmentStats;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	@Query("SELECT e.department AS department, COUNT(e) AS totalEmployees FROM Employee e GROUP BY e.department")
	List<DepartmentStats> getDepartmentEmp();
	
	@Query("Select e from Employee e where e.email=?1 OR e.primaryNum=?2")
	public Employee getEmpByNo(String email,Long primaryNum);
	
	@Query("select e from Employee e where e.username=?1 And e.newPass=?2")
	public Employee empLogin(String username,String newPass);
	
	
	@Transactional
	@Modifying
	@Query("update Employee e set e.firstName=?1,e.lastName=?2,e.dob=?3,e.department=?4,e.location=?5 where e.id=?6")
	public int updateEmpProfile(String firstName,String lastName,Date dob,String department,String location,int id);
	
	@Transactional
	@Modifying
	@Query("update Employee e set e.newPass=?1 where e.id=?2")
	public int updateEmpPassword(String newPass,int id);

}
