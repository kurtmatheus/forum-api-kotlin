INSERT INTO usuario (id, nome, email, password) VALUES (2, 'admin', 'admin@email.com', '$2y$12$rnZ4zWxhPv.7iSOhBKkGqu2PVsJf./W78TScYJd.EWdaEeWhnhQW.');
INSERT INTO role (id, nome) VALUES (2, 'ADMIN');
INSERT INTO usuario_role (id, usuario_id, role_id) VALUES (2, 2, 2);