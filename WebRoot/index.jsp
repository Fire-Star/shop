<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账号登录</title>
    <link rel="stylesheet" type="text/css" href="style/index.css">
    <link rel="stylesheet" type="text/css" href="style/button_01.css">
</head>
<body>
<article>
    <section class="all">
        <div class="back"><img src="image/goods/goods01.png"></div>
        <div class="mai">
            <div class="log_div">
                <h3>账号登录</h3>
                <p class="systemInfo">${sessionScope.systemError }&nbsp;</p>
                <form action="LoginSevlet" method="post">
                    </label><input type="text" id="nm" name="username" class="nm" placeholder="输入用户名/邮箱/手机号"><br>
                    <p class="usernameInfo">&nbsp;${sessionScope.usernameError }</p>
                    <input type="password" id="pw" name="password" class="pw" placeholder="输入密码"><br>
                    <p class="passwordInfo">&nbsp;${sessionScope.passwordError }</p>
                    <div class="ne_lo_con">
                        <span><input type="checkbox" class="ne_au_lo" name="ne_au_lo" id="ne_au_lo"><label for="ne_au_lo">下次自动登录</label></span>
                        <a href="#" class="forget_pw">忘记密码</a>
                    </div><br>
                    <input hidden="hidden" name="role" value="1">
                    <div class="log_active">
                        <input type="submit" id="log" class="log" value="登录">
                    </div>
                </form>
                <a class="reg_a" href="regist.jsp">免费注册</a>
            </div>
            <div class="log_by">
                <span>第三方登录</span>
                <a class="log_qq" href="#"><img src="image/qq_log.png"></a>
            </div>
      		<c:remove var="systemError" scope="session"/>
      		<c:remove var="usernameError" scope="session"/>
      		<c:remove var="passwordError" scope="session"/>
        </div>
    </section>
</article>
</body>
</html>

