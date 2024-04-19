SQL todos

-- Login in as the root user
mysql -u root -p

-- Next, key in the password for root user

-- See all databases
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| acme               |
| bgg                |
| information_schema |
| mysql              |
| northwind          |
| performance_schema |
| rsvp               |
| sakila             |
| sys                |
+--------------------+

-- See all users
mysql> USE mysql
mysql> SELECT User, Host FROM user;
+------------------+-----------+
| User             | Host      |
+------------------+-----------+
| jon              | %         |
| mysql.infoschema | localhost |
| mysql.session    | localhost |
| mysql.sys        | localhost |
| root             | localhost |
| rsvp             | localhost |
+------------------+-----------+

-- Delete a user e.g.
DROP USER 'jon'@'%';

-- See all users (again, just for checking if user has been dropped)
mysql> SELECT User, Host FROM user;
+------------------+-----------+
| User             | Host      |
+------------------+-----------+
| mysql.infoschema | localhost |
| mysql.session    | localhost |
| mysql.sys        | localhost |
| root             | localhost |
| rsvp             | localhost |
+------------------+-----------+

-- Drop a database e.g.
mysql> DROP DATABASE sakila;

-- See all databases (again, just for checking if database has been dropped)
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| acme               |
| bgg                |
| information_schema |
| mysql              |
| northwind          |
| performance_schema |
| rsvp               |
| sys                |
+--------------------+

-------------------------------------------------------------------------------------

-- Typically, when you are given a new database:
		-- Make sure you are in the folder where your sql file(s) that you want to load 
		-- are. This is so that when you 'source' i.e. load the sql file(s), you don't need 
		-- a path.
		-- Next, sign in as the root user first, and then
		-- You will be prompted to key in your mysql root password

-- Load all the necessary databases into the root user first e.g.
source sakila-schema.sql -- load this first
source sakila-data.sql

-- See all databases (again, just for checking if database has been added)
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| acme               |
| bgg                |
| information_schema |
| mysql              |
| northwind          |
| performance_schema |
| rsvp               |
| sakila             |
| sys                |
+--------------------+

-- Check if data in sakila is loaded properly
mysql> use sakila;
mysql> show tables;
+----------------------------+
| Tables_in_sakila           |
+----------------------------+
| actor                      |
| actor_info                 |
| address                    |
| category                   |
| city                       |
| country                    |
| customer                   |
| customer_list              |
| film                       |
| film_actor                 |
| film_category              |
| film_list                  |
| film_text                  |
| inventory                  |
| language                   |
| nicer_but_slower_film_list |
| payment                    |
| rental                     |
| sales_by_film_category     |
| sales_by_store             |
| staff                      |
| staff_list                 |
| store                      |
+----------------------------+

mysql> select * from actor limit 10; --or select from any table you want
+----------+-------------+--------------+---------------------+
| actor_id | first_name  | last_name    | last_update         |
+----------+-------------+--------------+---------------------+
|        1 | PENELOPE    | GUINESS      | 2006-02-15 04:34:33 |
|        2 | NICK        | WAHLBERG     | 2006-02-15 04:34:33 |
|        3 | ED          | CHASE        | 2006-02-15 04:34:33 |
|        4 | JENNIFER    | DAVIS        | 2006-02-15 04:34:33 |
|        5 | JOHNNY      | LOLLOBRIGIDA | 2006-02-15 04:34:33 |
|        6 | BETTE       | NICHOLSON    | 2006-02-15 04:34:33 |
|        7 | GRACE       | MOSTEL       | 2006-02-15 04:34:33 |
|        8 | MATTHEW     | JOHANSSON    | 2006-02-15 04:34:33 |
|        9 | JOE         | SWANK        | 2006-02-15 04:34:33 |
|       10 | CHRISTIAN   | GABLE        | 2006-02-15 04:34:33 |
+-------------------------------------------------------------+


-- Next, never, never use the root user for database manipulation so... --

-- Create a new user e.g.
-- 'host' can be 'localhost', can be '%', depending on your needs
CREATE USER 'abcde'@'host' IDENTIFIED BY 'abcde'; 

-- Grant privileges to the new user e.g.
GRANT ALL PRIVILEGES ON sakila.* TO 'abcde'@'%'; 

-- Refresh user's permissions
FLUSH PRIVILEGES;

-- You can go back and check the list of users to verify a new user has been added --
mysql> use mysql
mysql> SELECT User, Host FROM user;
+------------------+-----------+
| User             | Host      |
+------------------+-----------+
| abcde            | %         |
| mysql.infoschema | localhost |
| mysql.session    | localhost |
| mysql.sys        | localhost |
| root             | localhost |
| rsvp             | localhost |
+------------------+-----------+

-- Next, exit the root user 

-- Sign in as the new user e.g.
mysql -u abcde -p

-- Next, key in the password for the user called 'abcde' --

-- View all the databases to verify the 'sakila' database has been addeds
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| performance_schema |
| sakila             |
+--------------------+

-- Use the database that you want e.g.
use sakila;

-- View all tables inside the selected database
show tables;

-- Afterwards you can manipulate the tables/data as you want using workbench or cli