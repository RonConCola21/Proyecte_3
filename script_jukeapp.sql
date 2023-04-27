drop table if exists active_token;
drop table if exists queue;
drop table if exists petition_queue;
drop table if exists petition;
drop table if exists users;
drop table if exists song;

create table song(
    son_id int AUTO_INCREMENT ,
    son_img varchar(100) not null,
    son_spotify_id varchar(80) not null,
   	son_artist varchar(80) not null,
   	son_name varchar(80) not null,
    son_duration int not null,
   	son_status char not null,
   	constraint pk_song primary key (son_id),
   	constraint check_song_status check (son_status in ('w','b','p')),
    constraint unique_son_spotify_id unique (son_spotify_id)
);

create table users (
	user_id int AUTO_INCREMENT ,
    user_nick varchar(80) not null,
    user_email varchar(80) not null,
    user_password varchar(80) not null,
    user_tokens int default 0,
    constraint pk_users primary key (user_id),
    constraint unique_users_nick unique (user_nick),
    constraint unique_users_email unique (user_email)
);

create table petition(
	pet_song_id int AUTO_INCREMENT ,
    constraint pk_petition primary key (pet_song_id),
    foreign key fk_petition_song (pet_song_id) references song(son_id)
);

create table petition_queue(
	pq_song_id int,
    pq_users_id int,
    pq_moment_temp datetime not null,
    constraint pk_petition_queue primary key (pq_song_id, pq_users_id),
    foreign key fk_petition_queue_song (pq_song_id) references petition(pet_song_id),
    foreign key fk_petition_queue_users (pq_users_id) references users(user_id)
);

create table active_token(
	at_id int AUTO_INCREMENT ,
    at_moment_temp datetime not null,
    at_cadena varchar(80) not null,
    at_value int not null,
    primary key (at_id)
);

create table queue(
	que_song_id int,
    que_users_id int,
    que_moment_temp datetime not null,
   	primary key (que_song_id, que_users_id),
    foreign key fk_queue_song (que_song_id) references song(son_id),
    foreign key fk_queue_users (que_users_id) references users(user_id)
);

insert into users values (null,'admin','admin@gmail.com', 'admin', 0);
insert into users values (null,'cferrer1','cferrer1@milaifontanals.org', 'admin', 0);
insert into users values (null,'mpaez','mpaez@milaifontanals.org', 'admin', 0);

insert into active_token values (null, sysdate(), 'akdgvjbalsdfhjblaskdjvasnñlgnkanba', 100);
insert into active_token values (null, sysdate(), 'kjbclabncasvjlasdnv,ahsdvlasdbvads', 50);
insert into active_token values (null, sysdate(), 'asdvasdalsdfhjblaskdjvasnñlgnkanba', 100);



