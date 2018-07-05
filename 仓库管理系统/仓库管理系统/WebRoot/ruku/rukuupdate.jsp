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
<script type="text/javascript">
    var req;
   
    function Change_Select(selectedId){//当第一个下拉框的选项发生改变时调用该函数
      var now = new Date();
      var url = "method!getcate?pid="+selectedId+"&t="+now.getTime();
      if(window.XMLHttpRequest){
        req = new XMLHttpRequest();
      }else if(window.ActiveXObject){
        req = new ActiveXObject("Microsoft.XMLHTTP");
      }
      if(req){
        //指定回调函数为callback
      	req.onreadystatechange = callback;
        req.open("GET",url,true);
        req.send(null);
      }
    }
    //回调函数
    function callback(){
      if(req.readyState ==4){
        if(req.status ==200){
          parseMessage();//解析XML文档
        }else{
          alert("不能得到描述信息:" + req.statusText);
        }
      }
    }
    //解析返回xml的方法
    function parseMessage(){
      var xSel = req.responseXML.getElementsByTagName('select');//获得返回的XML文档
      //获得XML文档中的所有<select>标记
      var select_root = document.getElementById('cid');
      //获得网页中的第二个下拉框
      select_root.options.length=0;
      //每次获得新的数据的时候先把每二个下拉框架的长度清0
     
      for(var i=0;i<xSel.length;i++){
        var xValue = xSel[i].childNodes[0].firstChild.nodeValue;
        //获得每个<select>标记中的第一个标记的值,也就是<value>标记的值
        var xText = xSel[i].childNodes[1].firstChild.nodeValue;
        //获得每个<select>标记中的第二个标记的值,也就是<text>标记的值
        var option = new Option(xText, xValue);
        //根据每组value和text标记的值创建一个option对象
       
        try{
          select_root.add(option);//将option对象添加到第二个下拉框中
        }catch(e){
        }
      }
    }       






</SCRIPT>


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
                <td width="94%" valign="bottom"><span class="STYLE1"> 修改入库单信息</span></td>
              </tr>
            </table></td>
                   </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

<form action="method!rukuupdate2"  method="post" >
<table align="center" border="1"> 


<tr>
<td width="100" >产品信息</td>
<td width="300" > 
<select name="product">
<c:forEach items="${productlist}" var="bean1">
<option value="${bean1.id }" <c:if test="${bean.product.id==bean1.id }">selected</c:if> >
产品名:${bean1.name }&nbsp;&nbsp;&nbsp;
供应商:${bean1.gongyingshang }&nbsp;&nbsp;&nbsp;
生产地址:${bean1.shengchandizhi }&nbsp;&nbsp;&nbsp;
库存数量:${bean1.kucunshuliang }&nbsp;&nbsp;&nbsp;

缺货报警数量:${bean1.baojingxiaxian }&nbsp;&nbsp;&nbsp;
</option>
</c:forEach>
</select>
</td>
</tr>


<tr>
<td width="100" >已选择库房信息</td>
<td width="300" >
库房名:${bean.kufang.name }&nbsp;&nbsp;&nbsp;库房剩余数量:${bean.kufang.zuidashuliang-bean.kufang.rushushuliang }
</td>
</tr>

<tr>
<td width="100" >库房信息</td>
<td width="300" >
<select name="kufang" id="pid" onchange="Change_Select(this.value)">
<option value="0">请选择库房</option>
<c:forEach items="${kufanglist}" var="bean2">
<option value="${bean2.id }">库房名:${bean2.name }&nbsp;&nbsp;&nbsp;库房剩余数量:${bean2.zuidashuliang-bean2.rushushuliang }</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td width="100" >已选择库房区域信息</td>
<td width="300" >
${bean.quyu.quyuming }
</td>
</tr>

<tr>
<td width="100" >库房区域信息</td>
<td width="300" >
<select name="quyu" id="cid">
<option value="0">请选择库房区域</option>
</select>		
</td>
</tr>


<tr>
<td width="100" >入库理由</td>
<td width="300" >
<select name="rukuliyou">
<c:forEach items="${rukuliyoulist}" var="bean3">
<option value="${bean3.id }" <c:if test="${bean.rukuliyou.id==bean3.id }">selected</c:if> >${bean3.liyou }</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td width="100" >入库数量</td>
<td width="300" ><input type="text"  name="rukushuliang"  value="${bean.rukushuliang }"  /> </td>
</tr>

<input type="hidden"  name="id"  value="${bean.id }"  />

<tr>
<td width="100" >操作</td>
<td width="300" ><input type="submit"  value="提交"/> <input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" /></td>
</tr>

</table>
</form>



</body>
</html>
