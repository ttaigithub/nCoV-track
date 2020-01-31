drop table if exists location;

drop table if exists path_location;

drop table if exists patient;

/*==============================================================*/
/* Table: path_location                                         */
/*==============================================================*/
create table path_location
(
    record_id            bigint unsigned not null comment '主键id',
    patient_id           bigint not null default 0 comment '患者id',
    appear_time          timestamp not null default CURRENT_TIMESTAMP comment '出没时间',
    departure            varchar(500) not null default '' comment '出发地',
    destination          varchar(500) not null default '' comment '目的地',
    departure_location   varchar(500) not null default '' comment '出发地经纬度',
    destination_location varchar(500) not null default '' comment '目的地经纬度',
    is_delete            bool not null default 0 comment '删除标识',
    gmt_create           timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified         timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    primary key (record_id)
);

alter table path_location comment '患者路径表';

/*==============================================================*/
/* Table: patient                                               */
/*==============================================================*/
create table patient
(
    record_id            bigint unsigned not null auto_increment comment '主键id',
    patient_name         varchar(20) not null default '' comment '姓名',
    sex                  bit not null default 0 comment '性别',
    age                  int not null default 0 comment '年龄',
    confirmed_time       timestamp not null default CURRENT_TIMESTAMP comment '确诊日期',
    state                int not null default 0 comment '状态',
    is_delete            bool not null default 0 comment '删除标识',
    gmt_create           timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    gmt_modified         timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
    primary key (record_id)
);

alter table patient comment '患者';

alter table path_location add constraint FK_Reference_1 foreign key (patient_id)
    references patient (record_id) on delete restrict on update restrict;

