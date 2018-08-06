CREATE USER bookstore IDENTIFIED BY bookstore;
GRANT CONNECT TO bookstore;
GRANT UNLIMITED TABLESPACE TO bookstore;
GRANT CREATE TABLE TO bookstore;

CREATE TABLE book (
    book_id NUMBER(11) primary key,
    title VARCHAR2(128) not null,
    author VARCHAR2(45) not null,
    price NUMBER(11,2) not null
);