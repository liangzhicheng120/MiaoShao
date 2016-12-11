<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>秒杀列表页</title>
<%@include file="../common/head.jsp"%>
<%@include file="../common/tag.jsp"%>
</head>
<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading text-center">
				<h1>秒杀列表</h1>
			</div>
			<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>库存</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>创建时间</th>
							<th>详情页</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="sk">
							<tr>
								<td>${sk.name }</td>
								<td>${sk.number }</td>
								<td><fmt:formatDate value="${sk.startTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${sk.endTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${sk.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><a class="btn btn-info" href="./${sk.seckillId }/detail" target="_blank">link</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>


</body>
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="/Miaosha/resource/js/factory.js" charset="GBK"></script>
</html>