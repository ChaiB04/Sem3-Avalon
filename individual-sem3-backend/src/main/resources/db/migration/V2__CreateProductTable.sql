CREATE TABLE product (
     id int NOT NULL AUTO_INCREMENT,
     name VARCHAR(50) NOT NULL,
     price DOUBLE NOT NULL,
     description VARCHAR(200),
     color VARCHAR(50) NOT NULL,
     picture LONGBLOB,
     PRIMARY KEY (id)
);
