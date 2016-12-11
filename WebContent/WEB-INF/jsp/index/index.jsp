<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<a href="javascript:getData()">获取json</a>
	<a href="javascript:getById()">获取getById</a><input type="text" id="myid"><span id="myspan"></span>
	<table>
		<thead>
			<tr>
				<td>id</td>
				<td>商品名称</td>
				<td>数量</td>
			</tr>
		</thead>
		<tbody id="mytbody"></tbody>
	</table>
</body>
<script src="./resource/js/jquery-1.9.1.min.js"></script>
<script>
	function getData()
	{
		$.ajax({
			url : 'getData.json',
			type : 'GET',
			dataType : 'json',
			success : function(data)
			{
				var tbody;

				$('#mytbody').empty();

				$.each(data, function(i, result)
				{
					tbody = "<tr><td>" + result['seckillId'] + "</td><td>"
							+ result['name'] + "</td><td>" + result['number'];
					$('#mytbody').append(tbody);
				})

			},
			error : function(XMLHttpRequest, textStatus, errorThrown)
			{
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			},
			complete : function(XMLHttpRequest, textStatus, errorThrown)
			{
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
			}
		})
	}
</script>
<script>
	function getById()
	{
		$.ajax({
			url : 'getById.json',
			type : 'GET',
			dataType : 'json',
			data : {
				seckillId : $('#myid').val()
			},
			beforeSend : function()
			{
				$('#myspan').html("Loading...");
			},
			success : function(result)
			{
				var tbody;
				console.log(result);
				$('#mytbody').empty();
				$('#myspan').html("Success!");
				tbody = "<tr><td>" + result['seckillId'] + "</td><td>"
						+ result['name'] + "</td><td>" + result['number'];
				$('#mytbody').append(tbody);

			},
			error : function(XMLHttpRequest, textStatus, errorThrown)
			{
				console.log(XMLHttpRequest.status);
				console.log(XMLHttpRequest.readyState);
				console.log(textStatus);
				$('#myspan').html("不存在此商品！");
			}
		})
	}
</script>
</html>