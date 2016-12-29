<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>shopping</title>
    <link rel="stylesheet" href="style/shopping.css">
    <link rel="stylesheet" href="style/buttons.css">
    <link rel="stylesheet" href="style/re_g.css">
    <script type="text/javascript" src='<c:url value="/ajax-libs/ajax-lib.js"></c:url>'></script>
</head>
<body>
<div class="container">
    <div class="top">
        <div class="top_logo">
            <a href="../index.jsp"><img src="images/logo.jpg">中华商城</a>
            <a href="<c:url value="/SeeShoppingCart"></c:url>?username=${user.username }">购物车</a>
        </div>
        <div class="serach">
            <input type="text" name="serachtext">
            <button class="s_b1">搜索</button>
        </div>
        <div class="logstate">
            <button class="s_b1" id="loginorregist">登录/注册</button>
        </div>
    </div>
    
    <div class="content">
        <h3 class="content_title">全部商品</h3>
        <div class="detail">
            <span class="gooddetil">商品</span>
            <span class="price">单价</span>
            <span class="count">数量</span>
            <span class="itemallprice">小计</span>
            <span>操作</span>
        </div>
        <div class="goodstates">
            <div class="statetools">
                <div class="gouxuanall"><input type="checkbox" onchange="selectAll(this,option02)"  id="gou"><label for="gou">全选</label></div>
                <div><a onclick="delAll()">删除选中的商品</a></div>
                <div class="statetogo">
                    <span>已选择<span class="money" id="select">0</span>件商品</span>
                    <span>总价<span id="jieAllPrice" class="money"></span>元</span>
                    <a href="#" class="jiesuan">去结算</a>
                </div>
            </div>
      </div>
        <div id="goodss"></div>
<script type="text/javascript" >
      			var deletoption={
				"type":"json",
				"url":"<c:url value="/DeleteGoodItem"></c:url>?",
				"params":"null",
				"callback":function(){ajax(option)}
			};
			
			var option02={
				"method":"GET",
				"type":"json",
				"url":"<c:url value="/ShoppingItemSelect"></c:url>?username=${user.username}",
				"params":"null",
				"callback":function (data){ajax(option)}
			};
			var addorsuboption={
				"url":"<c:url value="/ShoppingCartServlet"></c:url>?username=${user.username }&re=re"+new Date().getTime(),
				"type":"json",
				"params":"null",
				"callback":function(data){ajax(option)}
			}
			
			var option={
				"method":"POST",
				"url":"<c:url value="/SeeShoppingCart"></c:url>?uuid="+new Date().getTime(),
				"params":"username=${sessionScope.user.username}",
				"type":"json",
				"callback":getcart
			};
			function getcart(data){
				var goodss=document.getElementById("goodss");
				if(!data){
					goodss.innerHTML="<div class=\"tips\">"+
			        	    "购物车空空如也，<a href=\"../index.jsp\">现在去购物</a>"+
			       			"</div>";
			       checkAllToFluch();
			       return ;
				}
				var len=data.length;
						
			goodss.innerHTML="";
			for(var i=0;i<len;i++){
			var checkvalue="";
			if(data[i].select=='1'){
				checkvalue="checked";
			}else{
				checkvalue="";
			}
			
			var addparam="add(addorsuboption,'bookid="+data[i].bookid+"&bookname="+data[i].bookname+"&price="+data[i].price+"&o=a',this)";
			var subparam="sub(addorsuboption,'bookid="+data[i].bookid+"&bookname="+data[i].bookname+"&price="+data[i].price+"&o=s',this,"+data[i].count+")";
			var deleteparm="del(deletoption,'username=${user.username}&bookid="+data[i].bookid+"')";
			var url={"addparam":addparam,"subparam":subparam};
	
			var item="<input name=\"selectitem\" class=\"checkitem\" "+checkvalue+" onchange=\"targle(this,option02,option)\" type=\"checkbox\">"+
			"<img src=<c:url value="/SeeShoppingCart"></c:url>?order=cover&index="+i+"&uuid="+new Date().getTime()+">"+
			"<div class=\"goodinfo\">"+data[i].bookname+"</div>"+
			"<input type=\"text\" name=\"bookid\" value=\""+data[i].bookid+"\"/ hidden>"+
			"<div class=\"pricevalue\">"+data[i].price+"</div>"+
		            "<div class=\"goodcount\">"+
		                "<div class=\"control\">"+
		                    "<a class=\"control_left\" onclick=\""+url.subparam+"\">-</a>"+
		                    "<input type=\"text\" disabled class=\"icount\" value="+data[i].count+">"+
		                    "<a class=\"control_right\" onclick=\""+url.addparam+"\" >+</a>"+
		                "</div>"+
		            "</div>"+
		            "<div name=\"itemallprice\" class=\"curallprice\" value=\""+data[i].allprice+"\">"+data[i].allprice+"</div>"+
		            "<button class=\"s_b2\" onclick="+deleteparm+">删除</button>";
		     var div=document.createElement("div");
		     div.innerHTML=item;
		     div.setAttribute("class", "gooditem");
		     goodss.appendChild(div);
			}
			checkAllToFluch();
	}

	ajax(option);
</script>
        <!-- 商品推荐开始 -->
        <div class="re_gc">
            <h3>商品推荐</h3><hr>
            <div class="re_gi">

            </div>
            <div class="re_gi">

            </div>
            <div class="re_gi">

            </div>
            <div class="re_gi">

            </div>
            <div class="re_gi">

            </div>
            <div class="re_gi">

            </div>
            <div class="re_gi">

            </div>
            <div class="re_gi">

            </div>
        </div>
        <!-- 商品推荐结束 -->
    </div>
</div>
<script type="text/javascript" src="js/shopping.js"></script>
</body>
</html>
