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
                <td width="94%" valign="bottom"><span class="STYLE1">审核内部调出申请单</span></td>
              </tr>
            </table></td>
                   </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

<form action="method!diaochuupdate6"  method="post" >
<table align="center" border="1"> 


<input type="hidden"   name="id" value="${bean.id }"  />

<tr>
<td width="100" >产品信息</td>
<td width="300" > 
<input type="text"   value="${bean.product.name }"  readonly="readonly"/>

</td>
</tr>

<tr>
<td width="100" >调出库房信息</td>
<td width="300" > 
<input type="text"   value="${bean.kufang1.name }"  readonly="readonly"/>

</td>
</tr>

<tr>
<td width="100" >调出区域信息</td>
<td width="300" > 
<input type="text"   value="${bean.quyu1.quyuming }"  readonly="readonly"/>

</td>
</tr>



<tr>
<td width="100" >调入库房信息</td>
<td width="300" >
库房名:${bean.kufang2.name }&nbsp;&nbsp;&nbsp;库房剩余数量:${bean.kufang2.zuidashuliang-bean.kufang2.rushushuliang }

</td>
</tr>


<tr>
<td width="100" >调入区域信息</td>
<td width="300" >
${bean.quyu2.quyuming }

</td>
</tr>

<tr>
<td width="100" >调度理由</td>
<td width="300" >
<input type="text"   value="${bean.rukuliyou.liyou }"  readonly="readonly"/>

</td>
</tr>

<tr>
<td width="100" >调度数量</td>
<td width="300" ><input type="text"  name="diaodushuliang"    value="${bean.diaodushuliang }" readonly="readonly" /></td>
</tr>


<tr>
<td width="100" >调度单状态</td>
<td width="300" >
<select name="shenhezhuangtai">
<option value="审核通过">审核通过</option>
<option value="审核未通过">审核未通过</option>
</select>
</td>
</tr>

<tr>
<td width="100" >审核反馈</td>
<td width="300" >
<textarea rows="7" cols="50" name="shenhefanhui"></textarea>
</td>
</tr>


<tr>
<td width="100" >操作</td>
<td width="300" >
<input type="submit"  value="提交"/>
<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" /></td>
</tr>

</table>
</form>



</body>
</html>
