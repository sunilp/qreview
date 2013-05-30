# --- First database schema

# --- !Ups

create table user_dtl (
  username                        	varchar(255) not null,
  name                      		varchar(255),
  password                      	varchar(255),
  designation                      	varchar(255),
  constraint pk_user primary key (username))
;

create table review_dtl (
  reviewId                        varchar(255) not null,
  reviewerId                      varchar(255),
  type                      	  varchar(255),
  severity                      	varchar(255),
  fileName                      	varchar(255),
  summery                      	varchar(255),
  description                      	varchar(4000),
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


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user_dtl;

drop table if exists review_dtl;

drop table if exists review_dtl_history;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_dtl_seq;

drop sequence if exists review_dtl_seq;

drop sequence if exists review_dtl_history_seq;

