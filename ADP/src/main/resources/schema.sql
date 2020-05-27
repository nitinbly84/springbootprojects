DROP TABLE IF EXISTS TBL_EMPLOYEES;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS Trade;
  
CREATE TABLE TBL_EMPLOYEES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

create table user (
    id INT primary key,
    name varchar(150)
);

create table Trade (
	id int primary key,
    type varchar(250),
    user int,
    stockSymbol  varchar(250),
    stockQuantity int,
    stockPrice DECIMAL(10,2),
    tradeTimestamp Timestamp,
    foreign key(user) references user(id)
)