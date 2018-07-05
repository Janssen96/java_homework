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
                <td width="94%" valign="bottom"><span class="STYLE1">审核出库单</span></td>
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
               出库单状态:
               <select name="shenhezhuangtai">
               <option value=""  >所有状态</option>
                <option value="未审核" <c:if test="${shenhezhuangtai=='未审核' }">selected</c:if> >未审核</option>
                 <option value="审核通过" <c:if test="${shenhezhuangtai=='审核通过' }">selected</c:if>>审核通过</option>
                  <option value="审核未通过" <c:if test="${shenhezhuangtai=='审核未通过' }">selected</c:if>>审核未通过</option>
                  <option value="成功出库" <c:if test="${shenhezhuangtai=='成功出库' }">selected</c:if>>成功出库</option>
               </select>

              
 				<input type="submit"  value="查询"/>
               </form>
    
    <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
      
 <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">添加用户</span></div></td>
        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">出库单编号</span></div></td>

        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">产品名</span></div></td>
        <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">库房名</span></div></td>
         <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">区域名</span></div></td>
          <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">经销商</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">联系电话</span></div></td>

           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">出库理由</span></div></td>
           
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">出库数量</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">出库单状态</span></div></td>
           <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">审核反馈</span></div></td>
           
            <td  height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">添加时间</span></div></td>
         

  		 <td width="16%" height="20" bgcolor="d3eaef" ><div align="center"><span class="STYLE10">操作</span></div></td>
      </tr>
      <c:forEach  items="${list}" var="bean">
       <tr>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.user.username }</div></td>
       
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.bianhao }</div></td>

        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.product.name }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.kufang.name }</div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.quyu.quyuming }</div></td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.jingxiaoshang }</div></td>
           <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.lianxidianhua }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.liyou.liyou }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.chukushuliang }</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.shenhezhuangtai }</div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${bean.shenhefanhui }</div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${fn:substring(bean.createtime,0, 19)}</div></td>


        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
        
          
          <c:if test="${bean.shenhezhuangtai=='未审核'}">
          
           <a href="${url2 }update5?id=${bean.id }">审核 
          
          </c:if>
          </td>
     
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
