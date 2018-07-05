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
<script src="js/Calendar.js"></script>

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
                <td width="94%" valign="bottom"><span class="STYLE1">出入库明细查询</span></td>
              </tr>
              
               
              
            </table></td>
            
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
 
  
  <tr>
    <td>
    		 <form action="${url }" method="post">
               产品名:<input type="text"  name="chanpin"  value="${chanpin }"/>
                供应商:<input type="text"  name="gongyingshang"  value="${gongyingshang }" size="10"/>
               经销商:<input type="text"  name="jingxiaoshang"  value="${jingxiaoshang }" size="10"/>
               明细类型:
               <select name="leixing">
               <option value="">所有类型</option>
                <option value="产品入库"  <c:if test="${leixing=='产品入库' }">selected</c:if>>产品入库</option>
                <option value="产品调入"  <c:if test="${leixing=='产品调入' }">selected</c:if>>产品调入</option>
                 <option value="产品移库"  <c:if test="${leixing=='产品移库' }">selected</c:if>>产品移库</option>
              
               </select>
               
               添加时间:<input type="text"  name="time1"  value="${time1 }"  onfocus='setDayHM(this);'/>
               <input type="text"  name="time2"  value="${time2 }"   onfocus='setDayHM(this);'/>
               

              
 				<input type="submit"  value="查询"/>
               </form>
    
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">明细类型</span></div></td>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">产品</span></div></td>
        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">数量</span></div></td>

           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">经办人</span></div></td>
            <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">理由</span></div></td>
           
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">库房</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">审核人</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">供应商</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">经销商</span></div></td>
   
           
            <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">添加时间</span></div></td>
         

  		
      </tr>
      <c:forEach  items="${list}" var="bean">
       <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.leixing }</div></td>

        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.chanpin }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.shuliang }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.user.username }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.liyou }</div></td>
		<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.content }</div></td>
		<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.shenheren }</div></td>
		<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.gongyingshang }</div></td>
		<td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.jingxiaoshang }</div></td>
		
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${fn:substring(bean.createtime,0, 19)}</div></td>


       
          
     
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
