drop database if exists Workshop24;

CREATE database Workshop24;

use Workshop24;

CREATE TABLE `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `order_date` DATE NOT NULL,
  `customer_name` VARCHAR(128) NOT NULL,
  `ship_address` VARCHAR(128) NOT NULL,
  `notes` TEXT NULL,
  `tax` DECIMAL(2,2) NULL DEFAULT 0.05,
  
  constraint order_id PRIMARY KEY (`order_id`)
  );

CREATE TABLE `order_details` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product` VARCHAR(64) NOT NULL,
  `unit_price` DECIMAL(3,2) NOT NULL,
  `discount` DECIMAL(4,2) NOT NULL DEFAULT 1.0,
  `quantity` INT NOT NULL,
  `order_id` INT NOT NULL, 
  
  constraint order_details_id PRIMARY KEY (`id`),
  
  constraint fk_order_id foreign key(`order_id`)
        references orders(`order_id`)
  );

grant all privileges on orders.* to 'fred'@'localhost';
flush privileges;