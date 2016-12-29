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
    <title>状态页面</title>
    <link rel="stylesheet" href="style/statepage.css">
	<script type="text/javascript">
		var end=2;
		var start=1;
		var time=setInterval(function(){
			if(start==end){
				clearInterval(time);
				window.location="<c:url value='/${sessionScope.location}'/>";
			}
			start++;
		},1000);
	</script>
</head>
<body>
<div class="container">
    <div class="mod">
        <img src="../image/tishi/${sessionScope.tishiimgurl }">
        <p class="systemInfo">${sessionScope.systemError }</p>
    </div>
</div>
<c:remove var="location" scope="session"/>
<c:remove var="tishiimgurl" scope="session"/>
<c:remove var="systemError" scope="session"/>
</body>
</html>
