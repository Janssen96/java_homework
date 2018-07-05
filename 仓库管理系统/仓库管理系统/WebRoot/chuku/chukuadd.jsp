<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

function checkform()
{
	 
	
	
	 if (document.getElementById('chukushuliangid').value=="")
	{
		alert("出库数量不能为空");
		return false;
	}
	

	
	
	
	 if (document.getElementById('chukushuliangid').value<0)
	{
		alert("出库数量不能小于0");
		return false;
	}


	return true;
	
}




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
                <td width="94%" valign="bottom"><span class="STYLE1"> 制定出库单</span></td>
              </tr>
            </table></td>
                   </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

<form action="method!chukuadd2"  method="post" onsubmit="return checkform() " > 
<table align="center" border="1"> 

<input type="hidden" name="kucunid"  value="${bean.id }"/>

<tr>
<td width="100" style="background-color: rgb(254, 237, 243);">产品信息</td>
<td width="500" style="background-color: rgb(254, 237, 243);"> 
产品名:${bean.product.name }&nbsp;&nbsp;&nbsp;
供应商:${bean.product.gongyingshang }&nbsp;&nbsp;&nbsp;
生产地址:${bean.product.shengchandizhi }&nbsp;&nbsp;&nbsp;
库存数量:${bean.shuliang }&nbsp;&nbsp;&nbsp;
可申请出库数量:${bean.keshengqingkecun }&nbsp;&nbsp;&nbsp;
缺货报警数量:${bean.product.baojingxiaxian }&nbsp;&nbsp;&nbsp;
</td>
</tr>


<tr>
<td width="100" >出库库房</td>
<td width="500" >
库房名:${bean.kufang.name} &nbsp;&nbsp;&nbsp; 
</td>
</tr>


<tr>
<td width="100"style="background-color: rgb(254, 237, 243);" >出库区域</td>
<td width="500" style="background-color: rgb(254, 237, 243);">
${bean.quyu.quyuming }
</td>
</tr>

<tr>
<td width="100" >出库理由</td>
<td width="500" >
<select name="liyou">
<c:forEach items="${rukuliyoulist}" var="bean3">
<option value="${bean3.id }">${bean3.liyou }</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td width="100" style="background-color: rgb(254, 237, 243);">出库数量</td>
<td width="500"style="background-color: rgb(254, 237, 243);" ><input type="text"  name="chukushuliang" id="chukushuliangid"   /> </td>
</tr>

<tr>
<td width="100" >经销商</td>
<td width="500" ><input type="text"  name="jingxiaoshang"    /> </td>
</tr>

<tr>
<td width="100" style="background-color: rgb(254, 237, 243);">联系电话</td>
<td width="500"style="background-color: rgb(254, 237, 243);" ><input type="text"  name="lianxidianhua"    /> </td>
</tr>



<tr>
<td width="100" >操作</td>
<td width="500" ><input type="submit"  value="提交"/> <input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" /></td>
</tr>

</table>
</form>



</body>
</html>
