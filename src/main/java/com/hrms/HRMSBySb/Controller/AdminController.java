package com.hrms.HRMSBySb.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.HRMSBySb.Entity.Admin;
import com.hrms.HRMSBySb.Entity.Career;
import com.hrms.HRMSBySb.Entity.EmpLeave;
import com.hrms.HRMSBySb.Entity.Employee;
import com.hrms.HRMSBySb.Entity.JobSeeker;
import com.hrms.HRMSBySb.Entity.Ticket;
import com.hrms.HRMSBySb.Service.AdminService;
import com.hrms.HRMSBySb.Service.CareerService;
import com.hrms.HRMSBySb.Service.DepartmentStats;
import com.hrms.HRMSBySb.Service.EmailService;
import com.hrms.HRMSBySb.Service.EmployeeService;
import com.hrms.HRMSBySb.Service.JobSeekerService;
import com.hrms.HRMSBySb.Service.LeaveService;
import com.hrms.HRMSBySb.Service.TicketService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	private EmployeeService empService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private CareerService careerService;
	@Autowired
	private JobSeekerService jobSeekerService;
	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/login")
	public String adminLogin() {
		return "AdminLogin";
	}
	
	@PostMapping("/adminLogin")
	public ModelAndView adminLogins(@RequestParam String username, @RequestParam String password,HttpSession session) {
		Admin admin=adminService.adminLogin(username, password);
		ModelAndView mv=new ModelAndView();
		if(admin!=null) {
			session.setAttribute("loggedInEmp", admin);
			mv.setViewName("AdminHome");
		}
		else {
			mv.addObject("message","Login Failed. Please try Again");
			mv.setViewName("AdminLogin");
		}
		return mv;
	}
	
	@RequestMapping("/home")
	public String adminHome() {
		return "AdminHome";
	}

	@RequestMapping("/Dashboard")
	public String showDashboard() {
		return "Dashboard";
	}

	@RequestMapping("/EmployeeReg")
	public String showAdminRegForm() {
		return "EmployeeReg";
	}
	
	@PostMapping("/searchByEmail")
	public Employee empByEmail(@RequestParam  String email, @RequestParam Long primaryNum) {
		return empService.getEmpByEmail(email, primaryNum);
	}
	
	@PostMapping("/addEmp")
	public ModelAndView addEmployee(@ModelAttribute Employee employee,EmpLeave leave) {
		ModelAndView mv=new ModelAndView();
		Employee emp=empByEmail(employee.getEmail(),employee.getPrimaryNum());
		if(emp==null) {
//			Adding Leave data in Employee Leave Table simultaneously			
			LocalDate leaveFrom=LocalDate.now();
			LocalDate leaveTo=LocalDate.now();
			leave.setName(employee.getFirstName()+" "+employee.getLastName());
			leave.setEmail(employee.getEmail());
			leave.setLeaveFrom(leaveFrom);
			leave.setLeaveTo(leaveTo);
			leave.setReason("");
			leave.setTotalLeave(26);
			leave.setTakenLeave(0);
			leave.setLeaveDay(0);
			leave.setId(employee.getId());
			leaveService.saveLeave(leave);
			
			
//		Adding New Employee to the employee table			
		Employee empl=empService.addEployeeData(employee);
		mv.addObject("employee", empl);
		mv.setViewName("EmployeeList");
		return mv;
		}
		else {
			mv.addObject("message", "Email Id Or Mobile Number Already Exist in DB");
			mv.setViewName("EmployeeReg");
			return mv;
		}
	}
	
	@RequestMapping("/AllEmployee")
	public ModelAndView allEmployee() {
		ModelAndView mv=new ModelAndView();
		List<Employee> list=empService.empList();
		mv.addObject("employee", list);
		mv.setViewName("EmployeeList");
		return mv;
	}
	
	@RequestMapping("/AdminReg")
	public String adminRegForm() {
		return "AdminRegForm";
	}
	
	@PostMapping("/addAdmin")
	public ModelAndView AddAdmin(@ModelAttribute Admin admin) {
		ModelAndView mv=new ModelAndView();
		Admin ad=adminService.getByEmail(admin.getNumber(), admin.getEmail());
		if(ad==null) {			
		Admin data=adminService.saveAdmin(admin);
		mv.addObject("admin", data);
		mv.setViewName("AdminList");
		return mv;
		}
		else {
			mv.addObject("message", "Admin details is Already Exist in database.");
			mv.setViewName("AdminRegForm");
			return mv;
		}
	}
	
	@RequestMapping("/AllAdmin")
	public ModelAndView allAdmin() {
		ModelAndView mv=new ModelAndView();
		List<Admin> list=adminService.adminList();
		mv.addObject("admin", list);
		mv.setViewName("AdminList");
		return mv;
	}
	
	@GetMapping("/EmpById")
    public String getEmpById() {
        return "EmpById";
    }

    @PostMapping("/SearchById")
    public ModelAndView searchEmpById(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("EmpById");
        Employee emp = empService.empById(id);
        
        if (emp != null) {
            mv.addObject("employee", emp);
        } else {
            mv.addObject("message", "No employee present");
        }
        return mv;
    }
    
    @GetMapping("/comingSoon")
    public String getDepartment() {
        return "ComingSoon";
    }
    
    @GetMapping("/deleteEmp")
    public String deleteEmp() {
    	return "DeleteEmpById";
    }
    
    @RequestMapping("/deleteById")
    public ModelAndView deleteEMp(@RequestParam int id) {
    	String st=empService.deleteEmp(id);
    	ModelAndView mv=new ModelAndView();
    	try {
	        if (st.equals("Deleted")) {
	        	mv.addObject("messageDel","Employee Deleted Successfull");
	        	mv.setViewName("DeleteEmpById");
	        	return mv;	        	
	        } else {
	        	mv.addObject("message","Data Not Present");
	        	mv.setViewName("DeleteEmpById");
	        	return mv;
	        }
	    } catch (Exception e) {
	    	mv.addObject("message","Data Not Present");
        	mv.setViewName("DeleteEmpById");
        	return mv;
	    }
    }
    
    @GetMapping("/updateEmp")
    public String updateEmp() {
        return "UpdateEmp2"; 
    }

    @PostMapping("/updateById")
    public ModelAndView empData(@RequestParam int id) {
        ModelAndView mv = new ModelAndView();
        Employee emp = empService.empById(id);
        if (emp != null) {
            mv.addObject("employee", emp);
        } else {
            mv.addObject("message", "No employee present");
        }
        mv.setViewName("UpdateEmp2");
        return mv;
    }

    @PostMapping("/update")
    public String updateById(@ModelAttribute Employee emp) {
        String status = empService.updateByEmp(emp);
        System.out.println("Status: " + status);
        if ("updated".equals(status))
            return "redirect:AllEmployee";
        else
            return "redirect:updateEmp";
    }
    
    @GetMapping("/departmentStats")
    public String viewDepartmentStats(Model model) {
        List<DepartmentStats> stats = empService.fetchDepartmentStats();
        model.addAttribute("deptStats", stats);
        return "DepartmentStats";
    }
    
    @RequestMapping("/holiday")
    public String holiday() {
    	return "Holiday.html";
    }
    
    @RequestMapping("/policy")
    public String policy() {
    	return "Policy.html";
    }
    
    @RequestMapping("/allTickets")
	public ModelAndView viewTicket() {
		ModelAndView mv=new ModelAndView();
		List<Ticket> allTicketById=ticketService.allTicket();
		mv.addObject("tickets",allTicketById);
		mv.setViewName("AllTicket");
		return mv;
	}
        
    @GetMapping("/updateTicket")
    public String updateTicket() {
        return "UpdateEmpTicket"; 
    }

    @PostMapping("/ticketById")
    public ModelAndView ticketData(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView();
        Ticket ticket = ticketService.ticketByID(id);
        if (ticket != null) {
            mv.addObject("ticket", ticket);
        } else {
            mv.addObject("message", "No Ticket present");
        }
        mv.setViewName("UpdateEmpTicket");
        return mv;
    }

    @PostMapping("/updateTicketById")
    public String updateTicket(@ModelAttribute Ticket ticket,HttpSession session) {
    	Admin admin = (Admin) session.getAttribute("loggedInEmp");
    	if(admin!=null) {
        ticket.setUpdateAt(LocalDate.now());
        ticket.setUpdateBy(admin.getFirstName()+" "+admin.getLastName()); 
        ticketService.saveTicket(ticket);
        return "redirect:/admin/allTickets"; 
    	}else {
    		return "redirect:/admin/login";
    	}
    }
    
    @GetMapping("/searchTicketById")
    public String searchTicketById() {
        return "TicketById";
    }

    @PostMapping("/searchTicket")
    public ModelAndView searchTicket(@RequestParam Long ticketId) {
        ModelAndView mv = new ModelAndView("TicketById");
        Ticket ticket=ticketService.ticketByID(ticketId);
        if (ticket != null) {
            mv.addObject("ticket", ticket);
        } else {
            mv.addObject("message", "No ticket present");
        }
        return mv;
    }
    
    @GetMapping("/viewProfile")
	public ModelAndView viewProfile(HttpSession session) {
    	Admin admin = (Admin) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("admin", admin);
		mv.setViewName("AdminProfile");
		return mv;
	}
    
    @GetMapping("/updateProfile")
	public ModelAndView updateProfile(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("admin", admin);
		mv.setViewName("UpdateAdminProfile");
		return mv;
	}

	@PostMapping("/updateAdminProfile")
	public ModelAndView updateEmpProfile(String firstName, String lastName, Date dob, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Admin admin = (Admin) session.getAttribute("loggedInEmp");
		int num=adminService.updateAdminProfie(firstName, lastName, dob, admin.getId());
		if (num > 0) {
			Admin updateAdmin=adminService.adminById(admin.getId());
			session.setAttribute("loggedInEmp", updateAdmin);
			mv.addObject("admin", updateAdmin);
		}
		mv.setViewName("AdminProfile");
		return mv;
	}
	
	@GetMapping("/contactInfo")
	public ModelAndView adminContactInfo(HttpSession session) {
		Admin admin = (Admin) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("admin", admin);
		mv.setViewName("AdminContactInfo");
		return mv;
	}
	
	@RequestMapping("/logout")
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "redirect:/admin/login";
	}
	
	@RequestMapping("/allLeaves")
	public ModelAndView allPendingLeaves() {
		ModelAndView mv=new ModelAndView();
		List<EmpLeave> pendingLeave=leaveService.pendingLeave();
		mv.addObject("leaves",pendingLeave);
		mv.setViewName("AllPendingLeave2");
		return mv;
	}
	
	@PostMapping("/updateLeave")
	public String updateLeave(LocalDate leaveFrom,LocalDate leaveTo,String reason,String status,long id,HttpSession session) {
		System.out.println("Reason in Admin Controller Section: "+reason);
		Admin admin = (Admin) session.getAttribute("loggedInEmp");
		String name=admin.getFirstName()+" "+admin.getLastName();
		leaveService.updateByAdmin(leaveFrom, leaveTo, reason, status, name, LocalDate.now(), id);
		return "redirect:/admin/allLeaves";
	}
	
	@RequestMapping("/jobPost")
	public String jobPost() {
		return "Career";
	}
	
	@PostMapping("/upload-job")
	public ModelAndView postJob(Career career,HttpSession session) {
		ModelAndView mv=new ModelAndView();
		Admin admin = (Admin) session.getAttribute("loggedInEmp");
		career.setPostDate(LocalDate.now());
		career.setPostedBy(admin.getFirstName()+" "+admin.getLastName());
		careerService.saveJob(career);
		mv.setViewName("AdminHome");
		return mv;
	}
	
	@RequestMapping("/postedJobs")
	public ModelAndView allJobs() {
		ModelAndView mv=new ModelAndView();
		List<Career> list=careerService.allJobs();
		mv.addObject("jobs", list);
		mv.setViewName("JobList");
		return mv;
	}
	
	@RequestMapping("/jobSeekerList")
	public ModelAndView jobSeekerList(){
		ModelAndView mv=new ModelAndView();
		List<JobSeeker> jobSeeker= jobSeekerService.pendingList();
		for(JobSeeker job:jobSeeker) {
			System.out.println(job.getJobId()+" "+job.getEmpName()+" "+job.getEmail());
		}
		mv.addObject("jobSeeker", jobSeeker);
		mv.setViewName("JobSeekerList");
		return mv;
	}
	
	@PostMapping("/jobApply")
	public String applyJob(@RequestParam Long jobId,
	                       @RequestParam Long empId,
	                       @RequestParam String status, HttpSession session) {
		Admin admin = (Admin) session.getAttribute("loggedInEmp");
		jobSeekerService.updateStatus(status, jobId, empId);
	    JobSeeker seeker = jobSeekerService.findByEmpIdAndJobId(empId, jobId);    
	    emailService.sendEmail(seeker, status,admin.getEmail());
	    return "redirect:/admin/jobSeekerList";
	}

       
}
