

CREATE TABLE seckill
(
seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '商品id',
name varchar(120) NOT NULL COMMENT '商品名称',
number int NOT NULL COMMENT '库存数量',
start_time timestamp NOT NULL COMMENT '开始时间',
end_time timestamp NOT NULL COMMENT '结束时间',
creat_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time), 
key idx_creat_time(creat_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

insert into seckill(name,number,start_time,end_time)
	values
	('1000元秒杀iphone6',100,'2018-11-11 00:00:00','2018-11-12 00:00:00'),
	('500元秒杀ipad2',100,'2018-11-11 00:00:00','2018-11-12 00:00:00'),
	('300元秒杀小米4',100,'2018-11-11 00:00:00','2018-11-12 00:00:00'),
	('500元秒杀小米5',100,'2018-11-11 00:00:00','2018-11-12 00:00:00');
	
CREATE TABLE success_killed(
seckill_id bigint NOT NULL COMMENT '秒杀商品id',
user_phone bigint NOT NULL COMMENT '用户手机号',
state tinyint NOT NULL DEFAULT -1 COMMENT '状态标示 -1 无效 0成功 1已付款',
creat_time timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY(seckill_id,user_phone),
key idx_creat_time(creat_time)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';