<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script language="javascript" type="text/javascript">


</script>


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
                <td width="94%" valign="bottom"><span class="STYLE1"> 修改产品信息</span></td>
              </tr>
            </table></td>
                   </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

<form action="method!productupdate2"  method="post" >
<table align="center" border="1"> 

<input type="hidden"  name="id" value="${bean.id }"/>
<tr>
<td width="100" >产品名</td>
<td width="300" ><input type="text"  name="name" value="${bean.name }" readonly="readonly" size="40"/> </td>
</tr>


<tr>
<td width="100" >生产地址</td>
<td width="300" ><input type="text"  name="shengchandizhi"   value="${bean.shengchandizhi }" readonly="readonly" size="40"/> </td>
</tr>

<tr>
<td width="100" >供应商</td>
<td width="300" ><input type="text"  name="gongyingshang"   value="${bean.gongyingshang }" readonly="readonly" size="40"/> </td>
</tr>

<tr>
<td width="100" >缺货报警数量  </td>
<td width="300" ><input type="text"  name="baojingxiaxian"  value="${bean.baojingxiaxian }"  readonly="readonly" size="40"/> </td>
</tr>





<tr>
<td width="100" >操作</td>
<td width="300" ><input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" /></td>
</tr>

</table>
</form>



</body>
</html>
