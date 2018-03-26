<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>Sell Goods</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- <link rel="stylesheet" href="froala_editor/css/froala_editor.min.css">
<link rel="stylesheet" href="froala_editor/css/froala_style.min.css"> -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
<link rel="stylesheet" href="froala_editor/css/froala_editor.css">
<link rel="stylesheet" href="froala_editor/css/froala_style.css">
<link rel="stylesheet" href="froala_editor/css/plugins/code_view.css">
<link rel="stylesheet" href="froala_editor/css/plugins/colors.css">
<link rel="stylesheet" href="froala_editor/css/plugins/emoticons.css">
<link rel="stylesheet"
	href="froala_editor/css/plugins/image_manager.css">
<link rel="stylesheet" href="froala_editor/css/plugins/image.css">
<link rel="stylesheet" href="froala_editor/css/plugins/line_breaker.css">
<link rel="stylesheet" href="froala_editor/css/plugins/table.css">
<link rel="stylesheet" href="froala_editor/css/plugins/char_counter.css">
<link rel="stylesheet" href="froala_editor/css/plugins/video.css">
<link rel="stylesheet" href="froala_editor/css/plugins/fullscreen.css">
<link rel="stylesheet" href="froala_editor/css/plugins/file.css">
<link rel="stylesheet" href="froala_editor/css/plugins/quick_insert.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.3.0/codemirror.min.css">

<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="froala_editor/js/froala_editor.min.js"></script>
<script type="text/javascript" src="froala_editor/js/font_size_min.js"></script>
<script type="text/javascript" src="froala_editor/js/fullscreen.min.js"></script>
<script type="text/javascript" src="froala_editor/js/image.min.js"></script>
<script type="text/javascript"
	src="froala_editor/js/image_manager.min.js"></script>
	<script type="text/javascript"src="froala_editor/js/word_paste.min.js"></script>
	
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

#edit {
	margin: 0 auto;
	width: 80%;
	text-align: left;
}
</style>
</head>

<body>
	<div id="edit">
		<div>
		
		</div>
	</div>
	<br />
	<script type="text/javascript">
		$(function() {
			$('#edit').froalaEditor();
		});
	</script>
</body>
</html>
