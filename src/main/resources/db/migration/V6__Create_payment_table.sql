create table payment
(
   id bigint auto_increment,
   user_id bigint not null,
   out_trade_no varchar(256) not null,
   trade_no varchar(256) not null,
   total_amount varchar(256),
   gmt_create bigint not null,
   constraint payment_pk
      primary key (id)
);

comment on column payment.user_id is '支付人id';

comment on column payment.out_trade_no is '商品订单号';

comment on column payment.trade_no is '支付宝交易号';

comment on column payment.total_amount is '付款金额';

comment on column payment.gmt_create is '创建时间';
