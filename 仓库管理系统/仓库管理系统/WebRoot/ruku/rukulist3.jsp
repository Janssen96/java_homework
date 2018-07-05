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
                <td width="94%" valign="bottom"><span class="STYLE1">在库查询</span></td>
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
               产品名:<input type="text"  name="product"  value="${product }"/>
               库房名:<input type="text"  name="kufang"  value="${kufang }"/>
               区域名:<input type="text"  name="quyu"  value="${quyu }"/>
               入库时间:<input type="text"  name="time1"  value="${time1 }"  onfocus='setDayHM(this);'/>
               <input type="text"  name="time2"  value="${time2 }"   onfocus='setDayHM(this);'/>
               

              
 				<input type="submit"  value="查询"/>
               </form>
    
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">入库单编号</span></div></td>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">产品名</span></div></td>
        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">库房名</span></div></td>
		<td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">区域名</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">入库理由</span></div></td>
           
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">入库数量</span></div></td>
   
           
            <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">入库时间</span></div></td>
         

  		
      </tr>
      <c:forEach  items="${list}" var="bean">
       <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.rukudanbianhao }</div></td>

        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.product.name }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.kufang.name }</div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.quyu.quyuming }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.rukuliyou.liyou }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.rukushuliang }</div></td>

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
