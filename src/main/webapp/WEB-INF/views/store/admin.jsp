<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="/static/layui/css/layui.css" rel="stylesheet" media="all" />
<script type="text/javascript" src="/static/layui/layui.js"></script>
<script type="text/javascript" src="/static/js/jquery-3.2.1.js"></script>
<title>店铺管理员管理</title>
</head>
<body>
	<div>
		<div style="padding: 10px;" >
			<form action="" class="layui-form">
				<div class="layui-form-item">
					<label class="layui-form-label">用户名：</label>
					<input type="text" id="username" style="width: 100px;float: left;" name="username" placeholder="请输入用户名" class="layui-input">
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
    ,url: '/storeAdmin/queryData'
    ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
    ,height: 'full'
    ,loading: true
    ,cols: [[
      {checkbox: true}
      ,{field: 'sa_id', title: 'ID',width: 80}
      ,{field: 'userName', title: '用户名'}
      ,{field: 'nickName', title: '昵称'} 
      ,{field: 's_name', templet: '<div>{{d.store.s_name}}</div>', title: '所属店铺名'}
      ,{field: 'building', templet: '<div>{{ d.store.building }}</div>', title: '所属楼栋',width: 177}
      ,{field: 'registerDate', templet: '<div>{{ layui.laytpl.toDateString(d.registerDate) }}</div>', title: '注册时间',width: 177}
      ,{field:'right', title: '操作',toolbar:"#barDemo",width:188}
    ]]
    ,page: true
    ,limit: 5
    ,limits: [5,10,20]
  });
 
  //高级查询（公共函数）
 function querydata(){
	 table.reload('testReload', {
		  url: '/storeAdmin/queryData'
		  ,where: {
			  username: $("#username").val()
		  } //设定异步数据接口的额外参数
		});
 }
 window._querydata = querydata;
  //查询事件
  $("#btn").click(function(){
	  table.reload('testReload', {
		  url: '/storeAdmin/queryData'
		  ,where: {
			  username: $("#username").val()
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
		  content: '/storeAdmin/showAdd',
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
      showdata(obj.data.sa_id);
    } else if(obj.event === 'del'){
    	layer.confirm('你确定删除该条数据', {
  		  btn: ['确定','取消'] //按钮
  		}, function(){
  			$.ajax({
  				url: '/storeAdmin/delAdmin?id='+obj.data.sa_id,
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
      updatedata(obj.data.sa_id);
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
		  content: '/storeAdmin/showEdit?sa_id='+id,
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
		  content: '/storeAdmin/showEdit?sa_id='+id,
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
				url: '/storeAdmin/delAdmin?id='+id,
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