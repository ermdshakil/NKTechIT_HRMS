package com.hrms.HRMSBySb.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.HRMSBySb.Entity.Employee;
import com.hrms.HRMSBySb.Repository.EmployeeRepo;
import com.hrms.HRMSBySb.Repository.LeaveRepo;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepo empRepo;
	@Autowired
	LeaveRepo leaveRepo;
	
	public Employee empLogin(String username, String newPass) {
		return empRepo.empLogin(username, newPass);
	}

	public Employee addEployeeData(Employee employee) {
		employee.setUsername(employee.getFirstName().toLowerCase() + "." + employee.getLastName().toLowerCase());
		employee.setPassword("Password@123");
		employee.setNewPass("Password@123");
		return empRepo.save(employee);
	}

	public Employee getEmpByEmail(String email, Long primaryNum) {
		return empRepo.getEmpByNo(email, primaryNum);
	}

	public List<Employee> empList() {
		return empRepo.findAll();
	}

	public Employee empById(int id) {
		Optional<Employee> opt = empRepo.findById(id);
		return opt.orElse(null);
	}

	public String deleteEmp(int id) {
		Employee emp = empById(id);
		if (emp != null) {
			leaveRepo.deleteById((long) id);
			empRepo.deleteById(id);
			return "Deleted";
		} else {
			return "Not Present";
		}
	}

	public String updateByEmp(Employee emp) {
		Employee existing = empById(emp.getId());
		if (existing != null) {
			emp.setUsername(emp.getFirstName().toLowerCase() + "." + emp.getLastName().toLowerCase());
			emp.setPassword("Password@123");
			emp.setNewPass(existing.getNewPass());
			System.out.println("New Password: "+emp.getNewPass());
			empRepo.save(emp);
			return "updated";
		}
		return "error";
	}

	public List<DepartmentStats> fetchDepartmentStats() {
		return empRepo.getDepartmentEmp();
	}

	public int updateEmpProfie(String firstName, String lastName, Date dob, String department, String location,
			int id) {
		return empRepo.updateEmpProfile(firstName, lastName, dob, department, location, id);
	}

	public int updateEmpPassword(String newPass, int id) {
		return empRepo.updateEmpPassword(newPass, id);
	}

}
