<%@page import="service.GoodsWantService"%>
<%@page import="java.io.File"%>
<%@page import="model.User"%>
<%@page import="model.UserService"%>
<%@page import="model.Goods"%>
<%@page import="model.PageBean"%>
<%@page import="service.GoodsService"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String username="";
	if(session!=null&&session.getAttribute("loginUsername")!=null){
		username = session.getAttribute("loginUsername").toString();
	}
	else{
		response.sendRedirect("/huadian/html/index.jsp?reserve=0");
		return;
	}
	System.out.println(username);
	Integer view=1;//1：已购买物品，2：已出售物品，3：想要的物品，4：被预约的物品，5：已预约的物品
	Integer pageNum=1;//页数的控制
	String path="/huadian/upload/";//路径
	if(request.getParameter("view")!=null){
		view=Integer.parseInt(request.getParameter("view"));
	}
	if(request.getParameter("pageNum")!=null){
		pageNum=Integer.parseInt(request.getParameter("pageNum"));
	}
	GoodsService goodsService=new GoodsService();
	GoodsWantService goodsWantService=new GoodsWantService();
	UserService userService=new UserService();
	User user=new User();
	user.setNameString(username);
	Integer userId=userService.queryUserId(user).getId();
	//System.out.println(userId);
	PageBean<Goods> pageBean=new PageBean<Goods>();
	pageBean.setPageNum(pageNum);
	pageBean.setPageSize(6); 
	Goods goodsTest=new Goods();
	goodsTest.setBuyerId(userId);
	goodsTest.setSellerId(userId);
	ArrayList<Goods> arrayList=new ArrayList<Goods>();
	switch(view){
		case 1:
			pageBean=goodsService.queryGoodsByCondition(1, pageBean, goodsTest, "sellerId");
			break;
		case 2:
			pageBean=goodsService.queryGoodsByCondition(3, pageBean, goodsTest, "buyerId");
			break;
		case 3:
			pageBean=goodsService.queryGoodsByCondition(3, pageBean, goodsTest, "sellerId");
			break;
		case 4:
			pageBean=goodsWantService.queryGoodsByCondition(1, pageBean, goodsTest, "sellerId");
			break;
		case 5:
			pageBean=goodsService.queryGoodsByCondition(2, pageBean, goodsTest, "sellerId");
			break;
		case 6:
			pageBean=goodsService.queryGoodsByCondition(2, pageBean, goodsTest, "buyerId");
			break;
		case 7:
			pageBean=goodsService.queryGoodsByCondition(2, pageBean, goodsTest, "sellerId");
			break;
		case 8:
			pageBean=goodsWantService.queryGoodsByCondition(2, pageBean, goodsTest, "sellerId");
			break;
		case 9:
			pageBean=goodsWantService.queryGoodsByCondition(2, pageBean, goodsTest, "buyerId");
			break;
	}
	arrayList=pageBean.getList();
%>
<%!
Integer fileCount(Goods goods){
	String path=getServletContext().getRealPath("./") + File.separator+"upload"+File.separator;
	int fileCount = 0;
	File d = new File(path+goods.getPicture());
 	File list[] = d.listFiles();
	for(int i = 0; i < list.length; i++){
	    if(list[i].isFile()&&list[i].getName().indexOf("thumbnail-")!=-1){
	        fileCount++;
	    }
}
	return fileCount;
} 
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
			<a href="/huadian/html/wantGoods.jsp" class="btn_want">帮我买物品</a>
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
		<div class="aside" xuhao=<%=view-1 %>>
			<ul class="aside_ul">
				<li class="aside_li" style="border-top: 1px solid #ebebeb"><a
					href="/huadian/html/user.jsp?view=1">在出售的商品</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=2">已购买的物品</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=3">已出售的物品</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=4">想要的物品</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=5">被预约的物品</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=6">已预约的物品</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=7">确认交易成功</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=8">被预定求购的物品</a></li>
				<li class="aside_li"><a href="/huadian/html/user.jsp?view=9">预定求购的物品</a></li>
				<li class="aside_li_bottom"><a href="/huadian/servlet/Exit">退出登录</a>
				</li>
			</ul>
		</div>
		</aside>
		<div class="center_right">
			<%if(arrayList!=null&&arrayList.size()>0){
            for(Goods goods:arrayList){
            %>
            
            <div class="good">
            	<%if(view==1||view==2||view==3||view==5||view==6||view==7){ %>
                <a href="/huadian/html/goodsShow.jsp?fileCount=<%=fileCount(goods)%>&goodsId=<%=goods.getGoodsId()%>"><img src=<%=path+goods.getPicture()+File.separator+"thumbnail.1.jpg"%> alt="图片无法显示"></a>
                <%}else if(view==4||view==8||view==9){ %>
                <a href="/huadian/html/wantGoodsShow.jsp?fileCount=<%=fileCount(goods)%>&goodsId=<%=goods.getGoodsId()%>"><img src=<%=path+goods.getPicture()+File.separator+"thumbnail.1.jpg"%> alt="图片无法显示"></a>
                <%} %>
                <p class="good_name"><span><%=goods.getGoodsName()%></span></p>
                <div class="good_des">
                    <span class="good_price">￥ <%=goods.getPrice() %></span>
                    <div class="connect">
                    <%if(view==3||view==5||view==7||view==8){ %>
						联系方式：<span><%=goods.getBuyerContact() %></span> 
                    <%}else{ %> 
						联系方式：<span><%=goods.getSellerContact() %></span> 
                    <%} %>
                    </div>
                </div>
                <%if(view==1||view==5){ %>
                <div class="cancel" mark="sell"><a href="/huadian/servlet/CancelGoods?goodsId=<%=goods.getGoodsId()%>&mark=1">取消出售</a></div>
            	<% }%>
            	<%if(view==4||view==8){ %>
                <div class="cancel" mark="want"><a href="/huadian/servlet/CancelGoods?goodsId=<%=goods.getGoodsId()%>&mark=4">取消求购</a></div>
            	<%}%>
            	<%if(view==9){ %>
                <div class="cancel" mark="reserve_want"><a href="/huadian/servlet/CancelGoods?goodsId=<%=goods.getGoodsId()%>&mark=5">取消预约</a></div>
            	<%}%>
            	<%if(view==6){ %>
                <div class="cancel" mark="reserve"><a href="/huadian/servlet/CancelGoods?goodsId=<%=goods.getGoodsId()%>&mark=2">取消预约</a></div>
            	<%}%>
            	<%if(view==7){ %>
                <div class="cancel" mark="confirm"><a href="/huadian/servlet/CancelGoods?goodsId=<%=goods.getGoodsId()%>&mark=3">确认交易成功</a></div>
            	<%}%>
            </div>
            <%}} %>
            <div class="pageDiv">
            	<%if(pageBean.getTotalPage()>=1){
            	if(pageBean.getPageNum()>1){ %>
            		<a href="/huadian/html/user.jsp?pageNum=1&view=<%=view%>" class="page">首页</a>
            		<a href="/huadian/html/user.jsp?pageNum=<%=pageNum-1 %>&view=<%=view%>" class="page">前一页</a>
            	<%}
            	 for(int i=pageBean.getStart();i<pageBean.getPageNum();i++){%>
            		<a class="page" href="/huadian/html/user.jsp?pageNum=<%=i%>&view=<%=view%>">第<%=i%>页</a>
            		<%} %>
            		<a class="page" id="pageNum" href="javascript:void(0);">第<%=pageBean.getPageNum() %>页</a>
            	<%for(int i=pageBean.getPageNum()+1;i<=pageBean.getEnd();i++){ %>
            		<a class="page" href="/huadian/html/user.jsp?pageNum=<%=i%>&view=<%=view%>">第<%=i%>页</a>
            	<%}
            	if(pageBean.getPageNum()<pageBean.getTotalPage()){ %>
            		<a class="page" href="/huadian/html/user.jsp?pageNum=<%=pageNum+1%>&view=<%=view%>">后一页</a>
            		<a class="page" href="/huadian/html/user.jsp?pageNum=<%=pageBean.getTotalPage()%>&view=<%=view%>">尾页</a>
            	<%}} %>
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
		<p>
			<span>@2017-2017 Huadian.com 版权所有</span>
		</p>
	</div>
	</footer>
<%
    String error="";
	if(request.getParameter("cancel")!=null&&Integer.parseInt(request.getParameter("cancel"))==1){
		error="取消出售物品成功";
	}
	else if(request.getParameter("cancel")!=null&&Integer.parseInt(request.getParameter("cancel"))==2){
		error="取消预约物品成功";
	}
	else if(request.getParameter("cancel")!=null&&Integer.parseInt(request.getParameter("cancel"))==3){
		error="确认交易成功";
	}
	else if(request.getParameter("cancel")!=null&&Integer.parseInt(request.getParameter("cancel"))==4){
		error="取消求购物品成功";
	}
	else if(request.getParameter("cancel")!=null&&Integer.parseInt(request.getParameter("cancel"))==5){
		error="取消预约求购物品成功";
	}
	%>
		<input id="hint" value=<%=error%>>
</body>
</html>
