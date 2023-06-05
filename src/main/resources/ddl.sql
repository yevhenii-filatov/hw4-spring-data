DROP TABLE IF EXISTS person;
CREATE TABLE person
(
    id    int PRIMARY KEY AUTO_INCREMENT,
    name  varchar(100) NOT NULL,
    age   int,
    email varchar(100)
);

INSERT INTO person (name, age, email)
VALUES ('Taras', 23, 'rar@gmail.com');
INSERT INTO person (name, age, email)
VALUES ('Grigoriy', 33, 'grigoriy@gmail.com');
INSERT INTO person (name, age, email)
VALUES ('Paraska', 77, 'babka@gmail.com');
