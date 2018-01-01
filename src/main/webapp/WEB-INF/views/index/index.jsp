<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="/static/img/high_school_128px_1200853_easyicon.net.ico" type="image/x-icon"/>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>校园O2O后台管理系统</title>
  <link rel="stylesheet" href="/static/layui/css/layui.css">
 <link rel="stylesheet" href="/static/build/css/app.css" media="all">
</head>
<body>
    <div class="layui-layout layui-layout-admin kit-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">校园O2O后台管理系统</div>
            <div class="layui-logo kit-logo-mobile">K</div>
            <ul class="layui-nav layui-layout-right kit-nav">
<!--                 <li class="layui-nav-item"><a href="javascript:;" id="pay"><i class="fa fa-gratipay" aria-hidden="true"></i> 捐赠我</a></li> -->
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://m.zhengjinfan.cn/images/0.jpg" class="layui-nav-img"> 胡鹏
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">基本资料</a></dd>
                        <dd><a href="javascript:;">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="javascript:;"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black kit-side">
            <div class="layui-side-scroll">
                <div class="kit-side-fold"><i class="fa fa-navicon" aria-hidden="true"></i></div>
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span>夜猫店</span></a>
                        <dl class="layui-nav-child">
                            <dd>
                                <a href="javascript:;" kit-target data-options="{url:'/store/list',icon:'&#xe6c6;',title:'夜猫店管理',id:'1'}"><i class="layui-icon">&#xe614;</i><span>夜猫店管理</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="form.html" data-icon="fa-user" data-title="订单管理" kit-target data-id='2'><i class="layui-icon">&#xe614;</i><span>订单管理</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="/storeAdmin/list" data-icon="&#xe628;" data-title="管理员管理" kit-target data-id='3'><i class="layui-icon">&#xe614;</i><span>管理员管理</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="nav.html" data-icon="&#xe628;" data-title="申请审核" kit-target data-id='3'><i class="layui-icon">&#xe628;</i><span>申请审核</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" data-url="list4.html" data-icon="&#xe614;" data-title="收入统计" kit-target data-id='4'><i class="layui-icon">&#xe658;</i><span>收入统计</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" kit-target data-options="{url:'https://www.baidu.com',icon:'&#xe658;',title:'评论管理',id:'5'}"><i class="layui-icon">&#xe658;</i><span>评论管理</span></a>
                            </dd>
                            <dd>
                                <a href="javascript:;" kit-target data-options="{url:'https://www.baidu.com',icon:'&#xe658;',title:'楼栋管理',id:'5'}"><i class="layui-icon">&#xe658;</i><span>楼栋管理</span></a>
                            </dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span>二手交易管理</span></a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" kit-target data-options="{url:'navbar.html',icon:'&#xe658;',title:'Navbar',id:'6'}"><i class="layui-icon">&#xe658;</i><span>订单管理</span></a></dd>
                            <dd><a href="javascript:;" kit-target data-options="{url:'tab.html',icon:'&#xe658;',title:'TAB',id:'7'}"><i class="layui-icon">&#xe658;</i><span>信息管理</span></a></dd>
                            <dd><a href="javascript:;" kit-target data-options="{url:'onelevel.html',icon:'&#xe658;',title:'OneLevel',id:'50'}"><i class="layui-icon">&#xe658;</i><span>....</span></a></dd>
                            <dd><a href="javascript:;" kit-target data-options="{url:'app.html',icon:'&#xe658;',title:'App',id:'8'}"><i class="layui-icon">&#xe658;</i><span>.....</span></a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item layui-nav-itemed">
                        <a href="javascript:;"><i class="fa fa-plug" aria-hidden="true"></i><span>系统管理管理</span></a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" kit-target data-options="{url:'navbar.html',icon:'&#xe658;',title:'管理员管理',id:'6'}"><i class="layui-icon">&#xe614;</i><span>管理员管理</span></a></dd>
                            <dd><a href="javascript:;" kit-target data-options="{url:'tab.html',icon:'&#xe658;',title:'权限管理',id:'7'}"><i class="layui-icon">&#xe614;</i><span>权限管理</span></a></dd>
                            <dd><a href="javascript:;" kit-target data-options="{url:'onelevel.html',icon:'&#xe658;',title:'菜单管理',id:'50'}"><i class="layui-icon">&#xe614;</i><span>菜单管理</span></a></dd>
                            <dd><a href="javascript:;" kit-target data-options="{url:'/user/list',icon:'&#xe658;',title:'用户管理',id:'8'}"><i class="layui-icon">&#xe614;</i><span>用户管理</span></a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>
        <div class="layui-body" id="container">
            <!-- 内容主体区域 -->
            <div style="padding: 15px;">主体内容加载中,请稍等...</div>
        </div>

        <div class="layui-footer">
            <!-- 底部固定区域 -->
           Copyright © 2017 泸州职业技术学院, All rights reserved.  技术支持：鹏信软件工作室

        </div>
    </div>
    <script type="text/javascript">
//         var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
//         document.write(unescape("%3Cspan id='cnzz_stat_icon_1264021086'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s22.cnzz.com/z_stat.php%3Fid%3D1264021086%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));
    </script>
    <script src="/static/layui/layui.js"></script>
    <script>
        var message;
        layui.config({
            base: '/static/build/js/'
        }).use(['app', 'message'], function() {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();
        });
    </script>
</body>

</html>