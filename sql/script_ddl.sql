drop table if exists USERTAB cascade;
create table USERTAB (
	ID serial primary key,
	LOGIN varchar(50) not null,
	PASSWORD varchar(50) not null,
	NAME varchar(50) not null,
	DESCR varchar(200)
);

drop table if exists PRIVILEGE cascade;
create table PRIVILEGE (
	ID serial primary key,
	CODE integer not null,
	ARG0 varchar(50),
	ARG1 varchar(50),
	USERTAB_REF integer not null references USERTAB
);

drop table if exists SUBSYSTEM cascade;
create table SUBSYSTEM (
	ID serial primary key,
	NAME varchar(50) not null,
	DESCR varchar(200),
	USER_OWNER_REF integer not null references USERTAB
);

drop table if exists SUBSYSTEM_USER cascade;
create table SUBSYSTEM_USER (
	ID serial primary key,
	USER_REF integer not null references USERTAB,
	SUBSYSTEM_REF integer not null references SUBSYSTEM
);

drop table if exists SUBSYSTEM_PRIVILEGE cascade;
create table SUBSYSTEM_PRIVILEGE (
	ID serial primary key,
	CODE integer not null,
	ARG0 varchar(50),
	ARG1 varchar(50),
	ARG2 varchar(50),
	SUBSYSTEM_USER_REF integer not null references SUBSYSTEM_USER,
	USERTAB_REF integer not null references USERTAB
);

drop table if exists ACCOUNT cascade;
create table ACCOUNT (
	ID serial primary key,
	NAME varchar(50) not null,
	DESCR varchar(100),
	INITIAL_STATE integer not null default 0,
	USERTAB_OWNER_REF integer references USERTAB,
	SUBSYSTEM_REF integer not null references SUBSYSTEM
);

drop table if exists TRANS_KIND cascade;
create table TRANS_KIND (
	ID serial primary key,
	NAME varchar(50) not null,
	DESCR varchar(200)
);

drop table if exists TRANS_SUB_KIND cascade;
create table TRANS_SUB_KIND (
	ID serial primary key,
	NAME varchar(50) not null,
	DESCR varchar(200),
	CODE integer not null,
	KIND_REF integer not null references TRANS_KIND
);

drop table if exists TRANSACTIONTAB cascade;
create table TRANSACTIONTAB (
	ID serial primary key,
	DESCR varchar(200),
	EVENT_DATE timestamp not null,
	POSTING_DATE timestamp not null,
	IDENTITY_NUM varchar(50),
	TRANS_SUB_KIND_REF integer not null references TRANS_SUB_KIND,
	USERTAB_CREATOR_REF integer not null references USERTAB
);

drop table if exists ACCOUNT_FLOW cascade;
create table ACCOUNT_FLOW (
	ID serial primary key,
	FLOW integer not null,
	ACCOUNT_REF integer not null references ACCOUNT,
	TRANSACTIONTAB_REF integer not null references TRANSACTIONTAB
);

