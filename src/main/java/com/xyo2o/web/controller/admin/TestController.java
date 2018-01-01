package com.xyo2o.web.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xyo2o.domain.admin.Admin;
import com.xyo2o.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private AdminService adminService;
	@RequestMapping("/test")
	public String getAll(HttpServletRequest request){
		return "test";
	}
	@RequestMapping("/insert")
	public String insert(){
		Admin admin = new Admin();
		admin.setUsername("abcd");
		admin.setPassword("abcd");
		admin.setName("ABC");
		
		int i = adminService.insert(admin);
		if (i>0) {
			System.out.println("ok");
		}else {
			System.out.println("no");
		}
		return "";
	}
	
}
