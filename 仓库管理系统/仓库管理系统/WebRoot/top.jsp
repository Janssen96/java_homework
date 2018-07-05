<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {
	font-size: 12px;
	color: #000000;
}
.STYLE5 {font-size: 12}
.STYLE7 {font-size: 12px; color: #FFFFFF; }
.STYLE7 a{font-size: 12px; color: #FFFFFF; }
a img {
	border:none;
}
-->
</style></head>
<c:if test="${user.role==1}">
<body  background="images/top1.jpg">
</c:if>
<c:if test="${user.role==0}">
<body  background="images/top0.jpg">
</c:if>
<c:if test="${user.role==2}">
<body  background="images/top2.jpg">
</c:if>

<div align="right" style="margin-top:  -30px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="378" height="57" >&nbsp;</td>
        <td>&nbsp;</td>
        <td width="281" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="33" height="27"><img src="images/main_05.gif" width="33" height="27" /></td>
            <td width="248" background="images/main_06.gif"><table width="225" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="17"><div align="right"><a href="method!changepwd" target="rightFrame"><img src="images/pass.gif" width="69" height="17" /></a></div></td>
                <td><div align="right"><a href="method!user" target="rightFrame"><img src="images/user.gif" width="69" height="17" /></a></div></td>
                <td><div align="right"><a href="method!loginout" target="_parent"><img src="images/quit.gif" alt=" " width="69" height="17" /></a></div></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table>
</div>

<br/>
<br/>
<div style="padding-left: 320px;padding-top:40px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>

            <td width="21" class="STYLE7"><img src="images/main_15.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center"><a href="javascript:history.go(-1);"><span style="font-size: 15px;color:black;">后退</span></a></div></td>
            <td width="21" class="STYLE7"><img src="images/main_17.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center"><a href="javascript:history.go(1);"><span style="font-size: 15px;color:black;">前进</span></a></div></td>
            <td width="21" class="STYLE7"><img src="images/main_19.gif" width="19" height="14" /></td>
            <td width="35" class="STYLE7"><div align="center"><a href="javascript:window.parent.location.reload();"><span style="font-size: 15px;color:black;">刷新</span></a></div></td>


            <td>&nbsp;</td>
          </tr>
        </table>      
        </div> 
</body>
</html>
