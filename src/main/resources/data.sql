-- DB

insert into "user" (username, password, nickname, activated) values ('admin', '$2a$10$LxmEYywAI880NqVtPWS0.O6oTPvOwM9cQhANTrZAiuFL0.XTPG.DK', 'admin', 1);
insert into "user" (username, password, nickname, activated) values ('user', '$2a$10$/uqJ56PG0h.2GNSH997hNejz4wCDQf3j7tNik/1KOtrXkJ1W3CVjW', 'user', 1);
-- 평문 비밀번호 : adminPWD / userPWD

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');