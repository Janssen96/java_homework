<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>新用户注册</title>
		<%
		response.addHeader("Cache-Control", "no-store, must-revalidate"); 
		response.addHeader("Expires", new java.util.Date().getTime()+"");
		%>
		
	<style type="text/css">
#regdiv {
	position: absolute;
	width: 700px;
	height: 500px;
	background-image: url(img/b2c_04.jpg);
}
</style>
<script language="javascript" type="text/javascript" src="jquery.min.js"></script>

<script language="javascript" type="text/javascript">

function checkregisterform()
{
	 
	 if (document.getElementById('usernameid').value=="")
	{
		alert("用户名不能为空");
		return false;
	}
	var valid=/^\w+$/;
	if(!valid.test(document.getElementById('usernameid').value)){
		alert("用户名必须是数字、字母或下划线");
		return false;
	}

	if (document.getElementById('passwordid').value=="")
	{
		alert("密码不能为空");
		return false;
	}
	if (document.getElementById('passwordid').value.length<6)
	{
		alert("密码长度必须大于6位");
		return false;
	}
	if (document.getElementById('password2id').value != document.getElementById('passwordid').value)
	{
		alert("确认密码与密码不一致");
		return false;
	}	 
	if (document.getElementById('truenameid').value=="")
	{
		alert("真实姓名不能为空");
		return false;
	}
	if (document.getElementById('addressid').value=="")
	{
		alert("地址不能为空");
		return false;
	}
	if (document.getElementById('phoneid').value=="")
	{
		alert("手机不能为空");
		return false;
	}
	
	valid=/^0?1[3,4,5,6,7,8,9][0,1,2,3,4,5,6,7,8,9]\d{8}$/;  
	if(!valid.test(document.getElementById('phoneid').value)){
		alert("请输入正确的手机格式");
		return false;
	}
	
	if (document.getElementById('emailid').value=="")
	{
		alert("email不能为空");
		return false;
	}
 


	var reg = new RegExp('^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-z][a-z.]{2,8}$');

	if(!reg.test(document.getElementById('emailid').value)){
		alert("请输入正确的email格式");
		return false;
	}
	if (document.getElementById('qqid').value=="")
	{
		alert("qq不能为空");
		return false;
	}
	valid = /^\d{1,20}$/;
	if(!valid.test(document.getElementById('qqid').value)){
		alert("请输入正确的qq格式");
		return false;
	}
	doRequestUsingPOSTregister();
	
}

function createQueryStringregister(){
	//必须两次编码才能解决中文问题
	var username = encodeURI(encodeURI($("#usernameid").val()));
	var password = encodeURI(encodeURI($("#passwordid").val()));
	var truename = encodeURI(encodeURI($("#truenameid").val()));
	var address = encodeURI(encodeURI($("#addressid").val()));
	var phone = encodeURI(encodeURI($("#phoneid").val()));
	var email = encodeURI(encodeURI($("#emailid").val()));
	var qq = encodeURI(encodeURI($("#qqid").val()));
	var queryString = 
	"username="+username+
	"&password="+password+
	"&truename="+truename+
	"&address="+address+
	"&phone="+phone+
	"&email="+email+
	"&qq="+qq;
	return queryString;
}



function doRequestUsingPOSTregister(){

		$.ajax({
			type: "POST",
			url: "register",
			data: createQueryStringregister(),
			success: function(data){
			var returnvalue = decodeURI(data);
				alert(returnvalue);
				window.close();
			}
		});
}


function resetform(){
window.close();
}
</script>


</head>


	<body>



		<div id="regdiv">

			<br />
			<br />
			<table align="center">
				<tr align="center">
					<td>
						新用户注册
					</td>
				</tr>
			</table>
			
			
				<table align="center" border="1" cellpadding="5" cellspacing="3">
					<tr>
						<td>
							用户名：
						</td>
						<td>
							<input type="text" name="username" size="25"  id="usernameid"/>
						</td>
					</tr>
					<tr>
						<td>
							密码：
						</td>
						<td>
							<input type="password" name="password" size="25" id="passwordid"/>
						</td>
					</tr>
					<tr>
						<td>
							确认密码：
						</td>
						<td>
							<input type="password" name="password2" size="25" id="password2id"/>
						</td>
					</tr>
					<tr>
						<td>
							真实姓名
						</td>
						<td>
							<input type="text" name="truename" size="25" id="truenameid"/>
						</td>
					</tr>
					<tr>
						<td>
							地址
						</td>
						<td>
							<input type="text" name="address" size="25" id="addressid"/>
						</td>
					</tr>
					<tr>
						<td>
							手机
						</td>
						<td>
							<input type="text" name="phone" size="25" id="phoneid" />
						</td>
					</tr>
					<tr>
						<td>
							email
						</td>
						<td>
							<input type="text" name="email" size="25" id="emailid"/>
						</td>
					</tr>
					<tr>
						<td>
							qq
						</td>
						<td>
							<input type="text" name="qq" size="25" id="qqid"/>
						</td>
					</tr>
					<tr>

						<td colspan="2">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" value="注册"  onclick="checkregisterform()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="取消" onclick="resetform()"/>
						</td>
					</tr>
				</table>


		</div>

	</body>

</html>
