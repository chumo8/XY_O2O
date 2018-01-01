var element;
layui.use('element', function(){
	element = layui.element();
}); 
$(function(){
	h = $("#mainBox").height();
	var layer;
	var ii;
	layui.use('layer', function(){
		  layer = layui.layer;
		  ii = layer.load();
	}); 
	$('#menuTree').tree({    
	    url: '/index/getResource',    
	    lines:true,
	    state:true,
	    onLoadSuccess:function(){
	    	layer.close(ii);
	    },
 	    loadFilter: function(data){ 
 	    	data = JSON.parse(data);
 	    	for (var i = 0; i < data.length; i++) {
				data[i].text = data[i].name;
				if (data[i].id==99) {
					//打开首页
		 	    	element.tabAdd("main", {
						title:data[i].name
						,content:"<iframe src='"+data[i].url+"' width='100%' height='"+(h-65)+"' style='border:0;'></iframe>"
						,id:data[i].id
					})
					selectTab("main",data[i].id);
				}
				if(typeof(data[i].children) == "object"){
					for (var j = 0; j < data[i].children.length; j++) {
						data[i].children[j].text = data[i].children[j].name;
						if(typeof(data[i].children[j].children) == "object"){
							for (var k = 0; k < data[i].children[j].children.length; k++) {
								data[i].children[j].children[k].text = data[i].children[j].children[k].name;
							}
						}
					}
				}
			}
 	    	return data;
 	    } 
		,onClick:function(node){
			if(node.url){
				var tabsArrIds = new Array(); 
				var lis = $(".layui-tab-title").children();
				for (var i = 0; i < lis.length; i++) {
					var id = $(lis[i]).attr("lay-id")+"";
					if ($.inArray(id,tabsArrIds)==-1) {
						tabsArrIds.push($(lis[i]).attr("lay-id"));
					}
				}
				if ($.inArray(node.id+"",tabsArrIds)==-1) {
					element.tabAdd("main", {
						title:node.text
						,content:"<iframe src='"+node.url+"' width='100%' height='"+(h-65)+"' style='border:0;'></iframe>"
						,id:node.id
					})
					tabsArrIds.push(node.id+"");
					selectTab("main",node.id);
				}else{
					selectTab("main",node.id);
				}
			}
		}
	});  
})
function selectTab(name,id){
	element.tabChange("main", id);
}



