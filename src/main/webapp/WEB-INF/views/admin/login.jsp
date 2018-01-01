<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/static/img/high_school_128px_1200853_easyicon.net.ico" type="image/x-icon"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/static/layui/layui.js"></script>
<link rel="stylesheet" href="/static/layui/css/layui.css" media="all">

<style type="text/css">
	* {
		margin: 0;
		padding: 0;
	}
	
	.xeon {
		width: 1200px;
		margin: 0 auto;
		position: relative;
		min-width: 1200px;
	}
	
	.login {
		width: 400px;
		height: 400px;
		position: fixed;
		top: 180px;
		right:17%;
		background: black;
		border-radius:20px;
		box-shadow: 10px 13px 35px -9px rgba(41,55,68,0.8);
		transition:box-shadow 2s,background 2S,color 2s;
		color: white;
		opacity:0.7;
	}
	.login:HOVER {
		box-shadow: 10px 16px 35px -9px rgba(146,59,33,0.8);
	}
	td{ height: 14%; font-size: 20px; font-family: "微软雅黑"; font-weight: bolder;}
	body{
		background: url("/static/img/0.jpg") fixed center center no-repeat;
background-size: cover;
width: 100%;;
	}
	.bottom{
		 position: fixed;
	    bottom: 0;
		width: 100%;
		height: 30px;
		background: black;
		opacity:0.5;
	}
	.bottom_text{
	    position: fixed;
	    bottom: 0;
	    width: 100%;
	    overflow: visible;
	    z-index: 99;
	    clear: both;
		bottom:6px;
		float:right;
		left: 72%; 
		color: white; 
	}
</style>
<script type="text/javascript">
	$(function(){
		layui.use(['layer', 'form']);
		 var layer = layui.layer;
	});
	//点击切换验证码
	function changeCode(el){
		el.src="/login/createCode?"+new Date().getTime();
	}
	//提交
	function login(){
		var verifyCodeValue = $("#verifyCode").val();
		var username = $("#username").val();
		var password = $("#password").val();
		if(username.replace(/\s/g, "") == "" ){
			layer.open({
				  title: '提示'
				  ,content: '请填写用户名'
				});   
		}else if(password.replace(/\s/g, "") == "" ){
			layer.open({
				  title: '提示'
				  ,content: '请填写密码'
				});   
		} else  if (verifyCodeValue.replace(/\s/g, "") == "" ) {
			layer.open({
				  title: '提示'
				  ,content: '请填写验证码'
				});   	 
		}else {
// 			提交前先异步检查验证码是否输入正确
			var verifyUrl = "/login/Verify?verifyCode="+verifyCodeValue;
			$.ajax({
				type : "GET",
				dataType: "json",
				url : verifyUrl,
				success : function(data) {
					data = $.parseJSON(data);
					if (data.result==true) {
						$.post("/login/login",{"username":username,"password":password,"code":verifyCodeValue},function(data){
			 		    	if (data.status==1) {
			 		    		layer.alert(data.message, {
									  skin: 'layui-layer-molv' //样式类名
									  ,closeBtn: 0
									}, function(){
										window.location="/index";
									});
			 				}else{
			 					layer.open({
									  title: '提示'
									  ,content: data.message
								});
			 					changeCode($("img")[3]);
			 				}
						});
					} else {
						layer.open({
							  title: '提示'
							  ,content: data.msg
							});
						changeCode($("img")[3]);	
					}
				},
			});
	
		}
	}
</script>
<title>校园管理系统登录</title>
</head>
<body >
	<div class="xeon">
		<div class="login">
			<table  width="80%" height="95%" style="margin: 0 auto;">
				<tr>
					<td colspan="2">登录</td>
				</tr>
				<tr>
					<td colspan="2">
						<img style="position:relative; top: 25px; right: 28px; height: 20px;" alt="" src='/static/img/user_icon_copy.png'>
						<input name="username" id="username" class="layui-input">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<img style="position:relative; top: 27px; right: 28px;  height: 20px;" alt="" src='/static/img/key.png'><input name="password" id="password" type="password" class="layui-input">
					</td>
				</tr>
				<tr>
					<td>
						<img style="position:relative; top:29px; right: 28px;  height: 20px;" alt="" src='/static/img/lock_icon_copy.png'><input name="verifyCode" id="verifyCode" style="width: 100px; margin: 0px;;" class="layui-input">
					</td>
					<td width="65%;">
					<img id="yzmimg" onclick="changeCode(this)" style="margin-top: 21px;" alt="验证" src="/login/createCode" >
						<font size="2"  >点图切换</font>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button style="width: 100%;" onclick="login()" class="layui-btn layui-btn-big layui-btn-radius layui-btn-normal">登录</button>
					</td>
				</tr>
			</table>
		</div>
		
	</div>

	<div class="bottom">
		
	</div>
		<div class="bottom_text" >
			Copyright © 2017 泸州职业技术学院, All rights reserved.  技术支持：鹏信软件工作室
	</div>
</body>
</html>