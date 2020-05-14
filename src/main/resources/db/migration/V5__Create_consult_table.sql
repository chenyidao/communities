create table consult
(
	id bigint auto_increment,
	consultee bigint not null,
	consultant bigint not null,
	content varchar(256) not null,
	fee varchar(256) default 0 not null,
	gmt_create bigint,
	constraint consult_pk
		primary key (id)
);

comment on column consult.consultee is '咨询者id';

comment on column consult.consultant is '被咨询者id';

comment on column consult.content is '咨询内容';

comment on column consult.fee is '咨询费用';

comment on column consult.gmt_create is '创建时间';

