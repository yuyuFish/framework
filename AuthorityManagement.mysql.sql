/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/1/11 16:35:56                           */
/*==============================================================*/


drop table if exists AM_GROUP;

drop table if exists AM_PERMISSION;

drop table if exists AM_REL_GROUP_PERMISSION;

drop table if exists AM_REL_GROUP_ROLE;

drop table if exists AM_REL_ROLE_PERMISSION;

drop table if exists AM_ROLE;

drop table if exists SYS_MODULE;

drop table if exists SYS_PRODUCT_LINE;

drop table if exists SYS_RESOURCES;

/*==============================================================*/
/* Table: AM_GROUP                                              */
/*==============================================================*/
create table AM_GROUP
(
   GROUP_ID             varchar(128) not null,
   PRODUCT_ID           varchar(128),
   PARENT_ID      varchar(128),
   GROUP_NAME           varchar(256),
   GROUP_INFO           varchar(512),
   GROUP_CODE           varchar(1024),
   IS_INHERIT           int,
   LEFT_VALUE           bigint,
   RIGHT_VALUE          bigint,
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (GROUP_ID)
);

/*==============================================================*/
/* Table: AM_PERMISSION                                         */
/*==============================================================*/
create table AM_PERMISSION
(
   PERMISSION_ID        varchar(128) not null,
   PRODUCT_ID           varchar(128),
   PERMISSION_NAME      varchar(256),
   PERMISSION_INFO      varchar(512),
   PERMISSION_URL       varchar(2048) comment '权限具体操作(web URL)，多个操作用分号(;)隔开',
   PERMISSION_CODE      varchar(1024),
   OPERATE_IP           varchar(128),
   OPERATE_TYPE         varchar(256),
   REQUEST_TYPE         varchar(256),
   REQUEST_HEADERS      varchar(1024),
   REQUEST_PARAMETERS   varchar(1024),
   LOGIC_TYPE           varchar(128) comment 'include or exclude',
   PERMISSION_CLASS_METHOD varchar(1024) comment '权限可以操作的类和方法(可与PERMISSION_OPERATE在不同环境配合或选择性使用)',
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (PERMISSION_ID)
);

/*==============================================================*/
/* Table: AM_REL_GROUP_PERMISSION                               */
/*==============================================================*/
create table AM_REL_GROUP_PERMISSION
(
   REL_ID               varchar(128) not null,
   GROUP_ID             varchar(128),
   PERMISSION_ID        varchar(128),
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (REL_ID)
);

/*==============================================================*/
/* Table: AM_REL_GROUP_ROLE                                     */
/*==============================================================*/
create table AM_REL_GROUP_ROLE
(
   REL_ID               varchar(128) not null,
   GROUP_ID             varchar(128),
   ROLE_ID              varchar(128),
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (REL_ID)
);

/*==============================================================*/
/* Table: AM_REL_ROLE_PERMISSION                                */
/*==============================================================*/
create table AM_REL_ROLE_PERMISSION
(
   REL_ID               varchar(128) not null,
   ROLE_ID              varchar(128),
   PERMISSION_ID        varchar(128),
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (REL_ID)
);

/*==============================================================*/
/* Table: AM_ROLE                                               */
/*==============================================================*/
create table AM_ROLE
(
   ROLE_ID              varchar(128) not null,
   PRODUCT_ID           varchar(128),
   PARENT_ID       varchar(128),
   ROLE_NAME            varchar(256),
   ROLE_INFO            varchar(512),
   ROLE_CODE            varchar(1024),
   IS_INHERIT           int,
   LEFT_VALUE           bigint,
   RIGHT_VALUE          bigint,
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (ROLE_ID)
);

/*==============================================================*/
/* Table: SYS_MODULE                                            */
/*==============================================================*/
create table SYS_MODULE
(
   MODULE_ID            varchar(128) not null,
   PRODUCT_ID           varchar(128),
   PARENT_ID     varchar(128),
   MODULE_NAME          varchar(256),
   MODULE_INFO          varchar(512),
   MODULE_CODE          varchar(128),
   MODULE_TYPE          varchar(32),
   AREA_ID              varchar(128),
   SORT                 int,
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (MODULE_ID)
);

/*==============================================================*/
/* Table: SYS_PRODUCT_LINE                                      */
/*==============================================================*/
create table SYS_PRODUCT_LINE
(
   PRODUCT_ID           varchar(128) not null,
   PARENT_ID    varchar(128),
   PRODUCT_NAME         varchar(256),
   PRODUCT_INFO         varchar(512),
   PRODUCT_PROTOCOL     varchar(128),
   PRODUCT_HOSTNAME     varchar(256),
   PRODUCT_IP           varchar(32),
   PRODUCT_PORT         int,
   PRODUCT_CODE         varchar(128),
   LEFT_VALUE           bigint,
   RIGHT_VALUE          bigint,
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (PRODUCT_ID)
);

/*==============================================================*/
/* Table: SYS_RESOURCES                                         */
/*==============================================================*/
create table SYS_RESOURCES
(
   RESOURCES_ID         varchar(128) not null,
   PRODUCT_ID           varchar(128),
   PARENT_ID  varchar(128),
   RESOURCES_NAME       varchar(256),
   RESOURCES_INFO       varchar(512),
   RESOURCES_CODE       varchar(128),
   RESOURCES_TYPE       int,
   REQUEST_URL          varchar(1024),
   AREA_ID              varchar(128),
   LEFT_VALUE           bigint,
   RIGHT_VALUE          bigint,
   SORT                 bigint,
   CREATE_TIME          datetime,
   EDIT_TIME            datetime,
   DELETE_TIME          datetime,
   DATA_STATE           varchar(32),
   primary key (RESOURCES_ID)
);

