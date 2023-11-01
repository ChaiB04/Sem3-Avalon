CREATE TABLE user_order
(
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    is_bundled BOOLEAN,
    date_of_purchase DATE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE order_items
(
    id int NOT NULL AUTO_INCREMENT,
    product_id int NOT NULL,
    order_id int NOT NULL,
    PRIMARY KEY (id)
);

