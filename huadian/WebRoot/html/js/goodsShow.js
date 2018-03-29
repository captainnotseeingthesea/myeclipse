$(function(){
	var fileCount=$("#main .lunbotu").attr("fileCount");
	var temp =0;
	var timeout;
	var timer = setInterval(moveRight, 2000);
	$("#main .lunbotu .lbt_li").eq(0).css({
		"color":"#fc5300"
	});
	$("#main .lunbotu .pic").css({
		"display":"none"
	});
	$("#main .lunbotu .pic").eq(0).css({
		"display":"block"
	});
	$("#main .lunbotu").mousemove(function() {
		clearInterval(timer);
		clearTimeout(timeout);
		timeout = setTimeout(function() {
			timer = setInterval(moveRight, 2000);
		}, 2000)
	});
	$("#main .lunbotu .lbt_li").click(
			function() {
				temp=$(this).attr("xuhao");
				$("#main .lunbotu .pic").finish().not($("#main .lunbotu .pic").eq(temp)).fadeOut();
				$("#main .lunbotu .pic").finish().eq(temp).fadeIn();
				$("#main .lunbotu .lbt_ul .lbt_li").css({
						"backgroundColor" : "black"
					}).eq(temp).css({
						"backgroundColor" : "#fc5300"
					});
			});
	function moveRight() {
		temp = ++temp % fileCount;
		$("#main .lunbotu .pic").finish().not($("#main .lunbotu .pic").eq(temp)).fadeOut();
		$("#main .lunbotu .pic").finish().eq(temp).fadeIn();
		$("#main .lunbotu .lbt_ul .lbt_li").css({
			"backgroundColor" : "black"
		}).eq(temp).css({
			"backgroundColor" : "#fc5300"
		});
	};
	function moveLeft() {
		if(temp==0){
				temp=fileCount-1;
			}
			else{
				temp=temp-1;
			}
		$("#main .lunbotu .pic").finish().not($("#main .lunbotu .pic").eq(temp)).fadeOut();
		$("#main .lunbotu .pic").finish().eq(temp).fadeIn();
		$("#main .lunbotu .lbt_ul .lbt_li").css({
			"backgroundColor" : "black"
		}).eq(temp).css({
			"backgroundColor" : "#fc5300"
		});
	};
	$("#main .lunbotu .btn").click(function(){
		if($(this).prop('class').indexOf("left")!=-1){
			moveLeft();
		}
		else{
			moveRight();
		}

	})
})