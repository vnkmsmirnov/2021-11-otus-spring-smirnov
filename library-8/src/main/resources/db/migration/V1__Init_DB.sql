DROP TABLE IF EXISTS USR, USER_ROLE, AUTHORS, GENRES, COMMENTS, BOOKS;

CREATE TABLE USR
(
    EMAIL VARCHAR(255) UNIQUE NOT NULL,
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(1024) NOT NULL
);

CREATE TABLE USER_ROLE
(
    USER_EMAIL VARCHAR(255) NOT NULL,
    ROLES VARCHAR(255) NOT NULL
);

CREATE TABLE GENRES
(
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE AUTHORS
(
    ID BIGSERIAL PRIMARY KEY,
    FIRST_NAME VARCHAR(50),
    LAST_NAME VARCHAR(50)
);

CREATE TABLE COMMENTS
(
    ID BIGSERIAL PRIMARY KEY,
    TEXT VARCHAR(1024),
    BOOK_ID BIGINT
);

CREATE TABLE BOOKS
(
    ID BIGSERIAL PRIMARY KEY,
    TITLE VARCHAR(255) UNIQUE NOT NULL,
    PAGES INT NOT NULL,
    AUTHOR_ID BIGINT,
    GENRE_ID BIGINT
);

ALTER TABLE USER_ROLE
    ADD FOREIGN KEY (USER_EMAIL)
    REFERENCES USR(EMAIL);

ALTER TABLE AUTHORS
    ADD UNIQUE (FIRST_NAME, LAST_NAME);

ALTER TABLE BOOKS
    ADD FOREIGN KEY (AUTHOR_ID)
    REFERENCES AUTHORS(ID);

ALTER TABLE BOOKS
    ADD FOREIGN KEY (GENRE_ID)
        REFERENCES GENRES(ID);

ALTER TABLE COMMENTS
    ADD FOREIGN KEY (BOOK_ID)
        REFERENCES BOOKS(ID)
        ON DELETE CASCADE;
