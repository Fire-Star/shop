<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="style/regist.css">
</head>
<body>
<div class="container">
    <div class="registForm">
        <h2 class="title">网上购物账号注册</h2><br><hr><br><br>
        <p style="text-align: start;"><a href="index.jsp">有账号,直接登录</a></p>
        <div class="systeminfo">${sessionScope.systemError }</div>
        <form action="RegistServlet" method="post">
            <input type="text" name="username" value="${sessionScope.username }" class="username" placeholder="用户名/邮箱"><br>
            <div class="info">&nbsp;${sessionScope.usernameError }</div><br>
            <input type="password" value="${sessionScope.password }" class="password" placeholder="输入密码"><br>
            <div class="info">&nbsp;${sessionScope.passwordError }</div><br>
            <input type="password" value="${sessionScope.password }" class="password" placeholder="再次输入密码" name="password"><br>
            <div class="info"> &nbsp;${sessionScope.passwordError }</div><br>
            <div><input type="text" name="vertifycode" class="vertify"><img id="vertifyImg" src="VertifyCodeServlet" class="vertifyImg"></div>
            <div class="info">&nbsp;${sessionScope.vertifyError }</div><br>
            <input type="text" name="role" hidden value="1">
            <input type="submit" class="submit">
        </form>
    </div>
    <c:remove var="systemError" scope="session"/>
    <c:remove var="usernameError" scope="session"/>
    <c:remove var="passwordError" scope="session"/>
    <c:remove var="vertifyError" scope="session"/>
</div>
<script type="text/javascript" src="js/regist.js"></script>
</body>
</html>
