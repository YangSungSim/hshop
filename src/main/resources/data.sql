insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into account(kind, name, email, password, phone) values (0, '홍길동', 'test01@gmail.com', '$2a$10$OFerxHCJ4UejtXJL1632qe.Wd76anZ742RvzYxoAZcQPj8cK70Hd6', '01012345678');
insert into account(kind, name, email, password, phone) values (1, '김철수', 'test02@gmail.com', '$2a$10$OFerxHCJ4UejtXJL1632qe.Wd76anZ742RvzYxoAZcQPj8cK70Hd6', '01045320000');
insert into account(kind, name, email, password, phone) values (1, '김영희', 'test03@gmail.com', '$2a$10$OFerxHCJ4UejtXJL1632qe.Wd76anZ742RvzYxoAZcQPj8cK70Hd6', '01078555522');

insert into account_authorities(account_id, authority_name) values (1, 'ROLE_ADMIN');
insert into account_authorities(account_id, authority_name) values (1, 'ROLE_USER');
insert into account_authorities(account_id, authority_name) values (2, 'ROLE_USER');
insert into account_authorities(account_id, authority_name) values (3, 'ROLE_USER');


insert into place(name, address, open_at, close_at, reserve, reserve_max) values ('testPlace01', 'testPlace01address', '11:00:00', '20:00:00', true, 20);
insert into place(name, address, open_at, close_at, reserve, reserve_max) values ('testPlace02', 'testPlace02address', '10:00:00', '22:00:00', false, 10);
insert into place(name, address, open_at, close_at, reserve, reserve_max) values ('testPlace03', 'testPlace03address', '13:00:00', '23:00:00', true, 200);

insert into reservation(place_id, account_id, reserve_at) values (1, 1, now());
insert into reservation(place_id, account_id, reserve_at) values (1, 2, now());

insert into place_reservation(place_id, reservation_id) values (1, 1);
insert into place_reservation(place_id, reservation_id) values (1, 2);

insert into style(hair_len, last_pc, hair_line, plenty, texture, health, color, photo, shoulder, neck, comment, account_id) values (1, 1, 1, 1, 1, 1, 'Y', 1, 1, 1, 'comment', 1);
insert into style(hair_len, last_pc, hair_line, plenty, texture, health, color, photo, shoulder, neck, comment, account_id) values (1, 1, 1, 1, 1, 1, 'Y', 1, 1, 1, 'comment', 2);
