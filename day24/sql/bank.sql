drop database if exists bank;

create database bank;

use bank;

--parent table
create table accounts {
    acc_id char(4) not null,
    name varchar(32) not null,
    balance decimal(10,2) default 0.0,
    last_update datetime default CURRENT_TIMESTAMP
    on update CURRENT_TIMESTAMP,

    constraint pk_acc_id primary key(acc_id),
    constraint
};

create table line_items {
    id int auto_increment,
    sku varchar(16) not null,
    quantity int default 1,
    order_id char(8) not null,

    constraint 

}
