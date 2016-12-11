var getMagic =
{
	url :
	{
		magic : function(param)
		{
			return '/Miaosha/magic/getMagic/' + param;
		}
	},
	init : function()
	{
		$('#number').bind('input propertychange', function()
		{
			var number = $('#number').val();
			if (number != '')
			{
				$.ajax(
				{
					url : getMagic.url.magic(number),
					type : 'GET',
					dataType : 'json',
					success : function(arr)
					{
						getMagic.msg('');
						getMagic.setBody(arr);
					},
					error : function(er)
					{
						getMagic.msg('ÇëÊäÈëÆæÊý½×Êý£¡');
					}
				})
			}
			;
			getMagic.clear();
		})
	},
	msg : function(msg)
	{
		$('#msg').html(msg);
	},
	html :
	{
		input : function(param)
		{
			return '<td>' + param + '</td>';
		}
	},
	setBody : function(arr)
	{
		var body = $('#setMagicBody');
		body.empty();
		for (var i = 0; i < arr.length; i++)
		{
			var arr_link = arr[i];
			var tbody = '<tr>';
			for (var j = 0; j < arr_link.length; j++)
			{
				tbody += getMagic.html.input(arr_link[j]);
			}
			tbody += '</tr>';
			body.append(tbody);
		}
	},
	clear : function()
	{
		getMagic.msg('');
		$('#setMagicBody').empty();
	}
}