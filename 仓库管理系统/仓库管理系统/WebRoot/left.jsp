<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.dimensions.js"></script>
<script type="text/javascript" src="js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">
  
  <c:if test="${user.role==2}">
  
  
  <li> <a class="head">库存基础信息管理</a>
      <ul>
        <li><a href="method!kufanglist" target="rightFrame">库房设置</a></li>
         <li><a href="method!rukuliyoulist" target="rightFrame">理由设置</a></li>
      </ul>
    </li>
  
  
  
  <li> <a class="head">申请审核管理</a>
      <ul>
        <li><a href="method!rukulist2" target="rightFrame">审核入库单</a></li>
        <li><a href="method!chukulist2" target="rightFrame">审核出库单</a></li>
        <li><a href="method!diaochulist3" target="rightFrame">审核移库单</a></li>
      </ul>
    </li>
    
    
      <li> <a class="head">综合管理</a>
      <ul>
       <li><a href="method!kucunlist7" target="rightFrame">动态库查询</a></li>
       <li><a href="method!mingxilist2" target="rightFrame">出入库明细查询</a></li>
       <li><a href="method!kucunlist8" target="rightFrame">库存盘点</a></li>
      
      </ul>
    </li>
    
     <li> <a class="head">预警查询</a>
      <ul>
        <li><a href="method!baojinglist" target="rightFrame">预警查询</a></li>
      </ul>
    </li>
  
  </c:if>
  
  
  
  <c:if test="${user.role==1}">

    
    
   
    
     <li> <a class="head">用户管理</a>
      <ul>
        <li><a href="method!userlist" target="rightFrame">仓库管理员管理</a></li>
        <li><a href="method!userlist2" target="rightFrame">仓库主管管理</a></li>
      </ul>
    </li>
    
    
    
    

      </c:if>
      
      
      <c:if test="${user.role==0}">

    <li> <a class="head">产品管理</a>
      <ul>
        <li><a href="method!productlist" target="rightFrame">产品管理</a></li>
      </ul>
    </li>
    
     <li> <a class="head">入库管理</a>
      <ul>
        <li><a href="method!rukulist" target="rightFrame">入库登记</a></li>
        <li><a href="method!rukulist3" target="rightFrame">在库查询</a></li>
		
      </ul>
    </li>
    
    
     <li> <a class="head">出库管理</a>
      <ul>
        <li><a href="method!chukulist" target="rightFrame">出库登记</a></li>
        <li><a href="method!chukulist3" target="rightFrame">出库查询</a></li>
		
      </ul>
    </li>
    
    
     <li> <a class="head">移库管理</a>
      <ul>
   
		<li><a href="method!diaochulist" target="rightFrame">移库申请</a></li>
		<li><a href="method!diaochulist2" target="rightFrame">移库查询</a></li>
      </ul>
    </li>
    
    
     <li> <a class="head">综合管理</a>
      <ul>
       <li><a href="method!kucunlist5" target="rightFrame">动态库查询</a></li>
       <li><a href="method!mingxilist" target="rightFrame">出入库明细查询</a></li>
       <li><a href="method!kucunlist6" target="rightFrame">库存盘点</a></li>
      
      </ul>
    </li>
    
    <li> <a class="head">预警查询</a>
      <ul>
        <li><a href="method!baojinglist" target="rightFrame">预警查询</a></li>
      </ul>
    </li>

    </c:if>
      
      
   
  </ul>
</div>
</body>
</html>
