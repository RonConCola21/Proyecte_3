drop table if exists  active_token cascade;
drop table if exists  queue cascade;
drop table if exists  petition_queue cascade;
drop table if exists  petition cascade;
drop table if exists  users cascade;
drop table if exists  song cascade;

create table song(
	song_id int,
    song_spotify_id varchar(80),
   	artist varchar(80),
   	song_name varchar(80),
    song_duration int,
   	status char,
   	primary key (song_id),
   	constraint check_song_status check (status in ('w','b','p')),
    unique (song_spotify_id)
);

create table users (
	users_id int,
    users_nick varchar(80),
    email varchar(80),
    password varchar(80),
    tokens int,
    primary key (users_id),
    unique (users_nick),
    unique (email)
);

create table petition(
	song_id int,
    primary key (song_id),
    foreign key fk_petition_song (song_id) references song(song_id)
);

create table petition_queue(
	song_id int,
    users_id int,
    moment_temp datetime,
    primary key (song_id, users_id),
    foreign key fk_petition_queue_song (song_id) references petition(song_id),
    foreign key fk_petition_queue_users(users_id) references users(users_id)
);

create table active_token(
	token_id int,
    moment_temp datetime,
    cadena varchar(80),
    value int,
    primary key (token_id)
);

create table queue(
	song_id int,
    users_id int,
    moment_temp datetime,
   	primary key (song_id, users_id),
    foreign key fk_queue_song (song_id) references song(song_id),
    foreign key fk_queue_users (users_id) references users(users_id)
);
