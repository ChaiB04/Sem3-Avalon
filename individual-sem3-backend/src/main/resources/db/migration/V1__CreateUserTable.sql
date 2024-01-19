CREATE TABLE app_user
(
    id int NOT NULL AUTO_INCREMENT,
    email VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL,
    firstname VARCHAR(200) NOT NULL,
    lastname VARCHAR(200) NOT NULL,
    street VARCHAR(200),
    housenumber int,
    zipcode VARCHAR(50),
    country VARCHAR(50),
    city VARCHAR(50),
    phonenumber VARCHAR(50),
    role VARCHAR(20),
    picture LONGBLOB,

    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE google_user (
 id INT NOT NULL AUTO_INCREMENT,
 sub VARCHAR(255) NOT NULL,
 user_id INT NOT NULL,
 FOREIGN KEY (user_id) REFERENCES app_user(id),
 PRIMARY KEY (id),
 UNIQUE (sub)
);