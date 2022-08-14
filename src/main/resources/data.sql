insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into account(kind, name, email, password, phone) values (0, '홍길동', 'test01@gmail.com', '$2a$10$OFerxHCJ4UejtXJL1632qe.Wd76anZ742RvzYxoAZcQPj8cK70Hd6', '01012345678');
insert into account(kind, name, email, password, phone) values (1, '김철수', 'test02@gmail.com', '$2a$10$OFerxHCJ4UejtXJL1632qe.Wd76anZ742RvzYxoAZcQPj8cK70Hd6', '01045320000');
insert into account(kind, name, email, password, phone) values (1, '김영희', 'test03@gmail.com', '$2a$10$OFerxHCJ4UejtXJL1632qe.Wd76anZ742RvzYxoAZcQPj8cK70Hd6', '01078555522');

insert into account_authorities(account_id, authority_name) values (1, 'ROLE_ADMIN');
insert into account_authorities(account_id, authority_name) values (1, 'ROLE_USER');
insert into account_authorities(account_id, authority_name) values (2, 'ROLE_USER');
insert into account_authorities(account_id, authority_name) values (3, 'ROLE_USER');