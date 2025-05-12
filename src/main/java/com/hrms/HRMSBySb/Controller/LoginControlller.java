package com.hrms.HRMSBySb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginControlller {

	@RequestMapping("/")
	public String getAdminHomePage() {
		return "FirstPage";
	}
}
