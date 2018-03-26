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
    })
})