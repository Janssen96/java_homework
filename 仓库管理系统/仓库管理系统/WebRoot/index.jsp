<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="util.Util"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
Util.init(request);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>仓库管理信息系统</title>

<script type="text/javascript" src="js/js.js"></script>



</head>
<body   style=" background:url(images/bg.jpg);">
<div align="center"> 
<font color="#ffff80"><font size="4"><br /><br /><br /><br /><font size="5">仓库管理信息系统</font></font> 
</font></div><br />
<div align="center">
<form id="login" name="login" action="method!login" method="post">
  <div id="center">
    <div id="center_left"></div>
    <div id="center_middle">
      <div class="user">
        <label>用户名：
        <input type="text" name="username" id="user" />
        </label>
      </div>
      <div class="user">
        <label>密　码：
        <input type="password" name="password" id="pwd" size="21"/>
        </label>
      </div>
       <div class="user">
        <label>用户角色：
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <select name="role">
        <option value="0">仓库管理员</option>
         <option value="2">仓库主管</option>
          <option value="1">系统管理员</option>
        </select>
        
        </label>
      </div>
      
    </div>
    <div id="center_middle_right"></div>
    <div id="center_submit">
      <div class="button"> 
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="submit" value="提交" />
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <input type="reset" value="重置" />
       </div>
      
    </div>
    <div id="center_right"></div>
  </div>
</form>
</div>
<div id="footer"></div>
</body>
</html>
