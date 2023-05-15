drop table if exists active_token;
drop table if exists queue;
drop table if exists petition_queue;
drop table if exists petition;
drop table if exists users;
drop table if exists song;

create table song(
    id int AUTO_INCREMENT,
    son_img varchar(100) not null,
    son_spotify_id varchar(80) not null,
   	son_artist1 varchar(80) not null,
    son_artist2 varchar(80),
	son_album varchar(40) not null,
   	son_name varchar(80) not null,
    son_duration int not null,
   	son_status char not null,
   	constraint pk_song primary key (id),
   	constraint check_song_status check (son_status in ('w','b','p')),
    constraint unique_son_spotify_id unique (son_spotify_id)
);

create table users (
	id int AUTO_INCREMENT ,
    user_nick varchar(80) not null,
    user_email varchar(80) not null,
    user_password varchar(80) not null,
    user_tokens int default 0,
    constraint pk_users primary key (id),
    constraint unique_users_nick unique (user_nick),
    constraint unique_users_email unique (user_email)
);

create table petition(
	pet_song_id int,
    constraint pk_petition primary key (pet_song_id),
    foreign key fk_petition_song (pet_song_id) references song(id)
);

create table petition_queue(
	id int auto_increment,
	pq_song_id int,
    pq_users_id int,
    pq_moment_temp datetime not null,
    constraint pk_petition_queue primary key (id),
    constraint unique_petition_queue_song_users unique (pq_song_id,pq_users_id),
    foreign key fk_petition_queue_song (pq_song_id) references petition(pet_song_id),
    foreign key fk_petition_queue_users (pq_users_id) references users(id)
);

create table active_token(
	at_id int AUTO_INCREMENT,
    at_moment_temp datetime not null,
    at_cadena varchar(80) not null,
    at_value int not null,
    constraint pk_active_token primary key (at_id),
    constraint unique_at_cadena unique (at_cadena)
);

create table queue(
	id int AUTO_INCREMENT,
	que_song_id int,
    que_users_id int,
    que_moment_temp datetime not null,
   	constraint pk_queue primary key (id),
	constraint unique_queue_users_datetime unique (que_users_id,que_moment_temp),
    foreign key fk_queue_song (que_song_id) references song(id),
    foreign key fk_queue_users (que_users_id) references users(id)
);

insert into users values (null,'admin','admin@gmail.com', 'admin', 0);
insert into users values (null,'cferrer1','cferrer1@milaifontanals.org', 'admin', 0);
insert into users values (null,'mpaez','mpaez@milaifontanals.org', 'admin', 0);

insert into active_token values (null, sysdate(), 'akdgvjbalsdfhjblaskdjvasnñlgnkanba', 100);
insert into active_token values (null, sysdate(), 'kjbclabncasvjlasdnv,ahsdvlasdbvads', 50);
insert into active_token values (null, sysdate(), 'asdvasdalsdfhjblaskdjvasnñlgnkanba', 100);

insert into song values (null, 'https://i.scdn.co/image/ab67616d0000b27329ebee2b5fb008871fcd201a', '6XSqqQIy7Lm7SnwxS4NrGx', 'Young Miko', 'Feid', 'Single', 'Classy 101', 195, 'w');
insert into song values (null, 'https://i.scdn.co/image/ab67616d0000b2731e0950bcdb5495e2038e0d14', '5MxFWjuqQIsbNWbMdMdbli', 'Bad Bunny', 'Arcàngel', 'Single', 'La Jumpa', 236, 'b');
insert into song values (null, 'https://i.scdn.co/image/ab67616d0000b273ed132404686f567c8f793058', '54ELExv56KCAB4UP9cOCzC', 'Ñego Flow', 'Bad Bunny', 'Single', 'Gato de noche', 227, 'p');
insert into song values (null, 'https://i.scdn.co/image/ab67616d0000b2737cc7b0d6a82846cd8b158f99', '0CYTGMBYkwUxrj1MWDLrC5', 'Feid', null, 'Single', 'Chorrito Pa Las Ánimas', 168, 'w');
insert into song values (null, 'https://i.scdn.co/image/ab67616d0000b273d12510170b4c55664e96e9a5', '4DuUwzP4ALMqpquHU0ltAB', 'Jack Harlow', null, 'Single', 'Tyler Herro', 157, 'b');
insert into song values (null, 'https://i.scdn.co/image/ab67616d0000b273d12510170b4c55664e96e9a5', '3kUq4sBcmxhnOtNysZ9yrp', 'Feid', null, 'Single', 'Feliz Cumpleaños Ferxo', 156, 'p');

insert into petition values (3);
insert into petition values (6);

insert into petition_queue values (null,3,2,sysdate());
insert into petition_queue values (null,6,3,SYSDATE());

insert into queue values (null,1,2,sysdate());
insert into queue values (null,4,3,sysdate());
COMMIT;
