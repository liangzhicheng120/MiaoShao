create database seckill;

use seckill;

create table seckill (
seckill_id bigint not null auto_increment comment '��Ʒ���id',
name  varchar(120) not null comment '��Ʒ����',
number  int not null comment '�������',
start_time timestamp not null comment '��ɱ����ʱ��',
end_time timestamp not null comment '��ɱ����ʱ��',
create_time timestamp not null default current_timestamp comment '����ʱ��',
primary key (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine = innoDB auto_increment = 1000 default charset = utf8 comment = '��ɱ����';

-- ��ʼ������
insert into 
	seckill (name,number,start_time,end_time)
values
('1000Ԫ��ɱiphone',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
('500Ԫ��ɱipad2',200,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
('300Ԫ��ɱС��4',300,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
('200Ԫ��ɱС��note',400,'2016-01-01 00:00:00','2016-01-02 00:00:00');

-- ��ɱ�ɹ���ϸ��
create table seckill_success (
seckill_id bigint  not null comment '��ɱ��Ʒid',
user_phone bigint not null comment '�û��ֻ�',
state tinyint not null default -1 comment '״̬��ʾ��-1 ��Ч 0���ɹ� 1���Ѹ���',
create_time timestamp not null comment'����ʱ��', 
primary key(seckill_id,user_phone)/*��������*/
)engine = innoDB  default charset = utf8 comment = '��ɱ�ɹ���ϸ��';