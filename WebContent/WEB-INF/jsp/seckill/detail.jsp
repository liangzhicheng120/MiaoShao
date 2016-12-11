<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>秒杀列表详情页</title>
<%@include file="../common/head.jsp"%>
<%@include file="../common/tag.jsp"%>
</head>
<body>

	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1>${seckill.name }</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<span class="glyphicon glyphicon-time"></span> 
					<span class="glyphicon" id="seckill-box"></span>
				</h2>
			</div>
		</div>
	</div>

	<div class="modal fade" id="killPhoneModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title text-center">
						<span class="glyphicon glyphicon-phone"></span>秒杀电话
					</h3>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-8 col-xs-offset-2">
							<input type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机号" class="form-control">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<span class="glyphicon" id="killPhoneMessage"></span>
					<button type="button" id="killPhoneBtn" class="btn btn-success">
						<span class="glyphicon glyphicon-phone"></span>Submit
					</button>
				</div>
			</div>
		</div>
	</div>


</body>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="//cdn.bootcss.com/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<script src="/Miaosha/resource/js/seckill.js" charset="GBK"></script>
<script type="text/javascript">
	$(function()
	{
		seckill.detail.init(
		{
			seckillId : "${seckill.seckillId}",
			startTime : "${seckill.startTime.time}",// 毫秒
			endTime : "${seckill.endTime.time}"
		});
	});
</script>
</html>