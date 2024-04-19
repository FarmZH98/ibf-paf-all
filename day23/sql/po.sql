drop database if exists acme;

create database acme;

use acme;

--parent table
create table purchase_orders {
    order_id char(8) not null,
    name varchar(128) not null,
    order_date datetime default CURRENT_TIMESTAMP,

    constraint pk_order_id primary key(order_id)
};

create table line_items {
    id int auto_increment,
    sku varchar(16) not null,
    quantity int default 1,
    order_id char(8) not null,

    constraint 

}
