<%@page import="service.GoodsWantService"%>
<%@page import="java.io.File"%>
<%@page import="service.GoodsClassService"%>
<%@page import="model.Goods"%>
<%@page import="service.GoodsService"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	Integer fileCount=Integer.parseInt(request.getParameter("fileCount").toString());
	Integer goodsId=Integer.parseInt(request.getParameter("goodsId").toString());
  	GoodsWantService goodsService=new GoodsWantService();
 	Goods goods=goodsService.queryGoodsById(goodsId);
 	GoodsClassService goodsClassService=new GoodsClassService();
 	String goodsClassName=goodsClassService.queryGoodsClass(goods).getClassName();
 	String path="/huadian/upload/";
 %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>商品展示</title>
	<link rel="stylesheet" type="text/css" href="/huadian/html/css/goodsShow.css">
	<script type="text/javascript" src="/huadian/html/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="/huadian/html/js/goodsShow.js" charset="gb2312"></script>
</head>
<body>
	                         <!-- 头部 -->
    <header id="header">
        <div class="header_in">
           <ul class="header_left">
              <li class="header_li">
                  <a href="#">华电[切换大学]</a>
              </li>
            <li class="header_li" style="padding: 0 20px 0 19px">
                 <span style="color:#a3a3a3">华电统一客服热线</span>              
                 <span style="color:#fc5300">18730272603</span> 
              </li>    
            </ul>
            <ul class="header_right">
            <% if(session.getAttribute("loginUsername")!=null){ %>
            	<li class="header_li"><img  src='/huadian/html/img/img4.gif'/></li>
                <li class="header_li"><a href='user.jsp?' style='font-size:12px'><%=session.getAttribute("loginUsername").toString() %></a></li>
               <%}else{ %>
                <li class="header_li"><a href="#" style="border-left:1px solid #d6d6d6">登录</a></li>
                <li class="header_li"><a href="#">注册</a></li>
                <%} %>
                <li class="header_li" id="right_li"><a href="javascipt:void(0);">网站导航</a>
                    <ul class="inner_ul">
                        <li class="inner_li"><a>购买物品指南</a></li>
                        <li class="inner_li"><a>出售物品指南</a></li>
                    </ul>
                </li>
            </ul> 
        </div>
    </header>
	<div id="main">
		<div class="title">
			<h2><%=goods.getGoodsName() %></h2>
		</div>
		<div class="center">
			<div class="lunbotu" fileCount=<%=fileCount %>>
			<%for(int i=0;i<fileCount;i++){ %>
				<a href=<%=path+goods.getPicture()+File.separator+"thumbnail-"+(i+1)+".jpg" %> class="pic" style="display: block;"><img src=<%=path+goods.getPicture()+File.separator+"thumbnail-"+(i+1)+".jpg"%>></a>
				<%} %>
	                <ul class="lbt_ul">
	                <% for(int i=0;i<fileCount;i++){ %>
						<li xuhao=<%=i %> class="lbt_li"></li>
					<%} %>
	                </ul>
	                <a href="javascript:void(0)" class="left btn"><img src="/huadian/html/img/左箭头.png"></a>
	                <a href="javascript:void(0)" class="right btn"><img src="/huadian/html/img/右箭头.png"></a>
			</div>
			<div class="center_right">
				<p class="price">价格：<span><%=goods.getPrice() %></span>元</p>
				<p class="description">
					<span>商品描述：</span>
					<br/>
					<span class="content"><%=goods.getDescription() %></span>
				</p>
				<p class="goodsClass">商品类型：
					<span><%=goodsClassName %></span>
				</p>
				<p class="contact">
					卖主联系方式：<span><%=goods.getSellerContact() %></span>
				</p>
					<%if(request.getParameter("reserve")!=null){%>
						<a class="reserve">立即预定</a>
					<%} %>
			</div>
			<div class="buyerInfo">
				<form action="/huadian/servlet/SellWantGoods" method="post">
					<input type="text"	name="contact" placeholder="您的联系方式" class="contact" required="required">
					<input type="hidden" name="goodsId" value=<%=goodsId%>>
					<br/>
					<input type="submit" name="reserve" value="预定" class="reserve">
				</form>
				<a href="javascript:void(0);" class="close"><img src="/huadian/html/img/close.png"></a>
			</div>
		</div>
	</div>
	<footer>
        <div class="bound">
            <div class="left"></div>
            <span>END</span>
            <div class="right"></div>
        </div>
        <div class="text">
            <address>河北省保定市华北电力大学(二校区)</address>
            <p><span>@2017-2017 Huadian.com 版权所有</span></p>
        </div>
    </footer>
</body>
</html>
