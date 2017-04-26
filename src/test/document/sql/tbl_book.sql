DROP TABLE if exists tbl_book;

CREATE TABLE tbl_book
(
   
    id                            VARCHAR(32)                             NOT NULL,
   
    price                         VARCHAR(32)                                            NOT NULL,
   
    number                        VARCHAR(32)                                            NOT NULL,
   
    create_datetime               DATETIME                                               NOT NULL,
   
    create_user_login_name        VARCHAR(32)                                            NOT NULL,
 CONSTRAINT tbl_book_KEY PRIMARY KEY (id)
);

