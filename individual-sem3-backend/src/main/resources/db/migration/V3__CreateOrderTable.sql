-- CREATE TABLE user_order
CREATE TABLE user_order (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    is_bundled BOOLEAN,
    date_of_purchase DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);


CREATE TABLE user_order_products (
     order_entity_id int NOT NULL,
     products_id int NOT NULL,
     PRIMARY KEY (order_entity_id, products_id),
     FOREIGN KEY (order_entity_id) REFERENCES user_order(id),
     FOREIGN KEY (products_id) REFERENCES product(id)
);
