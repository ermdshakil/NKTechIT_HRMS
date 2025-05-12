
package com.hrms.HRMSBySb.Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrms.HRMSBySb.Entity.Career;
import com.hrms.HRMSBySb.Entity.CheckIn;
import com.hrms.HRMSBySb.Entity.EmpLeave;
import com.hrms.HRMSBySb.Entity.Employee;
import com.hrms.HRMSBySb.Entity.Events;
import com.hrms.HRMSBySb.Entity.JobSeeker;
import com.hrms.HRMSBySb.Entity.Ticket;
import com.hrms.HRMSBySb.Service.CareerService;
import com.hrms.HRMSBySb.Service.CheckInService;
import com.hrms.HRMSBySb.Service.EmployeeService;
import com.hrms.HRMSBySb.Service.EventService;
import com.hrms.HRMSBySb.Service.JobSeekerService;
import com.hrms.HRMSBySb.Service.LeaveService;
import com.hrms.HRMSBySb.Service.TicketService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/employee")
@Controller
public class EmpController {

	@Autowired
	private EmployeeService empService;
	@Autowired
	private CheckInService checkService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private EventService eventService;
	@Autowired
	private CareerService careerService;
	@Autowired
	private JobSeekerService jobSeekerService;

	@RequestMapping("/empLogin")
	public String empLogin() {
		return "EmpLogin";
	}

	@PostMapping("/login")
	public ModelAndView empLogins(@RequestParam String username, @RequestParam String newPass, HttpSession session) {
		Employee emp = empService.empLogin(username, newPass);
		ModelAndView mv = new ModelAndView();
		if (emp != null) {
			session.setAttribute("loggedInEmp", emp);
			mv.setViewName("EmpHome");
		} else {
			mv.addObject("message", "Login Failed. Please try Again");
			mv.setViewName("EmpLogin");
		}
		return mv;
	}

	@GetMapping("/viewProfile")
	public ModelAndView viewProfile(HttpSession session) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("employee", emp);
		mv.setViewName("EmpProfile");
		return mv;
	}

	@GetMapping("/updateProfile")
	public ModelAndView updateProfile(HttpSession session) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("employee", emp);
		mv.setViewName("UpdateEmpProfile");
		return mv;
	}

	@PostMapping("/updateEmpProfile")
	public ModelAndView updateEmpProfile(String firstName, String lastName, Date dob, String department,
			String location, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		int num = empService.updateEmpProfie(firstName, lastName, dob, department, location, emp.getId());
		if (num > 0) {
			Employee updatedEmp = empService.empById(emp.getId());
			session.setAttribute("loggedInEmp", updatedEmp);
			mv.addObject("employee", updatedEmp);
			mv.setViewName("EmpHome");
		} else {
			mv.setViewName("EmpProfile");
		}
		return mv;
	}

	@GetMapping("/contactInfo")
	public ModelAndView contactInfo(HttpSession session) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("employee", emp);
		mv.setViewName("ContactInfo");
		return mv;
	}

	@GetMapping("/changePass")
	public ModelAndView changePass(HttpSession session) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("employee", emp);
		mv.setViewName("ChangeEmpPass");
		return mv;
	}

	@PostMapping("/updateEmpPass")
	public ModelAndView updateEmpPass(HttpSession session, String newPass) {
		ModelAndView mv = new ModelAndView();
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		int num = empService.updateEmpPassword(newPass, emp.getId());
		if (num > 0) {
			Employee updatedEmp = empService.empById(emp.getId());
			session.setAttribute("loggedInEmp", updatedEmp);
			mv.setViewName("EmpLogin");
		}
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
		return "redirect:/employee/empLogin";
	}

	@RequestMapping("/checkInOut")
	public String checkIn() {
		return "checkIn";
	}

	@RequestMapping("/checkIn")
	public ModelAndView checkIn(HttpSession session, CheckIn checkIn) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		int empId = emp.getId();
		CheckIn check = checkService.entryExist(empId, LocalDate.now());
		if (check == null) {
			String name = emp.getFirstName() + " " + emp.getLastName();
			checkIn.setId(empId);
			checkIn.setName(name);
			checkIn.setDate(LocalDate.now());
			checkIn.setInTime(LocalTime.now());
			checkIn.setOutTime(null);
			checkService.checkIn(checkIn);
			mv.setViewName("EmpHome");
		} else {
			mv.addObject("message", "You have already Checked In");
			mv.setViewName("checkIn");
		}
		return mv;
	}

	@RequestMapping("/checkOut")
	public String checkOut() {
		return "checkIn";
	}

	@RequestMapping("/checkItOut")
	public ModelAndView checkOut(HttpSession session, CheckIn checkIn) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		Long empId = (long) emp.getId();
		LocalTime outDate = LocalTime.now();
		LocalDate date = LocalDate.now();
		CheckIn checkOutExist = checkService.chckOutExist(empId, date);
		if (checkOutExist == null) {
			checkService.checkOut(outDate, empId, date);
			mv.setViewName("EmpHome");
		} else {
			mv.addObject("message", "You have already Checked Out");
			mv.setViewName("checkIn");
		}
		return mv;
	}

	@RequestMapping("/leaveApply")
	public ModelAndView applyLeave(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		EmpLeave leave = leaveService.getLeave(emp.getId());
		mv.addObject("leave", leave);
		mv.setViewName("LeaveForm");
		return mv;
	}

	@PostMapping("/apply-leave")
	public ModelAndView submitLeave(HttpSession session, EmpLeave leave) {
		ModelAndView mv = new ModelAndView();
		LocalDate leaveFrom = leave.getLeaveFrom();
		LocalDate leaveTo = leave.getLeaveTo();
		System.out.println("Reason: " + leave.getReason());
		leaveService.updateLeave(leaveFrom, leaveTo, leave.getReason(), leave.getId());
		mv.setViewName("EmpHome");
		return mv;
	}

	@RequestMapping("/viewLeave")
	public ModelAndView viewLeave(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		EmpLeave leave = leaveService.getLeave(emp.getId());
		mv.addObject("leave", leave);
		mv.setViewName("ViewLeave");
		return mv;
	}

	@RequestMapping("/raiseTicket")
	public ModelAndView raiseATicket(HttpSession session) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		mv.addObject("employee", emp);
		mv.setViewName("RaiseTicket");
		return mv;
	}

	@PostMapping("/ticket")
	public ModelAndView ticketRaise(Ticket employee, HttpSession session) {
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		ModelAndView mv = new ModelAndView();
		employee.setEmpId(emp.getId());
		employee.setStatus("Pending");
		employee.setCreatedAt(LocalDate.now());

		System.out.println(employee.getEmpId());
		System.out.println(employee.getUsername());
		System.out.println(employee.getIssueType());

		ticketService.saveTicket(employee);
		mv.setViewName("EmpHome");
		return mv;
	}

	@RequestMapping("/contactHR")
	public String contactHR() {
		return "ContactHR";
	}

	@RequestMapping("/viewTicket")
	public ModelAndView viewTicket(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		List<Ticket> allTicketById = ticketService.allTicketById(emp.getId());
		mv.addObject("tickets", allTicketById);
		mv.setViewName("EmpTicket");
		return mv;
	}

	@RequestMapping("/events")
	public ModelAndView getEvents(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		mv.addObject("employee", emp);
		mv.setViewName("Events");
		return mv;
	}

	@PostMapping("/eventForm")
	public String eventForm(@ModelAttribute Events events) {
		eventService.saveEvent(events);
		return "EmpHome";
	}

	@RequestMapping("/career")
	public ModelAndView allJobs() {
		ModelAndView mv = new ModelAndView();
		List<Career> allJobs = careerService.allJobs();
		mv.addObject("jobs", allJobs);
		mv.setViewName("AllJobs");
		return mv;
	}

	@PostMapping("/jobApply")
	public ModelAndView jobApply(JobSeeker seeker, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Employee emp = (Employee) session.getAttribute("loggedInEmp");
		seeker.setJobId(seeker.getJobId());
		seeker.setEmpId((long) emp.getId());
		System.out.println("Emp ID: " + emp.getId());
		seeker.setEmpName(emp.getFirstName() + " " + emp.getLastName());
		System.out.println("Emp Name: " + emp.getFirstName() + " " + emp.getLastName());
		seeker.setEmail(emp.getEmail());
		System.out.println("Emp Email Id: " + emp.getEmail());
		seeker.setNumber(emp.getPrimaryNum());
		System.out.println("Emp No: " + emp.getPrimaryNum());
		seeker.setDepartment(emp.getDepartment());
		System.out.println("emp Department: " + emp.getDepartment());
		seeker.setGender(emp.getGender());
		Career career = new Career();
		career.setId(seeker.getJobId());
		seeker.setCareer(career);
		seeker.setStatus("Pending");

		JobSeeker jb = jobSeekerService.findByEmpIdAndJobId(seeker.getEmpId(), seeker.getJobId());
		if (jb != null) {
			mv.addObject("message", "You have already applied this job. Please try for another Job requirement");
			mv.setViewName("AllJobs");
		} else {
			jobSeekerService.saveData(seeker);
			mv.setViewName("EmpHome");
		}
		return mv;
	}
	
	@RequestMapping("/aboutUs")
	public String aboutUs() {
		return "AboutUs";
	}

}
