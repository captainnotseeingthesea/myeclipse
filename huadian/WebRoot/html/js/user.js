$(function(){
    $("nav .nav_in .nav_right a").hover(function(){
        $("nav .nav_in .nav_right button").css({
            "background":"#fff",
            "color":"#494949"
        });
        $(this).css({
            "background":"#fc5300",
            "color":"#fff"
        });
    },function(){
    	$("nav .nav_in .nav_right a").css({
            "background":"#fff",
            "color":"#494949"
        });
    });
    $("#center aside .aside_ul li a").click(function(){
    	$("#center aside .aside_ul li a").css({
    		"color":"#2f3031",
    		"background":"#fafafa"
    	});
        $(this).css({
            "color":"#fc5300",
            "background":"#fff"
        });
    });
    $("#center aside .aside_ul li a").eq( $("#center aside .aside").attr("xuhao")).css({
    	"color":"#fc5300",
         "background":"#fff"
    });
    $("#center  .center_right .good").mouseenter(function(){
    	$(this).find(".cancel").finish().animate({
    		height:"40px"
    	},300);
    });
    $("#center  .center_right .good").mouseleave(function(){
    	$(this).find(".cancel").finish().animate({
    		height:"0"
    	},300);
    });
    $("#center  .center_right .good .cancel").click(function(){
    	var msg;
    	if($(this).attr("mark")=="sell"){
    		msg = "您真的确定要取消出售吗？\n\n请确认！"; 
    	}
    	else if($(this).attr("mark")=="reserve"){
   		 	msg = "您真的确定要取消预约吗？\n\n请确认！"; 
    	}
    	else if($(this).attr("mark")=="confirm"){
   		 	msg = "您真的确定要确认交易成功吗？\n\n请确认！"; 
    	}
    	else if($(this).attr("mark")=="want"){
   		 	msg = "您真的确定要确认取消求购吗？\n\n请确认！"; 
    	}
    	else if($(this).attr("mark")=="reserve_want"){
   		 	msg = "您真的确定要确认取消预约吗？\n\n请确认！"; 
    	}
    	  if (confirm(msg)==true){ 
    	    return true; 
    	  }else{ 
    	    return false; 
    	  }  
    });
	if($("#hint").val().length>0){
		alert($("#hint").val());
	}
});