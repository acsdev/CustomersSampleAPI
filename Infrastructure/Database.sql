create database db_company;

use db_company;

create table tb_customer (
  id  mediumint not null auto_increment,
  nr_number bigint not null unique,
  ds_usename varchar(10) not null unique,
  ds_password varchar(200) not null,
  ds_name varchar(200) not null,
  dt_birth date not null,
  ds_email varchar(200) not null,
  primary key (id)
);