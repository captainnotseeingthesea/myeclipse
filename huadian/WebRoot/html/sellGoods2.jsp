<%@page import="java.io.File"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
/* 	String path1="C:/Users/Administrator/Downloads/新建文件夹/js";
	int fileCount = 0;
	int folderCount = 0; 
	File d = new File(path1);
	File list[] = d.listFiles();
	for(int i = 0; i < list.length; i++){
	    if(list[i].isFile()){
	        fileCount++;
	    }else{
	        folderCount++;
	    }
}
System.out.println("文件个数："+fileCount);
System.out.println("文件夹数："+folderCount); */
	
 %>
<!DOCTYPE HTML>  
<html>  
<head>  
    <meta charset="UTF-8">  
    <title> 上传多张图片</title>  
    <link rel="stylesheet" href="/huadian/html/css/sellGoods.css">
    <script type="text/javascript" src="/huadian/html/js/jquery-1.11.3.min.js"></script>
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
    						<!-- 商品描述信息 -->
<div id="centerBox">
	<div id="title">
		<img src="/huadian/html/img/sellGoodsLogo.png">
	</div>
<div class="leftBox">
<form id="upBox" action="/huadian/servlet/SellGoods" method="post"   enctype="multipart/form-data">
 	<div id="inputBox">
        <input type="file" id="file"  multiple="multiple"  name="fileAttach" onchange="xmTanUploadImg(this)" required="required"  />
   		 点击选择图片
    </div>
    <div class="img-box" id="imgboxid">

    </div>
    <div id="errordiv" style="margin-top:15px;width:100%;text-align:center;">
    	<input id="btnSub" type="submit" value="提交" />
   	</div>
</form>
</div>
<div class="rightBox">
	<label for="goodsName" class="lab"><input id="goodsName" type="text" name="goodsName" placeholder="商品名称" maxlength="10" required form="upBox"><span>物品名称不可超过10个字</span></label>
	<br/>

	<label for="goodsClass" class="lab"><select name="goodsClass" id="goodsClass" required form="upBox">
		<option value="1">耳机</option>
		<option value="2">台灯</option>
		<option value="3">饰品</option>
		<option value="4">书本</option>
		<option value="5">电子设备</option>
		<option class="6">各类卡片</option>
	</select><span>选择物品的种类</span></label>
	<br/>

	<label for="price" class="lab"><input type="number" name="price" id="price" required form="upBox" step="0.01">
		<span>商品价格(元)</span></label>
	<br/>

	<label for="sellerContact" class="lab"><input type="tel" name="sellerContact"
		id="sellerContact" required form="upBox"><span>您的联系方式</span></label>
	<textarea rows="13" cols="60" name="description" id="description" form="upBox" required="required" autofocus="autofocus" placeholder="请填写商品的描述信息"></textarea>
</div>
</div>
<script type="text/javascript">
	var temp=0;//设置每个
    //选择图片，马上预览  
    function xmTanUploadImg(obj) {  
  		var file=document.getElementById("file");
        var fl=obj.files.length;
        if(fl>6){
        	alert("最多可选择6个文件！");
        	file.outerHTML=file.outerHTML;
        	return;
        }  
        for(var i=0;i<fl;i++){  
            var file=obj.files[i];  
            var reader = new FileReader();  
  
            //读取文件过程方法  
  
            reader.onloadstart = function (e) {  
                console.log("开始读取....");  
            };  
            reader.onprogress = function (e) {  
                console.log("正在读取中....");  
            };  
            reader.onabort = function (e) {  
                console.log("中断读取....");  
            };  
            reader.onerror = function (e) {  
                console.log("读取异常....");  
            };  
            reader.onload = function (e) { 
             	temp++;
             	console.log(temp);
                console.log("成功读取...."); 
  				if(!/image\/\w+/.test(file.type)){
  					alert("请确保文件类型为图像类型！");
  					return false;
  				}
                var imgstr='<img style="width:100%;height:100%;" src="'+e.target.result+'"/>';  
                var oimgbox=document.getElementById("imgboxid");  
                var ndiv=document.createElement("div");  
                var delDiv=document.createElement("div"); 
  				var deleteBtn='<input type="button"value="删除" style="width:100%;height:100%;">';
                ndiv.innerHTML=imgstr;  
                ndiv.className="img-div";
                ndiv.id=temp; 
                delDiv.className="delDiv";
                delDiv.setAttribute("flag",temp);
                delDiv.innerHTML=deleteBtn; 
                oimgbox.appendChild(ndiv);  
                ndiv.appendChild(delDiv);
                
                ndiv.onmouseover=function(){
                	delDiv.style.display="block";
                };
                ndiv.onmouseout=function(){
                	delDiv.style.display="none";
                };
               delDiv.onclick=function(){
               		oimgbox.removeChild(document.getElementById(this.getAttribute("flag")));
               };
                
            };
  
            reader.readAsDataURL(file);   
        }  
   }
</script>  
</body> 
</html>
