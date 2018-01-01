package com.xyo2o.web.controller.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.xyo2o.domain.user.Info;
import com.xyo2o.domain.user.User;
import com.xyo2o.domain.user.Wallet;
import com.xyo2o.dto.ResultDTO;
import com.xyo2o.dto.user.UserQueryPageDTO;
import com.xyo2o.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	//跳转页面
	@RequestMapping("/list")
	public String list(){
		return "user/user";
	}
	//高级查询加分页
	@RequestMapping("/findAll")
	@ResponseBody
	public Map<String, Object> QueryPage(UserQueryPageDTO dto, int page, int limit, String username,
										 @RequestParam(value="sex",defaultValue="-1")Integer sex,
										 @RequestParam(value="status",defaultValue="-1")Integer status){
		dto.setCurrentPage(page);
		dto.setPageSize(limit);
		dto.setUsername(username);
		dto.setSex(sex);
		dto.setStatus(status);
		List<User> user = userService.findAll(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", dto.getTotal());
		map.put("data", user);
		return map;
	}
	@RequestMapping("/showAdd")
	public String showAdd(){
		//跳转添加用户界面
		return "user/adduser";
	}
	//添加用户
	@RequestMapping("/addUser")
	@ResponseBody
	public ResultDTO addUser(String username, String password, String nickname, Integer sex, Integer status,
							 @RequestParam(value="tel",defaultValue="")String tel,
							 @RequestParam(value="email",defaultValue="")String email,
							 @RequestParam(value="stu_id",defaultValue="0")int stu_id,
							 @RequestParam(value="name",defaultValue="")String name, int cmd, @RequestParam(value="userId",defaultValue="0")Integer userId){
		ResultDTO dto;
		//封装钱包
		Wallet wallet = new Wallet();
		wallet.setAccountOver(BigDecimal.ZERO);
		wallet.setIntegral(0);
		//封装用户信息
		Info info = new Info();
		info.setSex(sex);
		info.setTel(tel);
		info.setAvatar("");
		info.setEmail(email);
		info.setName(name);
		info.setStatus(status);
		info.setStu_id(stu_id);
		info.setWallet(wallet);
		//封装用户
		User user = new User();
		user.setUserName(username);
		user.setPassWord(password);
		user.setNickName(nickname);
		user.setRegisterDate(new Date());
		user.setInfo(info);
		if(cmd==1){
			//验证昵称是否存在
			User user_nickname = userService.testNickName(nickname);
			if(user_nickname!=null){
				return dto = ResultDTO.newInStrance("该昵称已被使用", 0);
			}
			//验证用户名是否存在
			User user_username = userService.testUserName(username);
			if(user_username!=null){
				return dto = ResultDTO.newInStrance("该用户名已被注册", 0);
			}
			//添加
			//验证添加是否成功
			int i = userService.insertUser(user);
			if(i<1){
				return dto = ResultDTO.newInStrance("添加失败", 0);
			}else {
				return dto = ResultDTO.newInStrance("添加成功", 1);
			}
		}else {
			//修改
			User user_update = userService.findOneById(userId);
			int i = userService.updateUser(user,user_update);
			if(i<1){
				return dto = ResultDTO.newInStrance("修改失败", 0);
			}else {
				return dto = ResultDTO.newInStrance("修改成功", 1);
			}
		}
	}
	//根据ID获取user跳转修改页面
	@RequestMapping("/showEdit")
	public String showEdit(HttpServletRequest request,Integer id){
		//根据ID查询user
		User user = userService.findOneById(id);
		request.setAttribute("user", user);
		return "user/adduser";
	}
	
	//根据ID删除user
	@RequestMapping("/delUser")
	@ResponseBody
	public ResultDTO delUser(String[] id){
		ResultDTO dto;
		List<Integer> ids = new ArrayList<Integer>();
		for (String string : id) {
			ids.add(Integer.parseInt(string));
		}
		int i = userService.delUserById(ids);
		if(i<1){
			dto = ResultDTO.newInStrance("删除失败", 0);
		}
		dto = ResultDTO.newInStrance("删除成功", 1);
		return dto;
	}
}
