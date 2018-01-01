<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="/static/layui/css/layui.css">
<script type="text/javascript" src="/static/layui/layui.js" charset="utf-8"/></script>
<title>用户管理</title>
</head>
<body>
	<div>
		<div style="padding: 10px;" >
			<form action="" class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label">用户名：</label>
					<input type="text" id="username" style="width: 100px;float: left;" name="username" placeholder="请输入用户名" class="layui-input">
					<label class="layui-form-label">性别：</label>
					<div style="width: 100px;float: left;">
						<select name="sex" id="sex">
					        <option value="-1">请选择</option>
					        <option value="0">男</option>
					        <option value="1">女</option>
				      	</select>
					</div>
					<label class="layui-form-label">会员：</label>
					<div style="width: 100px;float: left;">
						<select name="status" id="status">
					        <option value="-1">请选择</option>
					        <option value="0">普通用户</option>
					        <option value="1">会员</option>
				      	</select>
					</div>
				</div>
				<div class="layui-form-item" style="text-align: right;width: 95%;margin: auto;">
					<input type="button" id="btn" value="查询" class="layui-btn layui-btn-radius layui-btn layui-btn-sm" />
					<input type="reset" value="重置" class="layui-btn layui-btn-radius layui-btn layui-btn-sm" />
				</div>
			</form>
			<div style="width: 95%;margin: auto;">
				<div class="layui-btn-group">
				  <button class="layui-btn layui-btn-sm" id="showadd">
				    <i class="layui-icon">&#xe654;</i>
				  </button>
				  <button class="layui-btn layui-btn-sm" id="showedit">
				    <i class="layui-icon">&#xe642;</i>
				  </button>
				  <button class="layui-btn layui-btn-sm" id="showdel">
				    <i class="layui-icon">&#xe640;</i>
				  </button>
				  <button class="layui-btn layui-btn-sm" onclick="location.reload()">
				    <i class="layui-icon">&#x1002;</i>
				  </button>
				</div>
			</div>
		</div>
		<div style="width: 95%;margin: auto;" >
			<form action="" class="layui-form">
				<table id="table" lay-filter="test"></table>
			</form>
		</div>
	</div>
	<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
<script>
layui.use(['form','table','layer'], function(){
	var $ = layui.jquery;
	var form = layui.form;
	var table = layui.table;
	var layer = layui.layer;
  table.render({
    elem: '#table'
    ,id: 'testReload'
    ,url: '/user/findAll'
    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,height: 'full'
    ,loading: true
    ,cols: [[
      {checkbox: true}
      ,{field: 'userId', title: 'ID',width: 80}
      ,{field: 'userName', title: '用户名'}
      ,{field: 'nickName', title: '昵称'} 
      ,{field: 'info', templet: '<div>{{d.info.sex==0?"男":"女"}}</div>', title: '性别'}
      ,{field: 'registerDate', templet: '<div>{{ layui.laytpl.toDateString(d.registerDate) }}</div>', title: '注册时间',width: 177}
      ,{field: 'info', templet: '<div>{{d.info.tel}}</div>', title: '电话',width: 177}
      ,{field: 'state', templet: '<div>{{d.state==0?"离线":d.state==1?"在线":"注销"}}</div>', title: '状态'}
      ,{field: 'info', templet: '<div>{{d.info.status==1?"是":"否"}}</div>', title: '是否会员'}
      ,{field: 'info', templet: '<div>{{d.info.stu_id}}</div>', title: '学号'}
      ,{field: 'info', templet: '<div>{{d.info.name}}</div>', title: '真实姓名'}
      ,{field:'right', title: '操作',toolbar:"#barDemo",width:188}
    ]]
    ,page: true
    ,limit: 5
    ,limits: [5,10,20]
  });
 
  //高级查询（公共函数）
 function querydata(){
	 table.reload('testReload', {
		  url: '/user/findAll'
		  ,where: {
			  username: $("#username").val()
			  ,sex: $("#sex").val()
			  ,status: $("#status").val()
		  } //设定异步数据接口的额外参数
		});
 }
 window._querydata = querydata;
  //查询事件
  $("#btn").click(function(){
	  table.reload('testReload', {
		  url: '/user/findAll'
		  ,where: {
			  username: $("#username").val()
			  ,sex: $("#sex").val()
			  ,status: $("#status").val()
		  } //设定异步数据接口的额外参数
		});
  })
  
  //showadd()
  $("#showadd").click(function(){
	  layer.open({
		  type: 2, 
		  title: '添加',
		  skin: 'demo-class',
		  area: ['800px', '600px'],
		  content: '/user/showAdd',
		  anim: 2,
		  btn: ['确定','取消'],
		  yes: function(index, layero){
			  var iframeWin = window[layero.find('iframe')[0]['name']];
			  iframeWin.user();
			},
	  	  btn2: function(index,layero){
	  		layer.close(index);
	  	  }
		}); 
  });
//监听工具条
  table.on('tool(test)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
      showdata(obj.data.userId);
    } else if(obj.event === 'del'){
    	layer.confirm('你确定删除该条数据', {
  		  btn: ['确定','取消'] //按钮
  		}, function(){
  			$.ajax({
  				url: '/user/delUser?id='+obj.data.userId,
  				type: 'post',
  				dataType:'json',
  				success: function(data){
  					if(data.status==1){
  						//成功
  						layer.msg(data.message, {icon: 1});
  						querydate();
  					}else{
  						//失败
  						layer.msg(data.message, {icon: 2});
  					}
  				}
  			});
  		});
    } else if(obj.event === 'edit'){
      updatedata(obj.data.userId);
    }
  });
  
  var abc = Array();
  table.on('checkbox(test)', function(obj){
	  if(obj.type=="one"){
		  if(obj.checked){
			  abc[abc.length] = obj.data.userId;
		  }else{
			  for(var i = 0;i < abc.length; i ++){
				  if(abc[i] == obj.data.userId){
					  abc.splice(i,1);
				  }
			  }
		  }
	  }
	  if(obj.type=="all"){
		  var checkStatus = table.checkStatus('testReload');
		  abc = Array(checkStatus.data.length);
		  for(var j = 0; j< checkStatus.data.length; j++){
			  abc[j] = checkStatus.data[j].userId;
		  }
	  }
	});
  //打开修改页面
  $("#showedit").click(function(){
	  if(abc.length==1){
		  updatedata(abc[0]);
	  }else if(abc.length==0){
		  layer.alert("必须选中一条数据");
	  }else{
		  layer.alert("只能选中一条数据");
	  }
  });
  //删除处理
  $("#showdel").click(function(){
	  if(abc.length==0){
		  layer.alert("必须选中一条数据");
	  }else{
		  deldata(abc);
	  }
  })
  
  
//查看
  function showdata(id){
	  layer.open({
		  type: 2, 
		  title: '查看',
		  skin: 'demo-class',
		  area: ['800px', '600px'],
		  content: '/user/showEdit?id='+id,
		  anim: 2,
		  btn: ['确定'],
		  yes: function(index, layero){
			  layer.close(index);
			}
		}); 
  }
  //修改
  function updatedata(id){
	  layer.open({
		  type: 2, 
		  title: '修改',
		  skin: 'demo-class',
		  area: ['800px', '600px'],
		  content: '/user/showEdit?id='+id,
		  anim: 2,
		  btn: ['确定','取消'],
		  yes: function(index, layero){
			  var iframeWin = window[layero.find('iframe')[0]['name']];
			  iframeWin.edit();
			},
	  	  btn2: function(index,layero){
	  		layer.close(index);
	  	  }
		}); 
  }
  //删除
  function deldata(id){
	  layer.confirm('你确定删除'+id.length+'条数据', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
				url: '/user/delUser?id='+id,
				type: 'post',
				dataType:'json',
				success: function(data){
					if(data.status==1){
						//成功
						layer.msg(data.message, {icon: 1});
						querydate();
					}else{
						//失败
						layer.msg(data.message, {icon: 2});
					}
				}
			});
		});
  }
  
//时间戳的处理
  layui.laytpl.toDateString = function(d, format){
    var date = new Date(d || new Date())
    ,ymd = [
      this.digit(date.getFullYear(), 4)
      ,this.digit(date.getMonth() + 1)
      ,this.digit(date.getDate())
    ]
    ,hms = [
      this.digit(date.getHours())
      ,this.digit(date.getMinutes())
      ,this.digit(date.getSeconds())
    ];

    format = format || 'yyyy-MM-dd HH:mm:ss';

    return format.replace(/yyyy/g, ymd[0])
    .replace(/MM/g, ymd[1])
    .replace(/dd/g, ymd[2])
    .replace(/HH/g, hms[0])
    .replace(/mm/g, hms[1])
    .replace(/ss/g, hms[2]);
  };
   
  //数字前置补零
  layui.laytpl.digit = function(num, length, end){
    var str = '';
    num = String(num);
    length = length || 2;
    for(var i = num.length; i < length; i++){
      str += '0';
    }
    return num < Math.pow(10, length) ? str + (num|0) : num;
  };
  
});
</script>
</body>
</html>