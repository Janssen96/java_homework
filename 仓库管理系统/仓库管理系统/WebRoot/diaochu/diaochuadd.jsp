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


<script language="javascript" type="text/javascript">

function checkform()
{
	 
	
	
	 if (document.getElementById('diaodushuliangid').value=="")
	{
		alert("调出数量不能为空");
		return false;
	}
	

	
	
	
	 if (document.getElementById('diaodushuliangid').value<0)
	{
		alert("调出数量不能小于0");
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
                <td width="94%" valign="bottom"><span class="STYLE1">制定内部调出申请单</span></td>
              </tr>
            </table></td>
                   </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>

<form action="method!diaochuadd2"  method="post" onsubmit="return checkform() " > 
<table align="center" border="1"> 
<input type="hidden"   name="kucunid" value="${bean.id }"  />

<tr>
<td width="100" style="background-color: rgb(254, 237, 243);">产品信息</td>
<td width="400" style="background-color: rgb(254, 237, 243);"> 
<input type="text"   value="${bean.product.name }"  readonly="readonly"/>
<input type="hidden"   name="product" value="${bean.product.id }"  />
</td>
</tr>

<tr>
<td width="100" >调出库房信息</td>
<td width="400" > 
库房名:${bean.kufang.name }&nbsp;&nbsp;&nbsp;区域名:${bean.quyu.quyuming }&nbsp;&nbsp;&nbsp;可申请调度数量:${bean.keshengqingkecun }
<input type="hidden"   name="kufang1" value="${bean.kufang.id }"  />
</td>
</tr>


<tr>
<td width="100" style="background-color: rgb(254, 237, 243);">调出库房区域</td>
<td width="400" style="background-color: rgb(254, 237, 243);"> 
${bean.quyu.quyuming }
<input type="hidden"   name="quyu1" value="${bean.quyu.id }"  />
</td>
</tr>




<tr>
<td width="100" >调入库房信息</td>
<td width="400" >
<select name="kufang2" id="pid" onchange="Change_Select(this.value)">
<option value="0">请选择调入库房</option>
<c:forEach items="${kufanglist}" var="bean2">
<option value="${bean2.id }">库房名:${bean2.name }&nbsp;&nbsp;&nbsp;库房剩余数量:${bean2.zuidashuliang-bean2.rushushuliang }</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td width="100" style="background-color: rgb(254, 237, 243);">调入库房区域信息</td>
<td width="400" style="background-color: rgb(254, 237, 243);">
<select name="quyu2" id="cid">
<option value="">请选择调入库房区域</option>
</select>		
</td>
</tr>

<tr>
<td width="100" >调度理由</td>
<td width="400" >
<select name="rukuliyou">
<c:forEach items="${rukuliyoulist}" var="bean3">
<option value="${bean3.id }">${bean3.liyou }</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td width="100"style="background-color: rgb(254, 237, 243);" >调度数量</td>
<td width="400"style="background-color: rgb(254, 237, 243);" ><input type="text"  name="diaodushuliang"  id="diaodushuliangid"   /> </td>
</tr>



<tr>
<td width="100" >操作</td>
<td width="400" ><input type="submit"  value="提交"/> <input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" /></td>
</tr>

</table>
</form>



</body>
</html>
