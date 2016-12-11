// javascript ģ�黯���ְ�
var seckill =
{
	// ��װ��ɱ��ص�ajax��url
	URL :
	{
		now : function()
		{
			return '/Miaosha/seckill/time/now';
		},
		exposer : function(seckillId)
		{
			return '/Miaosha/seckill/' + seckillId + '/exposer';
		},
		execution : function(seckillId, md5)
		{
			return '/Miaosha/seckill/' + seckillId + '/' + md5 + '/execution'
		}
	},
	handleSeckillkill : function(seckillId, node)
	{
		// ������ɱ�߼�
		node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">��ʼ��ɱ</button>');// ��ť
		$.post(seckill.URL.exposer(seckillId),
		{}, function(result)
		{
			// �ڻص�������,ִ�н�������
			if (result && result['success'])
			{
				var exposer = result['data'];

				if (exposer['exposed'])
				{
					// ������ɱ
					var md5 = exposer['md5'];
					var killUrl = seckill.URL.execution(seckillId, md5);
					console.log('killUrl:' + killUrl); // TODO killUrl
					// ��һ�ε���¼�
					$('#killBtn').one('click', function()
					{
						// ��ִ����ɱ�Ĳ���
						// ���ð�ť
						$(this).addClass('disabled');
						// ������ɱ����
						$.post(killUrl,
						{}, function(result)
						{
							if (result && result['success'])
							{
								var killResult = result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								// ��ʾ���
								node.html('<span class="label label-success">' + stateInfo + '</span>');
							}
						});
					});
					node.show();
				}
				else
				{
					// ��ɱδ����
					var now = exposer['now'], start = exposer['start'], end = exposer['end'];
					// ���½����ʱ�߼�
					seckill.countdown(seckillId, now, start, end);

				}
			}
			else
			{
				console.log('result:' + result);
			}
		});
	},
	// ��֤�ֻ���
	validatePhone : function(phone)
	{
		if (phone && phone.length == 11 && !isNaN(phone))
		{
			return true;
		}
		else
		{
			return false;
		}
	},
	countdown : function(seckillId, nowTime, startTime, endTime)
	{
		var seckillBox = $('#seckill-box');
		// ʱ���ж�
		if (nowTime > endTime)
		{
			// ��ɱ����
			seckillBox.html('��ɱ����!');
		}
		else if (nowTime < startTime)
		{
			// ��ɱδ��ʼ,��ʱ�¼���
			var killTime = new Date(startTime + 1000);
			seckillBox.countdown(killTime, function(event)
			{
				seckillBox.css(
				{
					display:'block'
				});
				// ʱ���ʽ
				var format = event.strftime('��ɱ����ʱ��%D�� %Hʱ %M�� %S��');
				seckillBox.html(format);
				// ʱ����ɺ�Ļص��¼�
			}).on('finish.countdown', function()
			{
				// ��ȡ��ɱ��ַ,������ʵ�߼�,ִ����ɱ
				seckill.handleSeckillkill(seckillId, seckillBox);
			});
		}
		else
		{
			// ��ɱ��ʼ
			seckill.handleSeckillkill(seckillId, seckillBox);
		}
	},
	// ����ҳ��ɱ�߼�
	detail :
	{
		// ����ҳ��ʼ��
		init : function(params)
		{
			// �ֻ���֤�͵�¼,��ʱ����
			// ��cookie�в����ֻ���
			var killPhone = $.cookie('killPhone');
			var startTime = params['starTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId'];
			// ��֤�ֻ���
			if (!seckill.validatePhone(killPhone))
			{
				// ��phone
				// �������
				$('#killPhoneModal').modal(
				{
					show : true, // ��ʾ������
					backdrop : 'static', // ��ֹλ�ùر�
					keyboard : false, // �رռ����¼�
				});
				$('#killPhoneBtn').click(function()
				{
					var inputPhone = $('#killPhoneKey').val();
					console.log('inputPhone=' + inputPhone) // TODO inputPhone
					if (seckill.validatePhone(inputPhone))
					{
						// �绰д��cookie
						$.cookie('killPhone', inputPhone,
						{
							expires : 7,
							path : '/Miaosha/seckill'
						});
						// ˢ��ҳ��
						window.location.reload();
					}
					else
					{
						$('#killPhoneMessage').hide().html('<label class="label label-danger">�ֻ��Ŵ���</label>').show(300);
					}
				});
			}
			// ��¼����
			// ��ʱ����
			$.get(seckill.URL.now(),
			{}, function(result)
			{
				if (result && result['success'])
				{
					var nowTime = result['data'];
					// ʱ���ж�,��ʱ����
					seckill.countdown(seckillId, nowTime, startTime, endTime);
				}
				else
				{
					console.log('result:' + result); // TODO result
				}
			});
		}
	},
}