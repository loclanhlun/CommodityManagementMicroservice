create database commoditymanagement;
use commoditymanagement;

insert into role(code,name) values
("ROLE_ADMIN","Quản lý"),
("ROLE_USER","Nhân viên");

insert into user(address,email,fullname,gender,password,phonenumber,status,roleid) values
("TP Tuy Hòa","admin@gmail.com","Huỳnh Bảo Lộc",0,"$2a$10$SmWij6mwA.eEwokxYVpFhuzceR6B1KMptHOOQdYo6g8M2Nxul6EUa","0393425933",0,1),
("TP Tuy Hòa","nguyenvana@gmail.com","Nguyễn Văn A",0,"$2a$10$SmWij6mwA.eEwokxYVpFhuzceR6B1KMptHOOQdYo6g8M2Nxul6EUa","0393425933",0,2);

alter table importbill modify column totalprice decimal(19,2) default 0;

alter table exportbill modify column totalprice decimal(19,2) default 0;

alter table commoditywarehouse modify column unitprice decimal(19,2) default 0;

