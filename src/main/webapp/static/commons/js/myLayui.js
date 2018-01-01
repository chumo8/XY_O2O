(function($) {
	var obj;
	var argumentFlag = true;
	var id_name;
	var id;
	var table;
	var privateFunction = function() {
	}
	// 在字面量对象中定义每个单独的方法
	function filterStr(str){
		var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？%+_]");    
		var specialStr = "";    
		for(var i=0;i<str.length;i++){    
			specialStr += str.substr(i, 1).replace(pattern, '');     
		}    
		return specialStr;
	}
	function machTable(id){
		//根据id得到table标签
		table = $("#"+id);
		table.html("");
		//检测标签是否是table标签
		if (table[0].tagName!="TABLE") {
			$.error("id指定的标签不是table标签");
			return;
		}
		//给table标签加layui的样式
		table.addClass("layui-table");
		return table;
	}
	
	function createThead(table,id){
		//根据数据格式生成表格的头
		table.append("<thead><tr id='"+id+"_thead'></tr></thead>");
		var thead = $("#"+id+"_thead");
		return thead;
	}
	function machTheadCheckBox(thead,checkbox){
		//判断是否有单选框--在表头上生成一个checkbox
		if (checkbox) {
			thead.append("<th><input type='checkbox' id='"+id+"_checkBox'></th>");
		}
	}
	
	function getTeadWidth(columns){
		var totalWidth = 0;//记录总宽度
		//计算表格的总宽度
		for (var i = 0; i < columns.length; i++) {
			var row = columns[i];
			if (!row.idField) {
				totalWidth+=row.width;
			}
		}
		return totalWidth;
	}
	
	function machTheadData(thead,totalWidth,columns){
		var id_name;//id的属性名
		var flag = false;//开关,这个开关状态用来表示用户是否填写了合法的数据id属性
		//
		for (var i = 0; i <columns.length; i++) {
			var row = columns[i];
			if (row.idField) {
				id_name = row.field;
				flag = true;
			}else{
				thead.append("<th width='"+row.width/totalWidth*100+"%'>"+row.title+"</th>");
			}
		}
		if (!flag) {
			$.error("请使用idField:true指定一个id属性名");
			return;
		}
		return id_name;
	}
	function createQuery(table,obj,result,id){
		//生成高级查询表单
		if (obj.query) {//有高级查询
			var autoQuery;
			//如果没有值默认为true,有值就根据值来
			if(typeof(obj.autoQuery)=="undefined"){
				autoQuery = true;
			}else{
				autoQuery = obj.autoQuery;
			}
			if (autoQuery) {//开启了自动生成高级查询表单
				if(typeof(obj.queryData) != "object" ||obj.queryData.length==0){
					$.error("开始自动生成高级查询表单,queryDate属性必须有值.")
					return;
				}
				//生成form表单
				$("body").prepend("<form class='layui-form' id='"+id+"_form'></form>");
				//获取form表单
				var queryForm = $("#"+id+"_form");
				layui.use(['laydate','form'], function(){
					var laydate = layui.laydate;
					var form = layui.form();
					var start = {
						min: laydate.now()
						,max: '2099-06-16 23:59:59'
							,istoday: false
							,choose: function(datas){
								end.min = datas; //开始日选好后，重置结束日的最小日期
								end.start = datas //将结束日的初始值设定为开始日
							}
					};
					var end = {
						min: laydate.now()
						,max: '2099-06-16 23:59:59'
							,istoday: false
							,choose: function(datas){
								start.max = datas; //结束日选好后，重置开始日的最大日期
							}
					};
				});
				queryForm.append("<input name='currentPage' type='hidden' value='"+result.currentPage+"'>");
				for (var l = 0; l < obj.queryData.length; l++) {
					var row = obj.queryData[l];
					var width = !!row.width?row.width:"150px";
					var height = !!row.height?row.height:"38px";
					if(row.type=="text"){
						queryForm.append(row.text+"<input class='layui-input' name='"+row.name+"'  placeholder='"+row.placeholder+"' style='display:inline-block;height:"+height+";width:"+width+"'>");
					}
					if (row.type=="date") {
						queryForm.append(row.text+"<input class='layui-input' name='"+row.name+"'  placeholder='"+row.placeholder+"' onclick='layui.laydate({elem: this, festival: true})' style='display:inline-block;height:"+height+";width:"+width+"'>");
					}
					if (row.type=="datetime") {
						queryForm.append(row.text+"<input class='layui-input' name='"+row.name+"'  placeholder='"+row.placeholder+"' onclick='layui.laydate({elem: this, istime: true, format: \"YYYY-MM-DD hh:mm:ss\"})' style='display:inline-block;height:"+height+";width:"+width+"'>");
					}
					if (row.type=="select") {
						var selectId = new Date().getTime();
						queryForm.append(row.text+"<div class='layui-input-inline' style='display:inline-block;height:"+height+";width:"+width+"'><select class='layui-input' id='queryPage_select"+selectId+"' name='"+row.name+"'></select><div>");
						var queryPage_select = $("#queryPage_select"+selectId);
						$.ajax({
							url:row.url,
			                cache : false, 
			                async : false,
			                type : "POST",
			                dataType : 'json',
			                success : function (s){
			                    if(typeof(s) != "object" ||s.length==0){
			                    	s = JSON.parse(s);
			                    }
			                    queryPage_select.html("<option value=''>请选择"+filterStr(row.text)+"</option>");
			                    for (var sss = 0; sss < s.length; sss++) {
			                    	var ss = s[sss];
			                    	queryPage_select.append("<option value='"+ss[row.dataId]+"'>"+ss[row.dataName]+"</option>");
			                    }
			                }
			            });
					}
				}
			}
			if (obj.queryData.length>0) {
				queryForm.append("<a onclick='loadData()' class='layui-btn' style='margin-left:10px;display:inline-block;'>提交查询</a>");
				queryForm.append("<a onclick='resetForm(\""+id+"_form\")' class='layui-btn' style='margin-left:10px;display:inline-block;'>重置</a>");
			}
		}
	}
	function createPage(id,table,result,obj){
		//生成分页条------------------------------------------------------------
		if (!obj.nopage) {
			var pageDiv = $("#"+id+"_page");
			if (pageDiv.length==0) {
				table.after("<div id='"+id+"_page'></div>");
			}else{
				pageDiv.html("");
			}
			var sysPagenumber = 5;
			if (!!obj.pagenumber&&obj.pagenumber>0) {
				sysPagenumber = obj.pagenumber;
			}
			layui.use(['laypage'], function(){
				if(typeof(result) != "object" || result.length==0){
					result = JSON.parse(result);
				}
				var laypage = layui.laypage
				laypage({
					cont: id+"_page"
					,pages: result.totalPage //总页数
					,groups:sysPagenumber //连续显示分页数
					,skip: true
					,curr:result.currentPage
					,jump: function(object, first){
						//得到了当前页，用于向服务端请求对应数据
						var curr = object.curr;
						if (!first&&!obj.query) {//没有高级查询
							//没有高级查询直接做分页
							if(typeof(obj.data) == "object" && obj.data.length>0){
								obj["data"]["currentPage"]=curr;
							}else{
									obj.data = new Object();
									obj.data.currentPage = curr;
							}
							sysReload();
							return;
						}
						if(!first&&obj.query){
							var queryForm = $("#"+id+"_form");
							var autoQuery;
							//如果没有值默认为true,有值就根据值来
							if(typeof(obj.autoQuery)=="undefined"){
								autoQuery = true;
							}else{
								autoQuery = obj.autoQuery;
							}
							if (!autoQuery&&queryForm.length==0) {//手写高级查询
								$.error("请定义一个id为"+obj.id+"_form的表单");
								return;
							}else{//自动生成高级查询需要的form表单
								if (queryForm.length==0) {
//									table.before("<form id='"+id+"_form'></form>");
									$("body").prepend("<form id='"+id+"_form'></form>");
									queryForm = $("#"+id+"_form");
								}
								var currInput = $("#"+id+"_form").find("input[name='currentPage']");
								if (currInput.length==0) {
									queryForm.append("<input type='hidden' name='currentPage' value='"+curr+"'>");
									currInput = $("#"+id+"_form").find("input[name='currentPage']");
								}else{
									currInput.val(curr);
								}
							}
							if(typeof(obj.data) == "object" && 
						            Object.prototype.toString.call(obj.data).toLowerCase() == "[object object]" && obj.data.length>0){
								obj["data"]["currentPage"]=curr;
							}else{
									obj.data = new Object();
									obj.data.currentPage = curr;
							}
							sysReload();
						}
					}
				});
			})
		}
	}
	function createTbody(id,id_name,checkbox,columns,result,onclick){
		//-----------------------------------------------------------------------
		if(typeof(result) != "object" ||result.length==0){
			result = JSON.parse(result);
		}
		//构建tbody
		//获取tbody
		var tbody = $("#"+id+"_tbody");
		if (tbody.length==0) {
			//加工table
			table.append("<tbody id='"+id+"_tbody'></tbody>");
			tbody = $("#"+id+"_tbody");
		}else{
			tbody.html("");
		}
		//循环结果集
		for (var i = 0; i < result.rows.length; i++) {
			//得到每一个结果集
			var row = result.rows[i];
			//组装tr
			tbody.append("<tr id='"+row[id_name]+"_tr'></tr>");
			//得到tr
			var tr = $("#"+row[id_name]+"_tr");
			tr.css("cursor","pointer ");
			//判断是否有单选框
			if (checkbox) {
				tr.append("<td><input type='checkbox' name='"+id_name+"' value='"+row[id_name]+"'></td>");
			}
			//循环取出用户传入的数据格式对象
			for (var j = 0; j < columns.length; j++) {
				//取出每一个格式
				var row2 = columns[j];
				//如果不是主键列
				if (!row2.idField) {
					//从结果集中根据数据格式属性取出值
					if(typeof(row2.format) == "function"){//数据格式化
						tr.append("<td>"+row2.format(row)+"</td>");
					}else if(row2.format&&typeof(row2.format)){
						var funStr = row2.format.toString();
						var funIndex = funStr.indexOf("function");
						var funStr_1 = funStr.substring(funIndex,8)+" format ";
						var fun = funStr_1 + funStr.substring(8,funStr.length);
						eval(fun);
						tr.append("<td>"+format(row)+"</td>");
					}else{
						tr.append("<td>"+row[row2.field]+"</td>");
					}
				}
			}
		}
		singleSelect(id,id_name,onclick,result.rows);
	}
	function reloadData(table,id,id_name,obj){
		//远程获取数据,url和参数从函数参数传入-------------------------------------------------------------------
		$.post(obj.url,obj.data,function(result){
			if(typeof(result) != "object" ||result.length==0){
				result = JSON.parse(result);
			}
			//创建高级查询box
			createQuery(table,obj,result,id);
			//创建分页box
			createPage(id,table,result,obj);
			//创建表格数据身体
			createTbody(id,id_name,obj.checkbox,obj.columns,result,obj.onclick);
			var str = JSON.stringify(result);
			str = encodeURIComponent(str);
			var data222 = $(".tabladata_data_222");
			if (data222[0]) {
				data222.html(str);
			}else{
				$("body").append("<div class='tabladata_data_222' style='display:none'>"+str+"</div>");
			}
		})
	}
	function singleSelect(id,id_name,fun,rows,bol){
		//单选全选
		var singleSelectFlag = false;
		if(obj.singleSelect||bol){
			singleSelectFlag = true;
		}
		if (singleSelectFlag) {
			//单选
			var allCheckBoxs = $("#"+id).find('input[name="'+id_name+'"]');
			$("#"+id+"_tbody").find("tr").click(function(){
				for (var k = 0; k < allCheckBoxs.length; k++) {
					allCheckBoxs[k].checked = false;
				}
				var checkbox = $(this).find("td input[name='"+id_name+"']")[0];
				checkbox.checked = !checkbox.checked;
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].id==$(checkbox).val()&&typeof(fun)=="function") {
						fun(rows[i]);
					}
				}
			})
			allCheckBoxs.click(function(){
				for (var k = 0; k < allCheckBoxs.length; k++) {
					allCheckBoxs[k].checked = false;
				}
				this.checked = !this.checked;
			})
		}else{
			//全选
			var allCheckBoxs = $("#"+id).find('input[name="'+id_name+'"]');
			$("#"+id+"_tbody").find("tr").click(function(){
				var checkbox = $(this).find("td input[name='"+id_name+"']")[0];
				checkbox.checked = !checkbox.checked;
				var flag = true;
				for (var k = 0; k < allCheckBoxs.length; k++) {
					if(!allCheckBoxs[k].checked){
						flag = false;
					}
				}
				var tbox = $("#"+id+"_thead").find("th input[type='checkbox']");
				if(flag){
					tbox[0].checked = true;
				}else{
					tbox[0].checked = false;
				}
			})
			allCheckBoxs.click(function(){
				this.checked = !this.checked;
			})
			var checkbox = $("#"+id+"_thead").find("th input[type='checkbox']");
			checkbox.click(function(){
				for (var k = 0; k < allCheckBoxs.length; k++) {
					allCheckBoxs[k].checked = checkbox[0].checked;
				}
			});
		}
	}
	function sysReload(){
		var ii;
		layui.use('layer', function(){
			  layer = layui.layer;
			  ii = layer.load();
		}); 
		var obj = getNewObj();
		//获取高级查询参数和分页参数,组装data
		var data = new Object();
		var queryForm = $("#"+obj.id+"_form");
		data["currentPage"]=$("input[name=currentPage]").val();
		for (var l = 0; l < obj.queryData.length; l++) {
			var row = obj.queryData[l];
			if(row.type=="text"){
				data[row.name] = queryForm.find("input[name='"+row.name+"']").val();
			}
			if (row.type=="date") {
				data[row.name] = queryForm.find("input[name='"+row.name+"']").val();
			}
			if (row.type=="datetime") {
				data[row.name] = queryForm.find("input[name='"+row.name+"']").val();
			}
			if (row.type=="select") {
				data[row.name] = queryForm.find("select[name='"+row.name+"']").val();
			}
		}
		$.post(obj.url,data,function(result){
			layer.close(ii);
			if(typeof(result) != "object" ||result.length==0){
				result = JSON.parse(result);
			}
			createTbody(obj.id,obj.id_name,obj.checkbox,obj.columns,result)
			//创建分页box
			createPage(obj.id,$("#"+obj.id),result,obj);
			singleSelect(obj.id,obj.id_name,{},result,true);
			var str = JSON.stringify(obj);
			str = encodeURIComponent(str);
			$(".tabladata_data_111").html(str);
			str = JSON.stringify(result);
			str = encodeURIComponent(str);
			var data222 = $(".tabladata_data_222");
			if (data222[0]) {
				data222.html(str);
			}else{
				$("body").append("<div class='tabladata_data_222' style='display:none'>"+str+"</div>");
			}
		});
	}
	function getNewObj(){
		var str = $(".tabladata_data_111").html();
		str = decodeURIComponent(str);
		return JSON.parse(str);
	}
	function getResultObj(){
		var str = $(".tabladata_data_222").html();
		str = decodeURIComponent(str);
		return JSON.parse(str);
	}
	var methods = {
		getRow:function(){
			var checkbox = $(this).find("td input[name='"+id_name+"']");
			var obj_id;
			checkbox.each(function(checkIndex,item){
				if($(item).prop("checked")){
					obj_id = $(item).attr("value")
				}
			});
			var result = getResultObj();
			if(typeof(result.rows) == "object" &&result.rows.length>0){
				for(var i = 0;i<result.rows.length;i++){
					var row = result.rows[i];
					if (obj_id==row.id) {
						return row;
					}
				}
			}
		}
		,getSelected:function(){
			var checkbox = $(this).find("td input[name='"+id_name+"']");
			var obj_id;
			checkbox.each(function(checkIndex,item){
				if($(item).prop("checked")){
					obj_id = $(item).attr("value")
				}
			});
			return obj_id;
		},
		reload:function(){
			sysReload();
		},
		dataTable: function(obj) {
			id = $(this).prop("id");
			//加工table
			var table = machTable(id);
			//生成表格头
			var thread = createThead(table,id);
			//加工头的checkbox
			machTheadCheckBox(thread,obj.checkbox);
			//根据用户传入的数据格式对象获取表格总宽度
			var totalWidth=getTeadWidth(obj.columns);
			//加工得到完整的表头,并得到id属性名
			id_name = machTheadData(thread,totalWidth,obj.columns);
			//加载数据
			var str2 = JSON.stringify(obj);
			reloadData(table,id,id_name,obj);
			str2 = JSON.stringify(obj);
			//将一些二次需要的数据保存到页面
			/**
				checkbox;
				columns
				var id_name;
				var id;
			 */
			for (var x = 0; x < obj.columns.length; x++) {
				if(typeof(obj.columns[x].format) == "function"){
					obj.columns[x].format = new String(obj.columns[x].format);
				}
			}
			var jsObj = obj;
			jsObj.id = id;
			jsObj.id_name = id_name;
			var str = JSON.stringify(jsObj);
			str = encodeURIComponent(str);
			$("body").append("<div class='tabladata_data_111' style='display:none'>"+str+"</div>");
		}
	};
	$.fn.myLayui = function() {
		/**
	 高级查询加分页组件,如果没有高级查询,只需要在页面定义一个<br>
		 * <table id="xxx"></table><br>
		 * 如果有高级查询,并且autoQuery=false,页面上还需要一个<br>
		 * <form id="xxx_form"></form>,你可以自己定义一个div把form表单装进去,然后自己处理div的位置,如果没有则默认在表格上方
		 * */
		var defaults = {
				url:"123",//table数据的获取地址,需返回json格式
				columns:[],//table表的标题和没行数据的属性名,isField代表当前属性为主键标识.格式如下
				/**
				 * columns:[[    <br>
		        {field:'id',title:'编号',width:100,idField:true},  <br>  
		        {field:'code',title:'Code',width:100},   <br> 
		        {field:'name',title:'Name',width:100},  <br>  
		        {field:'price',title:'Price',width:100,align:'right'} <br>  
		    ]]  <br>
				 * */
				checkbox:false,//代表显示checkbox框--默认false不显示
				singleSelect:false,//单选/默认多选
				nopage:false,//不显示分页条,默认显示
				pagenumber:5,//显示几个页码默认5
				query:false,//是否有高级查询--如果为true,必须有form表单且id必须为table的id+"_form",默认false没有高级查询
				autoQuery:false,//默认false,是否自动生成高级查询
				onclick:function(item){},//当行被点击时执行的函数,item代表当前被点击的行数据
				/**
				 * 	如果为true下面queryDate的属性必带,且DTO必须有下面对应的高级查询参数属性<br>
				 * 	如果为false,必须有个这样的表单<form id="table的id_form"></form><br>
				 */
				queryData:[]//高级查询对应的结构数据,格式如下
		/** queryData:[<br>
		{name:"username",type="text",text:"账号",width="120px"},<br>
		{name:"infotime",type="datetime",text:"入职时间",width="120px"},<br>
		{name:"borthddate",type="date",text:"生日",width="120px",height:"35px",placeholder="请选择生日"},<br>
		{name:"dept",type="select",text:"部门",width="120px",url="/dept/getDepts",dataName:"name"--远程获取数据的显示名称属性,dataId:"id"--远程获取数据的id},<br>
	  ]默认150px宽35px高<br>
		 */
		}
	    var settings = $.extend({}, defaults, arguments[1]);
		// 获取我们的方法，遗憾的是，如果我们用function(method){}来实现，这样会毁掉一切的
		var method = arguments[0];
		// 检验方法是否存在
		if(methods[method]) {
			method = methods[method];
		} else {
			$.error( '函数: ' +  method + ' 不存在' );
			return this;
		}
		obj = settings;
		//初始化obj
		if (argumentFlag) {
			obj = settings;
			argumentFlag = false;
		}
		return method.call(this,settings);
	}
 
})(jQuery);
function loadData(){
	$("input[name='currentPage']").val(1);
	$("table").myLayui("reload");
}
/**
 * 点击重置按钮
 * @param id
 */
function resetForm(id){
	$("#"+id)[0].reset();
	loadData();
}
function openDialog(name,url){
	layui.use('layer', function(){
		 var layer = layui.layer;
			layer.open({
			  title:name,
			  type: 2,
			  area: ['80%', '80%'],
			  fixed: false, //不固定
			  maxmin: true,
			  content: url
			});
			$(".layui-layer-shade").css("opacity",0);
	}); 
}