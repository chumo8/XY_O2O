<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加</title>
<link href="/static/layui/css/layui.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<style type="text/css">
	.text_right{
		text-align: right;
		background-color: #F2F2F2;
		width: 100px;
	}
	.text_left{
		width: 200px;
		padding: 10px;
	}
</style>
</head>
<body>
	<div>
		<div style="padding: 20px;">
			<form action="" class="layui-form" id="adduser">
			<table width="100%" height="450px" bordercolor="#B4CDE6" border="1">
				<tr>
					<td class="text_right"><label class="layui-form-label">昵称：</label></td>
					<c:choose>
						<c:when test="${user==null }">
							<td class="text_left"><input value="${user.nickName }" type="text" id="nickname" name="nickname" placeholder="昵称" class="layui-input"></td>
						</c:when>
						<c:otherwise>
							<td class="text_left"><input disabled value="${user.nickName }" type="text" id="nickname" name="nickname" placeholder="昵称" class="layui-input"></td>
						</c:otherwise>
					</c:choose>
					<td class="text_right"><label class="layui-form-label">账号：</label></td>
					<c:choose>
						<c:when test="${user==null }">
							<td class="text_left"><input value="${user.userName }" type="text" id="username" name="username" placeholder="用户名" class="layui-input"></td>
						</c:when>
						<c:otherwise>
							<td class="text_left"><input disabled value="${user.userName }" type="text" id="username" name="username" placeholder="用户名" class="layui-input"></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td class="text_right"><label class="layui-form-label">密码：</label></td>
					<td class="text_left"><input value="${user.passWord }" type="password" id="password" name="password" placeholder="密码" class="layui-input"></td>
					<td class="text_right"><label class="layui-form-label">确认密码：</label></td>
					<td class="text_left"><input  type="password" id="respassword" placeholder="密码" class="layui-input"></td>
				</tr>
				<tr>
					<td class="text_right"><label class="layui-form-label">性别：</label></td>
					<td class="text_left">
						<select name="sex" id="sex">
					        <option value="-1" selected>请选择</option>
					        <option value="0">男</option>
					        <option value="1">女</option>
				      	</select>
					</td>
					<td class="text_right"><label class="layui-form-label">是否会员：</label></td>
					<td class="text_left">
						<select name="status" id="status">
					        <option value="-1" selected>请选择</option>
					        <option value="0">普通用户</option>
					        <option value="1">会员</option>
				      	</select>
					</td>
				</tr>
				<tr>
					<td class="text_right"><label class="layui-form-label">电话号码：</label></td>
					<td class="text_left"><input value="${user.info.tel }" type="text" id="tel" name="tel" placeholder="电话号码" class="layui-input"></td>
					<td class="text_right"><label class="layui-form-label">邮箱：</label></td>
					<td class="text_left"><input value="${user.info.email }" type="text" name="email" lay-verify="email" autocomplete="off"  placeholder="邮箱" class="layui-input"></td>
				</tr>
				<tr>
					<td class="text_right"><label class="layui-form-label">学号：</label></td>
					<td class="text_left"><input value="${user.info.stu_id }" type="text" id="stu_id" name="stu_id" placeholder="学号" class="layui-input"></td>
					<td class="text_right"><label class="layui-form-label">真实姓名：</label></td>
					<td class="text_left"><input value="${user.info.name }" type="text" id="name" name="name" placeholder="姓名" class="layui-input"></td>
				</tr>
				<tr>
					<td class="text_right"><label class="layui-form-label">头像：</label></td>
					<td class="text_left"></td>
					<td class="text_right"></td>
					<td class="text_left"></td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
layui.use(['form','layer'], function(){
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;
	
});
	$("#sex").val("${user.info.sex}");
	$("#status").val("${user.info.status}");	
	function user(){
		var username = $("#username").val();
		var nickname = $("#nickname").val();
		var password = $("#password").val();
		var respassword = $("#respassword").val();
		var sex = $("#sex").val();
		var status = $("#status").val();
		var tel = $("#tel").val();
		var email = $("#email").val();
		var stu_id = $("#stu_id").val();
		var name = $("#name").val();
		if(nickname.length<2 || nickname.length>8){
			layer.msg("昵称必须2-8个字符！");
			return;
		}	
		if(username.length<6 || username.length >16){
			layer.msg("用户名必须6-16个字符！");
			return;
		}
		if(password.length<6 || password.length >16){
			layer.msg("密码必须6-16个字符！");
			return;
		}
		if(password!=respassword){
			layer.msg("两次密码不一致!");
			return;
		}
		if(sex==null || sex=='-1'){
			layer.msg("选择性别");
			return;
		}
		if(status==null || status=='-1'){
			layer.msg("选择是否会员");
			return;
		}
		
		$.ajax({
			url: '/user/addUser?cmd=1',
			type: 'post',
			dataType:'json',
			data: $("#adduser").serialize(),
			success: function(data){
				if(data.status==1){
					//成功
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
					parent._querydata();
					parent.layer.msg(data.message,{icon:1});
				}else{
					//失败
					parent.layer.msg(data.message,{icon:2});
				}
			}
		});
	}
	function edit(){
		
		var username = $("#username").val();
		var nickname = $("#nickname").val();
		var password = $("#password").val();
		var respassword = $("#respassword").val();
		var sex = $("#sex").val();
		var status = $("#status").val();
		var tel = $("#tel").val();
		var email = $("#email").val();
		var stu_id = $("#stu_id").val();
		var name = $("#name").val();
		var userId = "${user.userId}";
		if(nickname.length<2 || nickname.length>8){
			layer.msg("昵称必须2-8个字符！");
			return;
		}
		if(username.length<6 || username.length >16){
			layer.msg("用户名必须6-16个字符！");
			return;
		}
		if(password.length<6 || password.length >16){
			layer.msg("密码必须6-16个字符！");
			return;
		}
		if(password!=respassword){
			layer.msg("两次密码不一致!");
			return;
		}
		if(sex==null || sex=='-1'){
			layer.msg("选择性别");
			return;
		}
		if(status==null || status=='-1'){
			layer.msg("选择是否会员");
			return;
		}
		
		$.ajax({
			url: '/user/addUser?cmd=0&userId='+userId,
			type: 'post',
			dataType:'json',
			data: $("#adduser").serialize(),
			success: function(data){
				if(data.status==1){
					//成功
					var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					parent.layer.close(index);
					parent._querydata();
					parent.layer.msg(data.message, {icon: 1});
				}else{
					//失败
					parent.layer.msg(data.message, {icon:2});
				}
			}
		});
		
		
	}
</script>
</html>