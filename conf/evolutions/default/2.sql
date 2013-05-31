
# --- !Ups
ALTER TABLE review_dtl ADD comment varchar(2000);

ALTER TABLE review_dtl ADD groupname varchar(2000);


ALTER TABLE review_dtl MODIFY summery varchar(2000);
ALTER TABLE review_dtl MODIFY description varchar(6000);

ALTER TABLE user_dtl ADD supervisor varchar(255);
ALTER TABLE user_dtl ADD lastlogin timestamp;
ALTER TABLE user_dtl ADD groupname varchar(255);
ALTER TABLE user_dtl ADD alias varchar(255);
ALTER TABLE user_dtl ADD alt_email varchar(255);
ALTER TABLE user_dtl ADD psnl_email varchar(255);
ALTER TABLE user_dtl ADD pri_email varchar(255);



create table task_dtl (
  id                            varchar(255) primary key,
  owner                         varchar(255),
  todo                      	  varchar(255),
  status                      	varchar(255),
  details                      	varchar(255),
  due                      	    timestamp,
  createdAt                     timestamp,
  closedAt                      timestamp,
  done                          varchar(1),   
  author                        varchar(255),
  tskgroup                         varchar(10),
  active                        varchar(1)
  )
;

create table task_group_dtl (
  id                            varchar(10) primary key,
  name                          varchar(255),
  details                       varchar(1000)
  )
;

create table role_dtl (
id                varchar(10) primary key,
name              varchar(255)
)
;


create table user_role_xref (
username                varchar(255),
role_id                 varchar(10),
active                  varchar(1)
)

;


create sequence task_dtl_seq start with 1000;

create sequence role_dtl_seq start with 1000;

create sequence task_group_dtl_seq start with 1000;



create index ix_task_owner_1 on task_dtl (owner);

# --- !Downs
ALTER TABLE review_dtl DROP comment varchar(2000);

ALTER TABLE review_dtl DROP groupname varchar(2000);



SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user_dtl;

drop table if exists task_dtl;

drop table if exists task_group_dtl;

drop table if exists role_dtl;

drop table if exists user_role_xref;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_dtl_seq;

drop sequence if exists task_dtl_seq;

drop sequence if exists role_dtl_seq;

drop sequence if exists task_group_dtl_seq;