<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/static/layui/css/layui.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<title>添加管理员</title>
<style type="text/css">
	.layui-form-label{
		width: 90px;
	}
</style>
</head>
<body>
	<div>
		<div>
		<form action=""  class="layui-form" id="addStoreAdmin">
			<table height="400px;" style="margin: 0 auto">
				<tr>
					<td><label class="layui-form-label">管理员账户：</label></td>
					<c:choose>
						<c:when test="${admin==null }">
							<td><input value="${admin.userName }" type="text" id="username" name="username" placeholder="账户" class="layui-input"></td>
						</c:when>
						<c:otherwise>
							<td><input value="${admin.userName }" type="text" disabled id="username" name="username" placeholder="账户" class="layui-input"></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td><label class="layui-form-label">管理员密码：</label></td>
					<td><input value="${admin.passWord }" type="password" id="password" name="password" placeholder="密码" class="layui-input"></td>
				</tr>
				<tr>
					<td><label class="layui-form-label">确认密码：</label></td>
					<td><input value="" type="password" id="regpassword" name="regpassword" placeholder="密码" class="layui-input"></td>
				</tr>
				<tr>
					<td><label class="layui-form-label">管理员昵称：</label></td>
					<td><input value="${admin.nickName }" type="text" id="nickname" name="nickname" placeholder="昵称" class="layui-input"></td>
				</tr>
				<tr>
					<td><label class="layui-form-label">所属店铺：</label></td>
					<td>
						<select name="store" id="store">
					        <option value="-1" selected>请选择</option>
				      	</select>
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	
	
	<script type="text/javascript">
layui.use(['form','layer'], function(){
	var $ = layui.jquery;
	var form = layui.form;
	var layer = layui.layer;
	
	var admin = "${admin}";
	if(admin!=null){
		$.ajax({
			url: '/store/getStore',
			type: 'post',
			dataType:'json',
			success: function(data){
				if(data.status==1){
					//成功
					for(var i = 0;i<data.list.length;i++){
						$("#store").append("<option value='"+data.list[i].s_id+"'>"+data.list[i].s_name+"</option>");
					}
					$("#store").val("${admin.store.s_id}");
					form.render("select");
				}else{
					//失败
					layer.msg(data.message , {icon:2});
				}
			}
		});
	}
	
});
	var id = "${admin.sa_id}";
	
	function user(){
		var username = $("#username").val();
		var nickname = $("#nickname").val();
		var password = $("#password").val();
		var regpassword = $("#regpassword").val();
		var store = $("#store").val();
		if(username.length<3 || username.length >10){
			layer.msg("用户名必须6-16个字符！");
			return;
		}
		if(password.length<3 || password.length >10){
			layer.msg("密码必须6-16个字符！");
			return;
		}
		if(password!=regpassword){
			layer.msg("两次密码不一致!");
			return;
		}
		if(nickname.length<2 || nickname.length>8){
			layer.msg("昵称必须2-8个字符！");
			return;
		}	
		if(store==null || store=="-1"){
			layer.msg("选择所属店铺");
			return;
		}
		$.ajax({
			url: '/storeAdmin/addStoreAdmin?cmd=1',
			type: 'post',
			dataType:'json',
			data: $("#addStoreAdmin").serialize(),
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
		$('#username').attr("readonly","true");
		var username = $("#username").val();
		var nickname = $("#nickname").val();
		var password = $("#password").val();
		var respassword = $("#respassword").val();
		if(username.length<3 || username.length >10){
			layer.msg("用户名必须6-16个字符！");
			return;
		}
		if(password.length<3 || password.length >10){
			layer.msg("密码必须6-16个字符！");
			return;
		}
		if(password!=respassword){
			layer.msg("两次密码不一致!");
			return;
		}
		if(nickname.length<2 || nickname.length>8){
			layer.msg("昵称必须2-8个字符！");
			return;
		}
		if(store==null || store=="-1"){
			layer.msg("请选择所属店铺");
			return;
		}
		$.ajax({
			url: '/stroeAdmin/addStoreAdmin?cmd=0&id='+id,
			type: 'post',
			dataType:'json',
			data: $("#addStoreAdmin").serialize(),
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
</body>
</html>