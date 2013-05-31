# --- First database schema

# --- !Ups

create table user_dtl (
  username                        	varchar(255) not null,
  name                      		varchar(255),
  password                      varchar(255),
  designation                   varchar(255),
  supervisor                    varchar(255),
  lastlogin                     timestamp,
  groupname                     varchar(255),
  alias                         varchar(255),
  alt_email                     varchar(255),
  psnl_email                    varchar(255),
  pri_email                     varchar(255),
  constraint pk_user primary key (username))
;

create table review_dtl (
  reviewId                        varchar(255) not null,
  reviewerId                      varchar(255),
  type                      	  varchar(255),
  severity                      	varchar(255),
  fileName                      	varchar(255),
  summery                      	varchar(2000),
  description                      	varchar(6000),
  phase                      	varchar(255),
  assignedTo                      	varchar(255),
  author                      	varchar(255),
  creation_dt                timestamp,
  modification_dt              timestamp,
  modifier                varchar(255),
  line                varchar(255),
  revision                varchar(255),
  status                varchar(255),
  constraint pk_review primary key (reviewId))
;


create table review_dtl_history (
  review_dtl_htr_id                  varchar(255),
  reviewId                        	varchar(255),
  creation_dt                timestamp,
  attrName                        	varchar(255),
  modifier                     		varchar(255),
  newval                      	varchar(255),
  oldval                      	varchar(255),
  constraint pk_review_dtl_history primary key (review_dtl_htr_id))
;

create sequence user_dtl_seq start with 1000;

create sequence review_dtl_seq start with 1000;

create sequence review_dtl_history_seq start with 1000;

--alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_user_review_1 on review_dtl (reviewerId);





create table task_dtl (
  id                            varchar(255) primary key,
  owner                         varchar(255),
  todo                          varchar(255),
  status                        varchar(255),
  details                       varchar(255),
  due                           timestamp,
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

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user_dtl;

drop table if exists review_dtl;

drop table if exists review_dtl_history;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_dtl_seq;

drop sequence if exists review_dtl_seq;

drop sequence if exists review_dtl_history_seq;




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

