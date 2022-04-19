INSERT INTO USR (USERNAME, EMAIL, PASSWORD)
VALUES ('admin', 'admin@mail.com', '$2a$10$0c/rq67y/kIRveIlxGm8Lu7S/KR6BXhiwkPdU/xd.TwURmItZ3roy'),
       ('user', 'user@mail.com', '$2a$10$ImnbPOkatMErvYYz2rNP9eUlePVd5794oh7/rZ72nVP8eajcc6vQK');

INSERT INTO USER_ROLE(USER_EMAIL, ROLES)
VALUES ('admin@mail.com', 'ROLE_USER'),
       ('admin@mail.com', 'ROLE_ADMIN'),
       ('user@mail.com', 'ROLE_USER');

INSERT INTO GENRES (NAME)
VALUES ('Детективы'),
       ('Фантастика'),
       ('Фэнтези'),
       ('Приключения'),
       ('Легкая проза'),
       ('Классика жанра');

INSERT INTO AUTHORS (FIRST_NAME, LAST_NAME)
VALUES ('Елена', 'Саулите'),
       ('Майк', 'Омер'),
       ('Сергей', 'Лукьяненко'),
       ('Стивен', 'Кинг'),
       ('Александр', 'Дюма'),
       ('Елена', 'Колина'),
       ('Юлия', 'Ефимова');

INSERT INTO BOOKS (TITLE, PAGES, AUTHOR_ID, GENRE_ID)
VALUES ('Швейцарский счет', 240, 1, 1),
       ('Как ты умрешь', 310, 2, 1),
       ('Три дня Индиго', 350, 3, 2),
       ('Позже', 240, 4, 3),
       ('Граф Монте-Кристо', 2292, 5, 4),
       ('Дорогая Дуся', 190, 6, 5),
       ('Потерянное наследство тамплиера', 230, 7, 6);
