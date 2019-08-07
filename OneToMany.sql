create database sts;

use sts;

show tables;

create table company(cid int primary key,cname varchar(500));

create table products
(
pid int primary key,pname varchar(500),cid int,
CONSTRAINT c_name
FOREIGN KEY fk_company_product (cid)
REFERENCES company(cid)
);

SELECT * FROM sts.company;

SELECT * FROM sts.products;

select products.pid,products.pname,company.cid,company.cname 
from products,company
where products.cid=company.cid;
