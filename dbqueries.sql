create schema retail_bank_management;
use retail_bank_management;
create table customer(ws_ssn int unique not null,check(ws_ssn>=100000000 and ws_ssn<=999999999),
                      ws_cust_id int primary key auto_increment,
                      ws_status char(1) not null, check(ws_status='A' or ws_status='I'),
                      ws_msg varchar(255),
                      ws_last_update timestamp not null,
                      ws_name varchar(100) not null,
                      ws_adrs varchar(255) not null,
                      ws_city varchar(255) not null,
                      ws_state varchar(255) not null,
                      ws_age int not null,check(ws_age>=1 and ws_age<=999)
                      );
alter table customer auto_increment=100000000;
create table account(ws_acct_id int primary key auto_increment,
                     ws_cust_id int references customer(ws_cust_id) on delete cascade,
                     ws_acct_type char(1) not null,check(ws_acct_type='S' or ws_acct_type='C'),
                     ws_acct_balance int8 not null,check(ws_acct_balance>=0),
                     ws_acct_crdate date not null,
                     ws_status char(1) not null, check(ws_status='A' or ws_status='I'),
                     ws_msg varchar(255),
                     ws_last_update timestamp not null
                     );
alter table account auto_increment=100000000;
create table transactions(ws_trxn_id int primary key auto_increment,
                          ws_src_acct_id int references account(ws_acct_id) on delete set null,
                          ws_dest_acct_id int references account(ws_acct_id) on delete set null,
                          ws_type char(1) not null, check(ws_type='W' or ws_type='D' or ws_type='T'),
                          ws_amt int not null,check(ws_amt>0),
                          ws_trxn_ts timestamp not null
                          );
alter table transactions auto_increment=100000000;

create table userstore(ws_username varchar(50) primary key,
                       ws_password varchar(50) not null,
                       ws_last_login timestamp not null
                       );
insert into userstore values('user','user',current_timestamp());

                       
