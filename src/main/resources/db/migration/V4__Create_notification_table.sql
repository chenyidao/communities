create table notification
(
	id bigint auto_increment,
	notifier bigint not null,
	receive bigint not null,
	outer_id bigint not null,
	type int not null,
	gmt_create bigint,
	status int default 0 not null,
	notifier_name varchar(100) not null,
	outer_title varchar(256) not null,
	constraint notification_pk
		primary key (id)
);

comment on column notification.id is '通知id';

comment on column notification.notifier is '通知者';

comment on column notification.receive is '被通知者';

comment on column notification.outer_id is '被评论的评论或问题id';

comment on column notification.type is '评论or回复';

comment on column notification.gmt_create is '创建时间';

comment on column notification.status is '通知状态(未读/已读)';

