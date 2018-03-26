<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("utf-8");
	String username = session.getAttribute("loginUsername").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户中心</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="/huadian/html/css/user.css">
<script type="text/javascript" src="/huadian/html/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/huadian/html/js/user.js" charset="gb2312"></script>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}
</style>
</head>

<body>
	<header id="header">
	<div class="header_in">
		<ul class="header_left">
			<li class="header_li"><a href="#">华电[切换大学]</a>
			</li>
			<li class="header_li" style="padding: 0 20px 0 19px"><span
				style="color:#a3a3a3">华电统一客服热线</span> <span style="color:#fc5300">18730272603</span>
			</li>
		</ul>
		<ul class="header_right">
			<li class="header_li"><span><img alt="img4"
					src="/huadian/html/img/img4.gif">
			</span>
			</li>
			<li class="header_li"><span style="color: #fc5300"><%=username%></span>
			</li>
			<li class="header_li" id="right_li"><a href="javascipt:void(0);">网站导航</a>
				<ul class="inner_ul">
					<li class="inner_li"><a>购买物品指南</a></li>
					<li class="inner_li"><a>出售物品指南</a></li>
				</ul>
			</li>
		</ul>
	</div>
	</header>
	<nav>
	<div class="nav_in">
		<a href="javascript:void(0);"></a>
		<div class="nav_right">
			<a href="/huadian/html/sellGoods2.jsp" class="btn_sell">我有物品卖</a>
			<a href="#" class="btn_want">帮我买物品</a>
		</div>
	</div>
	</nav>
	<div id="center">
		<aside>
		<div class="head">
			<img src="/huadian/html/img/img6.png" alt="head">
			<p class="username">
				<span><%=username %></span>
			</p>
		</div>
		<div class="aside">
			<ul class="aside_ul">
				<li class="aside_li" style="border-top: 1px solid #ebebeb;background:#fff"><a
					href="javascript:void(0);"style="color:#fc5300">已购买物品</a></li>
				<li class="aside_li"><a href="javascript:void(0);">已出售物品</a></li>
				<li class="aside_li"><a href="javascript:void(0);">想要的物品</a></li>
				<li class="aside_li"><a href="javascript:void(0);">被预约的物品</a></li>
				<li class="aside_li"><a href="javascript:void(0);">已预约的物品</a></li>
				<li class="aside_li_bottom"><a href="/huadian/servlet/Exit">退出登录</a>
				</li>
			</ul>
		</div>
		</aside>
		<div class="center_right"></div>
	</div>
	<footer>
	<div class="bound">
		<div class="left"></div>
		<span>END</span>
		<div class="right"></div>
	</div>
	<div class="text">
		<address>河北省保定市华北电力大学(二校区)</address>
		<p>
			<span>@2017-2017 Huadian.com 版权所有</span>
		</p>
	</div>
	</footer>
</body>
</html>
