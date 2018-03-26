$(function() {
	var timer;
	var timeout;
	var temp = 0;
	var tishi = "<span class='login_tishi' style='position:absolute;left:150px;color:red;'></span>";
	$(".login_header").after(tishi);
	$(".register_header").after(tishi);
	if (localStorage.getItem("biaoji") == 1) {
		$("#login_name").val(localStorage.getItem("username"));
		$("#login_password").val(localStorage.getItem("password"));
	}
	$("#login_submit").click(
			function() {
				$.ajax({
					type : "POST",
					cache : false,
					dataType : "json",
					url : "../servlet/login",
					data : {
						"username" : $("#login_name").val(),
						"password" : $("#login_password").val()
					},
					success : function(js) {
						// var json=eval("("+data+")");
						//alert(data);
						
//						var json = JSON.stringify(data);
						
						var username = $("#login_name").val();
						var user_img = "<img  src='img/img4.gif'/>";
						var user_name = "<a href='user.jsp?username="+username+"' style='font-size:12px'> "
								+ username + "</a>";
//						var js = JSON.parse(json);
//						alert(data+"----"+json+"-----"+js);
						if (js["i"] == 0) {
							if ($("#login_remember").prop("checked")) {
								localStorage.setItem("biaoji", 1);
								localStorage.setItem("username", $(
										"#login_name").val());
								localStorage.setItem("password", $(
										"#login_password").val());
							} else {
								localStorage.setItem("biaoji", 0);
							}
							$(".login").css({
								"display" : "none"
							});
							$("#header .header_right .header_li a").eq(0)
									.remove();
							$("#header .header_right .header_li").eq(0).html(
									user_img);
							$("#header .header_right .header_li").eq(1).html(
									user_name);
						} else if (js["i"] == 2) {
							$(".login_tishi").html("用户名不存在");
						} else {
							$(".login_tishi").html("密码错误");
						}
					},
					error : function(a) {
						alert("b");
					}

				});
			})
	$("#register_submit").click(function() {
		$.ajax({
			type : "POST",
			cache : false,
			dataType : "json",
			url : "../servlet/Register",
			data : {
				"username" : $("#register_name").val(),
				"password1" : $("#register_password1").val(),
				"password2" : $("#register_password2").val()
			},
			success : function(data) {
				var json = JSON.parse(JSON.stringify(data));
				if (json["temp"] == 0) {
					$(".register").css({
						"display" : "none"
					});
					alert('注册成功，请前往登陆');
				} else if (json["temp"] == 1) {
					$(".login_tishi").html("用户名格式不正确");
				} else if (json["temp"] == 2) {
					$(".login_tishi").html("两次密码不匹配");
				} else if (json["temp"] == 3) {
					$(".login_tishi").html("密码不可为空");
				} else {
					$(".login_tishi").html("用户名已经存在");
				}
			},
			error : function(a) {
				alert("注册失败，原因为" + a.status);
			}
		})

	})

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
	}
	;
	function sel() {
		if (!$("#radio").prop("checked")) {
			$("#register_submit").attr({
				"disabled" : "true"
			});
		} else {
			$("#register_submit").removeAttr("disabled");
		}
	}
	var timer = setInterval(move, 2000);
	$("#center .lunbotu .lbt_pic").mousemove(function() {
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
	$(".header_right .header_li a").eq(0).click(function() {
		$("#center .login").css({
			"display" : "block"
		});
		$("#center .register").css({
			"display" : "none"
		});
		$(".login_tishi").html("");
	});
	$(".header_right .header_li a").eq(1).click(function() {
		$("#center .register").css({
			"display" : "block"
		});
		$("#center .login").css({
			"display" : "none"
		});
		$(".login_tishi").html("");
	});
	$("#center .login .login_header div").click(function() {
		$("#center .login").css({
			"display" : "none"
		});
		$(".login_tishi").html("");
	});
	$("#center .register .register_header").click(function() {
		$("#center .register").css({
			"display" : "none"
		});
		$(".login_tishi").html("");
	})
	sel();
	$("#radio").click(function() {
		sel();
	})
	$("#center .login .login_header p a").click(function() {
		$("#center .register").css({
			"display" : "block"
		});
		$("#center .login").css({
			"display" : "none"
		});
		$(".login_tishi").html("");
	})
	$("#nav .nav_in .nav_box .nav_ul .nav_li a").eq(0).click(function() {
		$("#goods .good_buy").css({
			"display" : "block"
		});
		$("#goods .good_want").css({
			"display" : "none"
		});
		$("#goods .good_buy_show").css({
			"display" : "none"
		}).eq(0).css({
			"display" : "block"
		});
	})
	$("#nav .nav_in .nav_box .nav_ul .nav_li a").eq(1).click(function() {
		$("#goods .good_buy").css({
			"display" : "block"
		});
		$("#goods .good_want").css({
			"display" : "none"
		});
		$("#goods .good_buy_show").css({
			"display" : "none"
		}).eq(0).css({
			"display" : "block"
		});
	})
	$("#nav .nav_in .nav_box .nav_ul .nav_li a").eq(2).click(function() {
		$("#goods .good_buy").css({
			"display" : "none"
		});
		$("#goods .good_want").css({
			"display" : "block"
		});

	})
	$("aside div span a").click(function() {
		var xh = $(this).attr("xh");
		$("#goods .good_buy_show").css({
			"display" : "none"
		}).eq(xh).css({
			"display" : "block"
		});
	})
})
