<%@page import="exam.domain.CategoryItem"%>
<%@page import="exam.service.CategoryService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="categoryService" class="exam.service.CategoryService"/>
<%
categoryService.loadCategory();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>网上书城</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="container">
    <div class="top">
        <a href="#"><div class="logo"><img src="images/logo.jpg">中华商城</div></a>
        <div class="top_control">
            <div class="serach">
                <input type="text" class="serachtext" placeholder="商品名称" /><span id="toserach" class="toserach">搜索</span>
            </div>
            <a class="shoppingcart" href="<c:url value="/mainpage/shoppingcartpage/shopping.jsp"></c:url>">
            	<img src="images/shopcart.png"><span>购物车</span>
            </a>
            <div class="loginstate">
            <c:choose>
	            <c:when test="${sessionScope.user.username==null}">
	            	<c:set value="登录/注册" var="stateinfo"></c:set>
	            	<c:set var="loginorregisturl" value="${pageContext.request.contextPath }/index.jsp"></c:set>
	            </c:when>
	            <c:when test="${sessionScope.user.username!=null}">
	            	<c:set var="stateinfo" value="${sessionScope.user.username}"></c:set>
	            </c:when>
            </c:choose>
           		 <c:if test="${sessionScope.user!=null }">
           		 	<div class="userinfo" >
                    <div class="userintroduce">
                        <span class="infotitle">个人简介</span>
                        <p class="infodetail">
                        	<c:choose>
                        		<c:when test="${sessionScope.personinfo==null }">暂无个人简介</c:when>
                        		<c:otherwise>${sessionScope.personinfo }</c:otherwise>
                        	</c:choose>
                        </p>
                    </div>
                    <div class="statecontrol">
                        <a class="loginout" href='<c:url value="/ExitServlet"></c:url>'>退出</a>
                    </div>
                	</div>
           		 </c:if>
                <a href="${loginorregisturl }" class="loginorregist">${stateinfo }</a>
            </div>
        </div>
    </div>
    <div class="middle">
        <div class="left">
        <c:forEach var="first" items="${categoryService.firstCategory }">
        	<div class="item">
                <div class="click"><h2 class="firsttitle">${first.name}</h2></div>
                <ul class="items">
                        <div class="item">
                        	<%
                           		categoryService.setSecondCategory(((CategoryItem)pageContext.findAttribute("first")).getCode());
                           	%>
                        	<c:forEach var="second" items="${categoryService.secondCategory }">
                           		<a href="javascript:getURL('${second.code }','${first.name}','${second.name}',true);"><h4 code="${second.code }" class="secondtitle">${second.name}</h4></a>
                           		<%
                           		categoryService.setSecondCategory(((CategoryItem)pageContext.findAttribute("first")).getName());
                           		%>
                        	</c:forEach>
                        </div>
                </ul>
            </div>
        </c:forEach>
            
        </div>
        <div class="right">
        <div class="pathInfo">&nbsp;当前位置:</div>
        <div id="allgooditem"></div>
        </div>
    </div>
    <div class="bottom">
        <a class="prepage" onclick="topre();">上一页</a>
        <a class="nexpage" onclick="tonext();">下一页</a>
    </div>
</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/index.js"></script>
<script type="text/javascript" src="../ajax-libs/ajax-lib.js"></script>
<script type="text/javascript">
	var curpage=1;
	var catetonext="<c:url value="/BrowerBookServlet"></c:url>?tonext=1";
	var catetopre="<c:url value="/BrowerBookServlet"></c:url>?topre=1";
	var serachtonext;
	var serachtopre;
	var tonexttemp;
	var topretemp;
	var key;
	
	function bandTag(tagClassName){
		$(tagClassName).css({"background-color":"gray"});
		$(tagClassName).mouseover(function(){
			$(tagClassName).css({"color":"white","border":"none","cursor":"not-allowed","background-color":"gray"});
		});
		$(tagClassName).mouseleave(function(){
			$(tagClassName).css({"color":"white","border":"none","background-color":"gray"});
		});
		$(tagClassName)[0].onclick=function(){return false;}
	}
	function reTag(tagClassName,callback){
		$(tagClassName).css({"background-color":"#499EFF"});
		$(tagClassName).mouseover(function(){
			$(tagClassName).css({"color":"#499EFF","border":"1px solid #499EFF","cursor":"pointer","background-color":"white"});
		});
		$(tagClassName).mouseleave(function(){
			$(tagClassName).css({"color":"white","border":"none","background-color":"#499EFF"});
		});
		$(tagClassName)[0].onclick=callback;
	}
	
	function locadBooks(data){
		var len=data.length;
		var contain=document.getElementById("allgooditem");
		
		contain.innerHTML="";
		for(var i=0;i<len;i++){
			var gooditem="<div class=\"gooditem\">"+
						"<img class=\"goodimg\" src=\"<c:url value="/BookCoverServlet"/>?index="+i+"&uuid="+new Date().getTime()+"\">"+
						"<div class=\"goodinfo\">"+
						"<p class=\"price\">价格:"+data[i].price+"</p>"+
						"<p class=\"bookname\">书名:"+data[i].bookname+"</p>"+
						"<p class=\"sellcount\">销售量:"+data[i].booksell+"</p>"+
						"<p class=\"sellcontrol\">"+
						"<a class=\"addtocart\" href=\"<c:url value="/ShoppingCartServlet"></c:url>?bookid="+data[i].id+"&bookname="+data[i].bookname+"&username=${user.username}&price="+data[i].price+"&o=a\">加入购物车</a>"+
						"</p>"+
						"</div>"+
						"</div>";
			contain.innerHTML+=gooditem;
		}
		if(len==0){
			contain.innerHTML="<div style=\"text-align: center;color: red;margin: 100px 20px auto;\">已经到底了。。。</div>";
		}
		if(len<12){
			bandTag(".nexpage",tonext);
		}else{
			reTag(".nexpage",tonext);
		}
		if(curpage==1){
			bandTag(".prepage",topre);
		}else{
			reTag(".prepage",topre);
		}
		toTop();
	}
	
	function toTop(){
		document.documentElement.scrollTop = document.body.scrollTop =0;
	}
	
	var pathInfo=document.getElementsByClassName("pathInfo")[0];
	var initLocateCode=document.getElementsByClassName("secondtitle")[0].attributes['code'].value;
	/*初始化*/
		getURL(initLocateCode,"","",false);
	/*初始化结束*/
	
	function getURL(code,firstname,secondname,flag){
		tonexttemp=catetonext;
		topretemp=catetopre;
		curpage=1;
   		get('<c:url value="/BrowerBookServlet"/>?code='+code+"&firstpath="+encodeURIComponent(firstname)+"&secondpath="+encodeURIComponent(secondname),locadBooks);
    	if(flag)
    		pathInfo.innerHTML="&nbsp;当前位置:"+firstname+">>"+secondname+"&gt;&gt;第"+curpage+"页";
    	else{
    		pathInfo.innerHTML="&nbsp;当前位置:"+"商品推荐"+"&gt;&gt;第"+curpage+"页";
    	}
    }
    function tonext(){
    	curpage++;
    	get(tonexttemp,locadBooks);
    }
    
    function topre(){
    	curpage--;
    	get(topretemp,locadBooks);
    }
    
    var toserach=document.getElementById("toserach");
    toserach.onclick=serach;
    
    function serach(){
    	curpage=1;
    	key=document.getElementsByClassName("serachtext")[0].value;
    	serachtonext="<c:url value="/BrowerBookServlet"></c:url>?tonext=1&bnk="+key;
    	serachtopre="<c:url value="/BrowerBookServlet"></c:url>?topre=1&bnk="+key;
    	tonexttemp=serachtonext;
    	topretemp=serachtopre;
    	get("<c:url value="/BrowerBookServlet"></c:url>?bnk="+key+"&toserach=1",locadBooks);
    }
</script>
</body>
</html>

