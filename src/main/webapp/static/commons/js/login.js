$(function(){
	if (window.top != null && window.top.location != window.location) {
	    window.top.location = window.location;
	}
	//设置登录框的上下位置
	var height = $(window).height();
	var loginBoxHeight = $(".login-box").height();
	$(".login-box").css("margin-top",(height-loginBoxHeight)/2);
	$(".login-img").css("margin-top",(height-loginBoxHeight)/2-100);
	//提交登录
	layui.use('form', function(){
		  var form = layui.form();
 			form.on('submit(login)', function(data){
 				login(data.elem,data.field);
		});
	});
})
function resetCode(el){
	el.src='/login/showCode?'+new Date().getTime();
}

function login(el,data){
	$(el).html("数据提交中...");
	$.post("/login/login",data,function(result){
		result = JSON.parse(result);
		$(el).html("用户登录");
		//提示信息
		$(".login-box .login-parent .msg").html(result.message);
		$(".login-box .login-parent .msg").animate({opacity: 'show'},1000);
		if (result.status==0) {
			resetCode($("#loginCodeImg")[0]);
		}else{
			location.href="/index/";
		}
	});
}