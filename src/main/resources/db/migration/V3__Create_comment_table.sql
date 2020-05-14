create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	type int,
	content varchar(1024) not null,
	commentator bigint not null,
	like_count bigint default 0 not null,
	gmt_create bigint not null,
	gmt_modified bigint not null,
	comment_count int default 0,
	constraint comment_pk
		primary key (id)
);

comment on column comment.id is '评论id';

comment on column comment.parent_id is '父类id';

comment on column comment.type is '评论类型';

comment on column comment.content is '评论内容';

comment on column comment.commentator is '评论人';

comment on column comment.like_count is '点赞次数';

comment on column comment.gmt_create is '创建时间';

comment on column comment.gmt_modified is '修改时间';