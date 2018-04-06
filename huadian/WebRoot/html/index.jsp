<%@page import="service.GoodsWantService"%>
<%@page import="model.PageBean"%>
<%@page import="service.GoodsClassService"%>
<%@page import="model.GoodsClass"%>
<%@page import="java.io.File"%>
<%@page import="model.Goods"%>
<%@page import="service.GoodsService"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
	Integer buyOrSell=0;//0为首页 1代表是查看想要买的物品，2代表查看想要卖的物品
	Integer goodsClass=0;//0全部，1耳机，2台灯，3小物品，4书，5电子设备，6各类笔记，7卡片	
							/* 页数控制 */
	GoodsService goodsService=new GoodsService();
	GoodsWantService goodsWantService=new GoodsWantService();
	request.setCharacterEncoding("UTF-8");
	ArrayList<Goods> goodsList =new ArrayList<Goods>();
	Integer pageNum=1;
	if(request.getParameter("pageNum")!=null){
		pageNum=Integer.parseInt(request.getParameter("pageNum"));
 	}
 	if(request.getParameter("buyOrSell")!=null){
 		System.out.println(request.getParameter("buyOrSell"));
 		buyOrSell=Integer.parseInt(request.getParameter("buyOrSell"));
 	}
 	if(request.getParameter("goodsClass")!=null){
 		System.out.println(request.getParameter("goodsClass"));
 		goodsClass=Integer.parseInt(request.getParameter("goodsClass"));
 	}
 	
 	PageBean<Goods> pageBean=new PageBean<Goods>();
	pageBean.setPageNum(pageNum);
	pageBean.setPageSize(6); 
	Goods goodsTest=new Goods();
	goodsTest.setClassId(goodsClass);
	String goodsName="";
	if(request.getParameter("search")!=null&&request.getParameter("search").length()>0){
		goodsName=request.getParameter("search");
		goodsTest.setGoodsName(goodsName);
		pageBean=goodsService.queryGoodsByCondition(1, pageBean, goodsTest, "goodsName");
	}
	else{
		if((buyOrSell==0||buyOrSell==1)&&goodsClass==0){
			pageBean=goodsService.queryGoodsByCondition(1,pageBean, goodsTest, "all");
		}
		else if((buyOrSell==0||buyOrSell==1)&&goodsClass!=0)
		{
			pageBean=goodsService.queryGoodsByCondition(1,pageBean, goodsTest, "class");
		}
		else if((buyOrSell==2)&&goodsClass==0)
		{
			pageBean=goodsWantService.queryGoodsByCondition(1,pageBean, goodsTest, "all");
		}
		else if((buyOrSell==2)&&goodsClass!=0)
		{
			pageBean=goodsWantService.queryGoodsByCondition(1,pageBean, goodsTest, "class");
		}
	}
	goodsList=pageBean.getList();
	
	GoodsClassService goodsClassService=new GoodsClassService();
	String path="/huadian/upload/";
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta http-equiv="Content-Type" content="text/html;charset=gb2312">
    <title>华电二手交易平台</title>
    <link rel="stylesheet" href="/huadian/html/css/index.css">
    <style>
        *{
            padding: 0;
            margin: 0;
        }
    </style>
    <script src="/huadian/html/js/jquery-1.11.3.min.js"></script>
    <script src="/huadian/html/js/index.js" charset="gb2312" type="text/javascript"></script>
    <script>

    </script>
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
                        <!-- 导航栏 -->
    <nav id="nav">
        <div class="nav_in">
            <div class="pic">
                <a href="#" title="华电二手交易平台"></a>
            </div>
            <div class="nav_box" xuhao="<%=buyOrSell %>">
                <ul class="nav_ul">
                    <li class="nav_li">
                        <a href="/huadian/html/index.jsp?buyOrSell=0">首页</a>
                    </li>
                    <li class="nav_li"><a href="/huadian/html/index.jsp?buyOrSell=1">购买物品</a></li>
                    <li class="nav_li"><a href="/huadian/html/index.jsp?buyOrSell=2">求购商品</a></li>
                </ul>
            </div>
            <div class="nav_search">
                <form action="/huadian/html/index.jsp" method="get" class="nav_form">
                    <input type="search" name="search" id="search" placeholder="请输入要查询的商品名称" required="required"><input type=submit name="btn" id="btn" value="">
                </form>
            </div>
        </div>
    </nav>
                        <!-- 中部大区域 -->
    <div id="center">
        <div class="register">
            <div class="register_header">
                <h2>Sign Up</h2>
                <div><span>×</span></div>
            </div>
            <form action="" method="post">
                <input type="text" name="register_name" id="register_name" placeholder="Username">
                <br>
                <input type="password" name="register_password" id="register_password1" placeholder="Password">
                <br>
                <input type="password" name="register_password" id="register_password2" placeholder="Confirm Password">
                <br>
                <input type="button" value="Sign Up" id="register_submit">
                <label for="radio" ><input type="radio" name="radio" id="radio"><span>I accept the terms of use</span></label>
            </form>
            <div class="register_img1"></div>
            <div class="register_img2"></div>
            <div class="register_img3"></div>            
        </div>
        <%if(session.getAttribute("loginUsername")==null){ %>
        <div class="login">
            <div class="login_header">
                <p>
                    <span>Secure Login</span><i> or get a <ins><a href="javascript:void(0);">free account</a></ins></i>
                </p>
                <div><span>×</span></div>
            </div>
            <form action="#" method="post">
                <input type="text" name="login_name" id="login_name" placeholder="Username">
                <br>
                <input type="password" name="login_password" id="login_password" placeholder="Password">
                <br>
                <input type="button" value="Let me in!" id="login_submit">
                <label for="login_remember"><input type="checkbox" name="login_remember" id="login_remember"><i>Remember me</i></label>
            </form>
            <p class="forgot"><a href="javascipt:void(0);"><ins>Forgot your password?</ins></a></p>
            <div class="login_img1"></div>
            <div class="login_img2"></div>
        </div>
        <%} %>
        <div class="lunbotu">
            <div class="lbt_pic">
                <div class="pic">
                   <a href="/huadian/html/img/pic_1.jpg"><img src="/huadian/html/img/pic_1.jpg" alt="pic_1"></a>
                   <a href="/huadian/html/img/pic_2.jpg"><img src="/huadian/html/img/pic_2.jpg" alt="pic_2"></a> 
                   <a href="/huadian/html/img/pic_3.jpg"><img src="/huadian/html/img/pic_3.jpg" alt="pic_3"></a>                    
                </div>
                <ul class="lbt_ul">
                    <li xuhao="0" class="lbt_li" style="background:#fc5300"></li>
                    <li xuhao="1" class="lbt_li"></li>  
                    <li xuhao="2" class="lbt_li"></li>                  
                </ul>
            </div>
        </div>
    </div>
    <div id="goods">
        <div class="goods_center">
                <aside goodsClass="<%=goodsClass-1%>">
                        <div class="life">
                            <h4>
                                生活用品
                            </h4>
                            <p>
                                <span><a href="/huadian/html/index.jsp?buyOrSell=<%=buyOrSell %>&goodsClass=1" xh="1">耳机</a></span>
                                <span><a href="/huadian/html/index.jsp?buyOrSell=<%=buyOrSell %>&goodsClass=2" xh="2">台灯</a></span>
                                <span><a href="/huadian/html/index.jsp?buyOrSell=<%=buyOrSell %>&goodsClass=3" xh="3">小物品</a></span>
                            </p>
                        </div>
                        <div class="study">
                            <h4>
                                学习用品
                            </h4>
                            <p>
                                <span><a href="/huadian/html/index.jsp?buyOrSell=<%=buyOrSell %>&goodsClass=4" xh="4">书</a></span>
                                <span><a href="/huadian/html/index.jsp?buyOrSell=<%=buyOrSell %>&goodsClass=5" xh="5">电子设备</a></span>
                                <span><a href="/huadian/html/index.jsp?buyOrSell=<%=buyOrSell %>&goodsClass=6" xh="6">各类笔记</a></span>
                            </p>
                        </div>
                        <div class="other">
                            <h4>
                                其他
                            </h4>
                            <p>
                                <span><a href="/huadian/html/index.jsp?buyOrSell=<%=buyOrSell %>&goodsClass=7" xh="7">各类卡片</a></span>
                            </p>
                        </div>
                    </aside>
            <div class="good_buy">
            <div class="good_buy_show">
            <%if(goodsList!=null&&goodsList.size()>0){
            for(Goods goods:goodsList){
            %>
            
            <div class="good">
            	<%if(buyOrSell==0||buyOrSell==1){ %>
                <a href="/huadian/html/goodsShow.jsp?fileCount=<%=fileCount(goods)%>&goodsId=<%=goods.getGoodsId()%>&reserve=1"><img src=<%=path+goods.getPicture()+File.separator+"thumbnail.1.jpg"%> alt="图片无法显示"></a>
                <%}else if(buyOrSell==2){ %>
                <a href="/huadian/html/wantGoodsShow.jsp?fileCount=<%=fileCount(goods)%>&goodsId=<%=goods.getGoodsId()%>&reserve=1"><img src=<%=path+goods.getPicture()+File.separator+"thumbnail.1.jpg"%> alt="图片无法显示"></a>
                <%} %>
                <p class="good_name"><span><%=goods.getGoodsName()%></span></p>
                <div class="good_des">
                    <span class="good_price">￥ <%=goods.getPrice() %></span>
                    <br/>
                    <div class="connect">
                            联系方式：<span><%=goods.getSellerContact() %></span>  
                    </div>
                </div>
            </div>
            <%}}else{ %>
            <div class="noGoods">
            	<img alt="no goods" src="/huadian/html/img/no-goods.png">
            </div>
            <%} %>
            <div class="pageDiv">
            	<%if(pageBean.getTotalPage()>=1){
            	if(pageBean.getPageNum()>1){ %>
            		<a href="/huadian/html/index.jsp?pageNum=1&buyOrSell=<%=buyOrSell %>&goodsClass=<%=goodsClass %>&search=<%=goodsName %>" class="page">首页</a>
            		<a href="/huadian/html/index.jsp?pageNum=<%=pageNum-1 %>&buyOrSell=<%=buyOrSell %>&goodsClass=<%=goodsClass %>&search=<%=goodsName %>" class="page">前一页</a>
            	<%}
            	 for(int i=pageBean.getStart();i<pageBean.getPageNum();i++){%>
            		<a class="page" href="/huadian/html/index.jsp?pageNum=<%=i%>&buyOrSell=<%=buyOrSell %>&goodsClass=<%=goodsClass %>&search=<%=goodsName %>">第<%=i%>页</a>
            		<%} %>
            		<a class="page" id="pageNum" href="javascript:void(0);">第<%=pageBean.getPageNum() %>页</a>
            	<%for(int i=pageBean.getPageNum()+1;i<=pageBean.getEnd();i++){ %>
            		<a class="page" href="/huadian/html/index.jsp?pageNum=<%=i%>&buyOrSell=<%=buyOrSell %>&goodsClass=<%=goodsClass %>&search=<%=goodsName %>">第<%=i%>页</a>
            	<%}
            	if(pageBean.getPageNum()<pageBean.getTotalPage()){ %>
            		<a class="page" href="/huadian/html/index.jsp?pageNum=<%=pageNum+1%>&buyOrSell=<%=buyOrSell %>&goodsClass=<%=goodsClass %>&search=<%=goodsName %>">后一页</a>
            		<a class="page" href="/huadian/html/index.jsp?pageNum=<%=pageBean.getTotalPage()%>&buyOrSell=<%=buyOrSell %>&goodsClass=<%=goodsClass %>&search=<%=goodsName %>">尾页</a>
            	<%}} %>
            </div>
        </div>
        
    <div class="good_want">
        
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
    <%
    String error="";
	if(request.getParameter("reserve")!=null&&Integer.parseInt(request.getParameter("reserve"))==0){
		error="请先登录或注册";
	}else if(request.getParameter("reserve")!=null&&Integer.parseInt(request.getParameter("reserve"))==1){
		error="恭喜您，预定成功，请与卖主联系";}
	%>
		<input id="hint" value=<%=error%>>
</body>
</html>
