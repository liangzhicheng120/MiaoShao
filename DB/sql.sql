create database seckill;

use seckill;

create table seckill (
seckill_id bigint not null auto_increment comment '商品库存id',
name  varchar(120) not null comment '商品名称',
number  int not null comment '库存数量',
start_time timestamp not null comment '秒杀开启时间',
end_time timestamp not null comment '秒杀结束时间',
create_time timestamp not null default current_timestamp comment '创建时间',
primary key (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)engine = innoDB auto_increment = 1000 default charset = utf8 comment = '秒杀库存表';

-- 初始化数据
insert into 
	seckill (name,number,start_time,end_time)
values
('1000元秒杀iphone',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
('500元秒杀ipad2',200,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
('300元秒杀小米4',300,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
('200元秒杀小米note',400,'2016-01-01 00:00:00','2016-01-02 00:00:00');

-- 秒杀成功明细表
create table seckill_success (
seckill_id bigint  not null comment '秒杀商品id',
user_phone bigint not null comment '用户手机',
state tinyint not null default -1 comment '状态标示：-1 无效 0：成功 1：已付款',
create_time timestamp not null comment'创建时间', 
primary key(seckill_id,user_phone)/*联合主键*/
)engine = innoDB  default charset = utf8 comment = '秒杀成功明细表';