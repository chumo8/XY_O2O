package com.xyo2o.web.controller.store;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xyo2o.domain.store.StoreAdmin;
import com.xyo2o.dto.ResultDTO;
import com.xyo2o.dto.store.StoreAdminQueryPageDTO;
import com.xyo2o.service.store.StoreAdminService;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * 店铺管理员
 * @author YLJ
 *
 */
@Controller
@RequestMapping("/storeAdmin")
public class StoreAdminController {
	@Autowired
	private StoreAdminService storeAdminService;
	
	//跳转店铺管理员管理页面
	@RequestMapping("/list")
	public String showStoreAdmin(){
		return "store/admin";
	}
	//高级查询加分页
	@RequestMapping("/queryData")
	@ResponseBody
	public Map<String, Object> queryData(StoreAdminQueryPageDTO dto, int page, int limit, String username){
		dto.setUsername(username);
		dto.setCurrentPage(page);
		dto.setPageSize(limit);
		List<StoreAdmin> list = storeAdminService.query(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", dto.getTotal());
		map.put("data", list);
		return map;
	}
	
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request){
		System.out.println("findAll");
		List<StoreAdmin> list = storeAdminService.findAll();
		request.setAttribute("list", list);
		return "store/store";
	}
	//跳转添加店铺管理员页面
	@RequestMapping("/showAdd")
	public String showAdd(){
		return "store/addStoreAdmin";
	}
	//添加店铺管理员
	@RequestMapping("/addStoreAdmin")
	@ResponseBody
	public ResultDTO addStoreAdmin(String username, String password, String nickname, int cmd, int id){
		ResultDTO dto;
		StoreAdmin admin = new StoreAdmin();
		admin.setUserName(username);
		admin.setPassWord(password);
		admin.setNickName(nickname);
		admin.setRegisterDate(new Date());
		dto = ResultDTO.newInStrance("", 0);
		return dto;
	}
}
