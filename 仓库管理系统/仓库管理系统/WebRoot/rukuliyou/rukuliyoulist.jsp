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
                <td width="94%" valign="bottom"><span class="STYLE1"> 理由信息列表</span></td>
              </tr>
              
               
              
            </table></td>
            
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <a href="${url2 }add">添加新理由</a>
  
  <tr>
    <td>
    		 <form action="${url }" method="post">
    		  类型 <select name="infotype">
<option value="">所有类型</option>
<option value="入库" <c:if test="${infotype=='入库' }">selected</c:if> >入库</option>
<option value="出库" <c:if test="${infotype=='出库' }">selected</c:if> >出库</option>
<option value="调度" <c:if test="${infotype=='调度' }">selected</c:if> >调度</option>
</select>
    		 
               理由:<input type="text"  name="liyou"  value="${liyou }"/>
     

              
 				<input type="submit"  value="查询"/>
               </form>
    
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
      
      <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">类型</span></div></td>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">理由</span></div></td>

    
            <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">添加时间</span></div></td>
         

  		 <td width="16%" height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
      <c:forEach  items="${list}" var="bean">
       <tr>
       <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.infotype }</div></td>
       
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.liyou }</div></td>

         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${fn:substring(bean.createtime,0, 19)}</div></td>


        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
        <a href="${url2 }update?id=${bean.id }">修改 | 
        
         <a href="${url2 }delete?id=${bean.id }">删除|
         
          <a href="${url2 }update3?id=${bean.id }">查看|
     
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
