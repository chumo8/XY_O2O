package com.xyo2o.web.controller.admin;

import com.xyo2o.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sys")
public class AdminController {
	@Autowired
	private AdminService adminService;

	/**
	 * 跳转到 用户列表页面
	 */
	@RequestMapping("/list")
	public String list(){
		return "admin/admin_list";
	}

}
