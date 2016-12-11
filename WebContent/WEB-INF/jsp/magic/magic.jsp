<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	request.setAttribute("domain", "http://192.168.1.103:8080/Miaosha/");
%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>magic</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
	<div class="container" style="margin-top: 20px;">
		
		<div class="row">
			<div class="col-md-2 col-md-offset-5">
				<div class="input-group input-group-sm">
					<span id="magicBtn" class="input-group-addon">阶数</span> <input type="text" id="number" placeholder="请输入奇数阶数" class="form-control">
				</div>
			</div>

			<div class="col-md-3">
				<span id="msg" style="color: red;"></span>
			</div>

		</div>
		<hr>
		<div class="row">
			<div>
				<table class="table text-center table-hover table-bordered responsive">
					<tbody id="setMagicBody">

					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="./resource/js/magic.js" charset="GBK"></script>
<script>
	$(function()
	{
		getMagic.init();
	});
</script>
</html>