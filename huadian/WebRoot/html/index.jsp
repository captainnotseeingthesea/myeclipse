<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
            <div class="nav_box">
                <ul class="nav_ul">
                    <li class="nav_li">
                        <a href="javascript:void(0);">首页</a>
                    </li>
                    <li class="nav_li"><a href="javascript:void(0);">购买物品</a></li>
                    <li class="nav_li"><a href="javascript:void(0);">求购商品</a></li>
                </ul>
            </div>
            <div class="nav_search">
                <form action="#" method="get" class="nav_form">
                    <input type="search" name="search" id="search" placeholder="请输入要查询的商品名称"><input type="button" name="btn" id="btn">
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
        <div class="lunbotu">
            <div class="lbt_pic">
                <div class="pic">
                   <a href="#"><img src="/huadian/html/img/pic_1.jpg" alt="pic_1"></a>
                   <a href="#"><img src="/huadian/html/img/pic_2.jpg" alt="pic_2"></a> 
                   <a href="#"><img src="/huadian/html/img/pic_3.jpg" alt="pic_3"></a>                    
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
        <div class="center">
                <aside>
                        <div class="life">
                            <h4>
                                生活用品
                            </h4>
                            <p>
                                <span><a href="javascript:void(0)" xh="1">耳机</a></span>
                                <span><a href="javascript:void(0)" xh="2">台灯</a></span>
                                <span><a href="javascript:void(0)" xh="3">小物品</a></span>
                            </p>
                        </div>
                        <div class="study">
                            <h4>
                                学习用品
                            </h4>
                            <p>
                                <span><a href="javascript:void(0)" xh="4">书</a></span>
                                <span><a href="javascript:void(0)" xh="5">电子设备</a></span>
                                <span><a href="javascript:void(0)" xh="6">各类笔记</a></span>
                            </p>
                        </div>
                        <div class="other">
                            <h4>
                                其他
                            </h4>
                            <p>
                                <span><a href="javascript:void(0)" xh="7">各类卡片</a></span>
                            </p>
                        </div>
                    </aside>
            <div class="good_buy">
            <div class="good_buy_show">
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img1.jpg" alt=""></a>
                <p class="good_name"><span>耳机</span></p>
                <div class="good_des">
                    <span class="good_price">￥ 268.00</span>
                    <div id="fal" class="condition">
                        <span>已预约</span>
                    </div>
                    <div class="connect">
                            联系方式：<span>phone:18730272603</span>  
                    </div>
                </div>
            </div>
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img2.jpg" alt=""></a>
                <p class="good_name"><span>大神笔记</span></p>
                <div class="good_des">
                     <span class="good_price">￥ 50.00</span>  
                     <div id="true" class="condition">
                         <span>预约</span>
                    </div> 
                    <div class="connect">
                            联系方式：<span>qq:57436746</span>  
                    </div>
                </div>
            </div>
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img3.jpg" alt=""></a>
                <p class="good_name"><span>电风扇</span></p>
                <div class="good_des">
                       <span class="good_price">价格面议</span> 
                       <div id="true" class="condition">
                           <span>预约</span>
                       </div>
                       <div class="connect">
                            联系方式：<span>phone:18730272603</span>  
                       </div>
                </div>
            </div>
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img1.jpg" alt=""></a>
                <p class="good_name"><span>挂式台灯</span></p>
                <div class="good_des">
                        <span class="good_price">￥ 20.00</span>
                        <div id="true" class="condition">
                            <span>预约</span>
                        </div>
                        <div class="connect">
                                联系方式：<span>qq:795939237</span>    
                        </div>
                </div>
            </div>
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img2.jpg" alt=""></a>
                <p class="good_name"><span>电脑桌</span></p>
                <div class="good_des">
                        <span class="good_price">价格面议 可小刀</span>
                        <div id="true" class="condition">
                            <span>预约</span>
                        </div>
                        <div class="connect">
                                联系方式：<span>phone:18833210750</span>     
                        </div>
                </div>
            </div>
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img3.jpg" alt=""></a>
                <p class="good_name"><span>收音机</span></p>
                <div class="good_des">
                        <span class="good_price">￥ 10.00</span>
                        <div id="fal" class="condition">
                            <span>已预约</span>
                        </div>
                        <div class="connect">
                                联系方式：<span>phone:18833210750</span>      
                        </div>
                </div>
            </div>
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img1.jpg" alt=""></a>
                <p class="good_name"><span>考研书</span></p>
                <div class="good_des">
                       <span class="good_price">价格面议</span> 
                       <div id="true" class="condition">
                           <span>预约</span>
                       </div>
                       <div class="connect">
                            联系方式：<span>qq:747482035</span>    
                       </div>
                </div>
            </div>
            <div class="good">
                <a href="#"><img src="/huadian/html/img/img2.jpg" alt=""></a>
                <p class="good_name"><span>徽谷健身卡</span></p>
                <div class="good_des">
                        <span class="good_price">￥ 1188.00</span>
                        <div id="true" class="condition">
                            <span>预约</span>
                        </div>
                        <div class="connect">
                            联系方式：<span>phone:13947830243</span>    
                        </div>
                </div>
            </div>
        </div>
        <div class="good_buy_show">

        </div>
        <div class="good_buy_show">

        </div>
        <div class="good_buy_show">

        </div>
        <div class="good_buy_show">
            
        </div>
        <div class="good_buy_show">

        </div>
        <div class="good_buy_show">

        </div>
        <div class="good_buy_show">

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
</body>
</html>
