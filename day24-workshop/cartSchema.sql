drop database if exists orders;

create database orders;

use orders;

-- main table
create table order_info (
    id INT NOT NULL AUTO_INCREMENT,
   name varchar(32) not null,
   email varchar(128) not null,
   delivery_date date null,
   rush tinyint(1),
   comments text,

   constraint order_id primary key(id)
);

-- table 2 
create table order_product_info (
    id int not null AUTO_INCREMENT,
    item varchar(32) not null,
    quantity int not null,
    order_id int,

    constraint order_product_id primary key(id),

    constraint fk_order_id foreign key(order_id)
        references order_info(id)
);


grant all privileges on orders.* to 'fred'@'localhost';
flush privileges;


-- comments: "test"
-- deliveryDate: 1713312000000
-- email: "fred@gmail.com"
-- items: [{item: "goggles", quantity: 1}]
-- name: "fred"
-- rush: false