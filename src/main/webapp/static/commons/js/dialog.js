var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
/**
 * 关闭当前索引窗口
 */
function closeD(){
    parent.layer.close(index);
}
/**
 * 刷新页面
 */
function reload(){
	 parent.loadData();
};
/**
 * 弹出功能提示： 回到主页 留下继续操作
 * @param conte  传入提示信息
 */
function comfigDialog(conte,state){
	layui.use('layer', function(){
		  var layer = layui.layer;
		  layer.open({
			  content:conte,
			  title:"提示信息",
			  icon: -1,
			  time: 0,
			  btn: ['回到主页面', '留下继续操作']
		  ,yes:function(i){
			  if(state){
				  layer.close(i);
				  closeD();
				  parent.location.reload();
			  }else{
				  layer.close(i);
				  closeD();
				  reload();
			  }
			  
		  }
		  });
	}); 
}
/**
 * 验证失败弹出框
 * @param content
 */
function tips(content){
	$("body").append("<div id='MyLayui_valida_tips' style='height:30px;width:auto;position: fixed;top:calc(40% - 30px);left:50%'><i class='layui-icon' style='font-size: 30px; color: #ff6600;'>&#xe60c;</i>  <label style='color: #ff6600;'>"+content+"</label></div>");
	for (var i = 0; i <=5; i++) {
		if (i%2==0) {
			$("#MyLayui_valida_tips").animate({
				left:"49%"
			},80)
		}else{
			$("#MyLayui_valida_tips").animate({
				left:"51%"
			},80)
		}
	}
	setTimeout("deleteTips()", 1000);
}
function deleteTips(){
	$('#MyLayui_valida_tips').remove();
}
