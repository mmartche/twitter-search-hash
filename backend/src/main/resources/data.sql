INSERT INTO USER(name, email, password) VALUES('Alunommmmm', 'aluno@email.com', '$2a$10$6TPdP6zGLo8tgP0KKuNeDu8PQc4RUoGNyZjf9BN4oekxVqahWtBui');
INSERT INTO USER(name, email, password) VALUES('MODERERER', 'moderador@email.com', '$2a$10$6TPdP6zGLo8tgP0KKuNeDu8PQc4RUoGNyZjf9BN4oekxVqahWtBui');

INSERT INTO PERFIL(id, name) VALUES (1, 'ROLE_ALUNO');
INSERT INTO PERFIL(id, name) VALUES (2, 'ROLE_MODERADOR');

INSERT INTO USER_PERFIS(user_id, perfis_id) VALUES (1, 1);
INSERT INTO USER_PERFIS(user_id, perfis_id) VALUES (2, 2);


INSERT INTO TOPIC(title, mesage, date_created, status, author_id) VALUES('hash 1', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1);
INSERT INTO TOPIC(title, mesage, date_created, status, author_id) VALUES('hash 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1);
INSERT INTO TOPIC(title, mesage, date_created, status, author_id) VALUES('hash3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1);