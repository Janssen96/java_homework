<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
-->
</style>
</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 库房信息列表</span></td>
              </tr>
              
               
              
            </table></td>
            
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <a href="${url2 }add">添加新库房</a>
  
  <tr>
    <td>
    		 <form action="${url }" method="post">
               库房编号:<input type="text"  name="kufangbianhao"  value="${kufangbianhao }"/>
               库房名:<input type="text"  name="name"  value="${name }"/>

              
 				<input type="submit"  value="查询"/>
               </form>
    
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">库房编号</span></div></td>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">库房名</span></div></td>
        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">面积</span></div></td>

           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">最大货物量</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">剩余数量</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">报警最小数量</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">报警最大数量</span></div></td>
           
            <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">添加时间</span></div></td>
         

  		 <td width="16%" height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
      <c:forEach  items="${list}" var="bean">
       <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.kufangbianhao }</div></td>

        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.name }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.mianji }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.zuidashuliang }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.zuidashuliang-bean.rushushuliang }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.baojingzuixiaoshuliang }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.baojingzuidashuliang }</div></td>
        
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${fn:substring(bean.createtime,0, 19)}</div></td>


        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
        <a href="${url2 }update?id=${bean.id }">修改 | </a>
         
          <a href="${url2 }update3?id=${bean.id }">查看|</a>
          
           <a href="method!quyulist?kufangid=${bean.id }">区域信息列表|</a>
     
      </tr>
      </c:forEach>
     
      
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="100%">
        ${pagerinfo }
		</td>
        

          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
