create table consult
(
	id int auto_increment,
	out_trade_no varchar(256) default 订单号 not null,
	total_count bigint default 付款金额 not null,
	subject varchar(256) default 订单名称 not null,
	description varchar(256) default 商品描述,
	constraint consult_pk
		primary key (id)
);

comment on table consult is '付费咨询';