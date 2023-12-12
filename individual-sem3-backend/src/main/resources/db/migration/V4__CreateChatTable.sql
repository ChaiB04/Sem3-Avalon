CREATE TABLE chat (
  id int NOT NULL AUTO_INCREMENT,
  user1 int NOT NULL,
  user2 int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user1) REFERENCES app_user(id),
  FOREIGN KEY (user2) REFERENCES app_user(id)
);

CREATE TABLE chat_messages(
    id int NOT NULL AUTO_INCREMENT,
    chat_id int NOT NULL,
    sender int NOT NULL,
    message VARCHAR(500) NOT NULL,
    date DATE NOT NULL,
    seen boolean NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (chat_id) REFERENCES chat(id),
    FOREIGN KEY (sender) REFERENCES app_user(id)
);

