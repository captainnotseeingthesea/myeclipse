$(function(){
	var timer = setInterval(move, 2000);
	$("#pic").mousemove(function() {
		clearInterval(timer);
		clearTimeout(timeout);
		timeout = setTimeout(function() {
			timer = setInterval(move, 2000);
		}, 1000)
	});
	$("#center .lunbotu .lbt_pic .lbt_li").click(
			function() {
				if (temp != $(this).attr("xuhao")) {
					temp = $(this).attr("xuhao");
					$("#center .lunbotu .lbt_pic .pic a").stop().eq(2).width(
							'0').prependTo("#center .lunbotu .lbt_pic .pic")
							.animate({
								"width" : "800px"
							}, 800);
					$("#center .lunbotu .lbt_pic .lbt_li").css({
						"backgroundColor" : "black"
					}).eq(temp).css({
						"backgroundColor" : "#fc5300"
					});
				}
			});
	function move() {
		temp = ++temp % 3;
		$("#center .lunbotu .lbt_pic .pic a").eq(2).width('0').prependTo(
				"#center .lunbotu .lbt_pic .pic").animate({
			"width" : "800px"
		}, 800);
		$("#center .lunbotu .lbt_pic .lbt_li").css({
			"backgroundColor" : "black"
		}).eq(temp).css({
			"backgroundColor" : "#fc5300"
		});
	};
	move();
})