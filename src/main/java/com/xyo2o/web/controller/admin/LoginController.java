package com.xyo2o.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xyo2o.common.util.CodeUtil;
import com.xyo2o.common.util.JSONUtil;
import com.xyo2o.domain.admin.Admin;
import com.xyo2o.dto.ResultDTO;
import com.xyo2o.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/*处理登录*/
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private AdminService adminService;
	/**
	 * 跳往登录界面
	 * @return
	 */
	@RequestMapping("/showLogin")
	public String showLogin(){
		return "admin/login";
	}
	/**
	 * 创建验证码
	 * @param resp
	 * @param session
	 */
	@RequestMapping("/createCode")
	public void createCode(HttpServletResponse resp,HttpSession session){
		String code = CodeUtil.createCode(80, 26, 4, resp);
		System.err.println("验证码是  "+code);
		session.setAttribute("code", code);
	}
	@RequestMapping("/Verify")
	@ResponseBody
	public void Verify(String verifyCode , HttpSession session , HttpServletResponse response){
        //获取传过来的验证码
        System.out.println("verifyCode----"+verifyCode);
        if(verifyCode=="") {
        	JSONUtil.INSTANCE.putJSON(response, false, "验证码为空");
        }else {
            //获取kaptcha生成存放在session中的验证码
        	String sysCode = (String)session.getAttribute("code");
            //比较输入的验证码和实际生成的验证码是否相同
        	if (!sysCode.equalsIgnoreCase(verifyCode)) {//忽略大小写比较
        		JSONUtil.INSTANCE.putJSON(response, false, "验证码错误");
    		}else{
    			JSONUtil.INSTANCE.putJSON(response, true, "验证成功");
    		}
        }
	}
	@RequestMapping("/login")
	@ResponseBody
	public ResultDTO login(@RequestParam(value="username")String username,
						   @RequestParam(value="password")String password, @RequestParam(value="code")String code, HttpSession session){
		//验证验证码
		ResultDTO dto;
		System.err.println("code  "+code);
		String sysCode = (String)session.getAttribute("code");
		if (!sysCode.equalsIgnoreCase(code)) {//忽略大小写比较
			dto = ResultDTO.newInStrance("验证码输入错误", 0);
			return dto;
		}
		//登录验证
		Admin sysAdmin = adminService.selectOneByUsername(username);
		if (sysAdmin==null) {
			dto = ResultDTO.newInStrance("账号不存在", 0);
			return dto;
		}
		try {
			if (!sysAdmin.getPassword().equals(password)) {
				dto = ResultDTO.newInStrance("密码获取错误,请刷新重试.", 0);
				return dto;
			}
		} catch (Exception e) {
			dto = ResultDTO.newInStrance("密码获取错误,请刷新重试.", 0);
			return dto;
		}
		dto = ResultDTO.newInStrance("登录成功!将自动跳转到首页!", 1);
		session.setAttribute("loginAdmin", sysAdmin);
		return dto;
	}
	
}
