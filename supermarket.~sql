create table users
(
userid number not null primary key, --用户编号
uname nvarchar2(20), --用户名
upwd nvarchar2(20),--密码
usex nvarchar2(2),--性别
ubirth date,  --生日
utel nvarchar2(20),--电话
uadd nvarchar2(50),--地址
utype number(2)  --1 管理员 2 经理 3 普通用户
);

grant create view to cxp;
update users set upwd=999 where  uname='cxp' and upwd='123';

insert into users values(seq_uid.nextval,'qazmlp4610@126.com','qaz4610','男','01-4月-1994','18720987001','文三西路4号',1);
insert into users values(seq_uid.nextval,'cxp','123','男','01-6月-1999','18720987005','康乐新村238号',1);
insert into users values(seq_uid.nextval,'hy','hy123','女','21-5月-1991','18720987003','康乐新村258号',3);
insert into users values(seq_uid.nextval,'sy','sy123','男','01-6月-1999','18720987005','枫林大道59号',2);
insert into users values(seq_uid.nextval,'za','123','女','19-8月-1994','13260158329','乐业路18号',1);

--创建视图
create or replace  view users_view 
as
select userid,uname,usex,floor((sysdate-ubirth)/365) age,utel,decode(utype,1,'管理员',2,'经理',3,'普通用户') job from users;
select* from users order by userid;
select* from users_view order by userid;

select userid,uname,usex,to_char(ubirth,'YYYY-MM-DD'),utel,uadd,utype from users where uname like '%12%';

create table suppliers
(
sid number primary key,--供应商编号
sname nvarchar2(30),--供应商名称
scontacts nvarchar2(20),--联系人
stel varchar2(20),--联系电话
sadd nvarchar2(50),--联系地址
sfax varchar2(20),--传真
sdesc nvarchar2(50),--描述
sdate date --创建提供商的日期
);


insert into suppliers values(seq_sid.nextval,'宝洁','丽丝','13934987389','北京东城区','132321','世界五百强','12-5月-2012');
insert into suppliers values(seq_sid.nextval,'阿里妈妈','赵四','13845879487','珠海拱北区','112312','世界五千强','1-2月-2002');
insert into suppliers values(seq_sid.nextval,'黄氏企业','黄勇','13934987389','华盛顿东城区','423221','世界五强','1-9月-2066');

select* from suppliers;
select* from suppliers_view;
create or replace  view suppliers_view 
as
select sid,sname,scontacts,stel,sfax,sdate from suppliers order by sid;

select sid,sname,scontacts,stel,sfax,sdesc from suppliers;

update suppliers set sname='亨得利',scontacts='安娜',stel='13988484433' where sid=1007;

--商品表
create table goods
(
gid number primary key,--商品编号
gname nvarchar2(20),--商品名称
gprice number(10,2),--单价
gunit nvarchar2(10),--单位
sid number references suppliers(sid),--供应商编号
gstock number--库存
);


insert into goods values(seq_gid.nextval,'薯片',5，'每包',1003,999);
insert into goods values(seq_gid.nextval,'瓜子',8，'每包',1001,879);
insert into goods values(seq_gid.nextval,'电脑',5999，'每台',1002,2009);
insert into goods values(seq_gid.nextval,'矿泉水',2，'每瓶',1002,1999);
insert into goods values(seq_gid.nextval,'电脑',4999，'每台',1001,1009);

insert into goods values(seq_gid.nextval,'薯片',5.2，'每包',1002,999);

select* from goods;
select* from goods_view;

create or replace  view goods_view 
as
select g.gid,g.gname,g.gprice,s.stel,g.gunit from goods g,suppliers s
where g.sid=s.sid
order by gid;

--订单表
create table orders
(
oid number primary key,--订单编号
gid number references goods(gid), --商品编号
onum number not null, --商品数量
odate date, --订单生成时间
opaid number  --0 未付款 1 已付款
);


insert into orders values(seq_oid.nextval,1002,2,'3-2月-2010',1);
insert into orders values(seq_oid.nextval,1003,32,'3-2月-2011',0);
insert into orders values(seq_oid.nextval,1004,22,'13-9月-2009',0);
insert into orders values(seq_oid.nextval,1005,32,'23-6月-2012',1);


select * from orders order by oid;
select* from orders_view;
select* from orders_detail where oid=1003;

--创建视图
create or replace  view orders_view 
as
select o.oid,g.gname,s.sname,o.onum*g.gprice costs,o.odate,decode(o.opaid,0,'未付款',1,'已付款') paid from orders o,goods g,suppliers s 
where o.gid=g.gid and g.sid=s.sid
order by o.oid;

create or replace  view orders_detail 
as
select o.oid,g.gname,g.gunit,o.onum, o.onum*g.gprice costs,s.sname,decode(o.opaid,0,'未付款','已付款') paid from orders o,goods g,suppliers s
where o.gid=g.gid and g.sid=s.sid
order by o.oid;



select g.gid,g.gname,s.sname from goods g,suppliers s where g.sid=s.sid and g.gname='电脑'and s.sname='阿里妈妈';
select s.sid,s.sname,g.gname from goods g,suppliers s where g.sid=s.sid and g.gname=(select  gname from goods where gid=(select gid from orders where oid=1003));
select s.sid,s.sname from goods g,suppliers s where g.sid=s.sid and g.gname=(select  gname from goods where gid=1005);

update orders set gid=1003,onum=2,opaid=0  where oid=1001;


--创建序列
create sequence seq_uid start with 1000 nocache;
create sequence seq_sid start with 1000 nocache;
create sequence seq_gid start with 1000 nocache;
create sequence seq_oid start with 1000 nocache;

select seq_uid.nextval from dual;

commit;
----------！删除
drop table users;
drop table orders;
drop table goods;
drop table suppliers;

drop sequence seq_uid;
drop sequence seq_sid;
drop sequence seq_gid;
drop sequence seq_oid;
