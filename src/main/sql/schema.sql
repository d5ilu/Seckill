

CREATE TABLE seckill
(
seckill_id bigint NOT NULL AUTO_INCREMENT COMMENT '��Ʒid',
name varchar(120) NOT NULL COMMENT '��Ʒ����',
number int NOT NULL COMMENT '�������',
start_time timestamp NOT NULL COMMENT '��ʼʱ��',
end_time timestamp NOT NULL COMMENT '����ʱ��',
creat_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time), 
key idx_creat_time(creat_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='��ɱ����';

insert into seckill(name,number,start_time,end_time)
	values
	('1000Ԫ��ɱiphone6',100,'2018-11-11 00:00:00','2018-11-12 00:00:00'),
	('500Ԫ��ɱipad2',100,'2018-11-11 00:00:00','2018-11-12 00:00:00'),
	('300Ԫ��ɱС��4',100,'2018-11-11 00:00:00','2018-11-12 00:00:00'),
	('500Ԫ��ɱС��5',100,'2018-11-11 00:00:00','2018-11-12 00:00:00');
	
CREATE TABLE success_killed(
seckill_id bigint NOT NULL COMMENT '��ɱ��Ʒid',
user_phone bigint NOT NULL COMMENT '�û��ֻ���',
state tinyint NOT NULL DEFAULT -1 COMMENT '״̬��ʾ -1 ��Ч 0�ɹ� 1�Ѹ���',
creat_time timestamp NOT NULL COMMENT '����ʱ��',
PRIMARY KEY(seckill_id,user_phone),
key idx_creat_time(creat_time)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='��ɱ�ɹ���ϸ��';