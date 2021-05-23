create database commoditymanagement;

use commoditymanagement;

insert into role(code, name) values
("ROLE_ADMIN","Quản lý"),
("ROLE_USER","Nhân viên");

insert into user(fullname,email,password,address,gender,phonenumber,status,roleid) values
("Huỳnh Bảo Lộc","admin@gmail.com","$10$SmWij6mwA.eEwokxYVpFhuzceR6B1KMptHOOQdYo6g8M2Nxul6EUa","TP Tuy Hòa",0,"0393425933",0,1),
("Nguyễn Văn A","nguyenvana@gmail.com","$10$SmWij6mwA.eEwokxYVpFhuzceR6B1KMptHOOQdYo6g8M2Nxul6EUa","TP Tuy Hòa",0,"0393425933",0,2);