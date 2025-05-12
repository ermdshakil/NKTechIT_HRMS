package com.hrms.HRMSBySb.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.Admin;
import com.hrms.HRMSBySb.Repository.AdminRepo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	public Admin adminLogin(String username,String password) {
		return adminRepo.adminLogin(username, password);
	}
	
	public Admin saveAdmin(Admin admin) {
		admin.setUsername(admin.getFirstName().toLowerCase()+"."+admin.getLastName().toLowerCase());
		admin.setPassword("Password@123");
		return adminRepo.save(admin);
	}
	
	public List<Admin> adminList(){
		return adminRepo.findAll();
	}
	
	public Admin getByEmail(Long number,String email) {
		return adminRepo.getByEmail(number, email);
	}
	
	public int updateAdminProfie(String firstName, String lastName, Date dob, int id) {
		return adminRepo.updateEmpProfile(firstName, lastName, dob, id);
	}
	
	public Admin adminById(int id) {
		return adminRepo.findById(id).orElse(null);
	}
	
}
