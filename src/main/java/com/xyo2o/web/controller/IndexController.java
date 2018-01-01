package com.xyo2o.web.controller;

import javax.servlet.http.HttpSession;

import com.xyo2o.domain.admin.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/index")
public class IndexController {
	@RequestMapping("")
	public String index(HttpSession session){
		System.out.println("index");
		Admin admin=(Admin)session.getAttribute("loginAdmin");
		if(admin==null){
			return "admin/login";
		}
		return "index/index";
	}
}
