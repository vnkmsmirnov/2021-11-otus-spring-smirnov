INSERT INTO USR (USERNAME, EMAIL, PASSWORD)
VALUES ('admin', 'admin@mail.com', '$2a$10$0c/rq67y/kIRveIlxGm8Lu7S/KR6BXhiwkPdU/xd.TwURmItZ3roy');

INSERT INTO USER_ROLE(USER_ID, ROLES)
VALUES ((SELECT ID FROM USR
            WHERE EMAIL = 'admin@mail.com'), 'USER'),
        ((SELECT ID FROM USR
            WHERE EMAIL = 'admin@mail.com'), 'ADMIN');

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
VALUES ('Швейцарский счет',
        240,
        (SELECT ID FROM AUTHORS A WHERE A.FIRST_NAME = 'Елена' AND A.LAST_NAME = 'Саулите'),
        (SELECT ID FROM GENRES G WHERE G.NAME = 'Детективы')),
       ('Как ты умрешь',
        310,
        (SELECT ID FROM AUTHORS A WHERE A.FIRST_NAME = 'Майк' AND A.LAST_NAME = 'Омер'),
        (SELECT ID FROM GENRES G WHERE G.NAME = 'Детективы')),
       ('Три дня Индиго',
        350,
        (SELECT ID FROM AUTHORS A WHERE A.FIRST_NAME = 'Сергей' AND A.LAST_NAME = 'Лукьяненко'),
        (SELECT ID FROM GENRES G WHERE G.NAME = 'Фантастика')),
       ('Позже',
        240,
        (SELECT ID FROM AUTHORS A WHERE A.FIRST_NAME = 'Стивен' AND A.LAST_NAME = 'Кинг'),
        (SELECT ID FROM GENRES G WHERE G.NAME = 'Фэнтези')),
       ('Граф Монте-Кристо',
        2292,
        (SELECT ID FROM AUTHORS A WHERE A.FIRST_NAME = 'Александр' AND A.LAST_NAME = 'Дюма'),
        (SELECT ID FROM GENRES G WHERE G.NAME = 'Приключения')),
       ('Дорогая Дуся',
        190,
        (SELECT ID FROM AUTHORS A WHERE A.FIRST_NAME = 'Елена' AND A.LAST_NAME = 'Колина'),
        (SELECT ID FROM GENRES G WHERE G.NAME = 'Легкая проза')),
       ('Потерянное наследство тамплиера',
        230,
        (SELECT ID FROM AUTHORS A WHERE A.FIRST_NAME = 'Юлия' AND A.LAST_NAME = 'Ефимова'),
        (SELECT ID FROM GENRES G WHERE G.NAME = 'Классика жанра'));