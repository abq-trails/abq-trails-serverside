create table photo (photo_id CHAR(16) FOR BIT DATA not null, created timestamp not null, updated timestamp not null, trail_id CHAR(16) FOR BIT DATA, user_id CHAR(16) FOR BIT DATA, primary key (photo_id))
create table rating (rating_id CHAR(16) FOR BIT DATA not null, created timestamp not null, rating integer not null, review varchar(255), updated timestamp not null, trail_id CHAR(16) FOR BIT DATA, user_id CHAR(16) FOR BIT DATA, primary key (rating_id))
create table trail (trail_id CHAR(16) FOR BIT DATA not null, bike varchar(255), created timestamp not null, dog varchar(255), horse varchar(255), length double not null, trail_name varchar(255) not null, trail_rating double, trail_head varchar(255) not null, updated timestamp not null, primary key (trail_id))
create table user_profile (user_id CHAR(16) FOR BIT DATA not null, authenticated_id varchar(255) not null, created timestamp not null, first_name varchar(255) not null, last_name varchar(255) not null, updated timestamp not null, username varchar(255) not null, primary key (user_id))
alter table trail add constraint UK_pslkabghf04fo744tf1xwchvi unique (trail_name)
alter table user_profile add constraint UK_kbgc4ul5fj2qlab2yuyf4jiy3 unique (authenticated_id)
alter table user_profile add constraint UK_9551piq2wp9kh4kket0wr65vt unique (username)
alter table photo add constraint FKfsgrsw612jk5mgbeeot5wxevo foreign key (trail_id) references trail
alter table photo add constraint FK8djnj0mx8yc01e8wfecmku9yo foreign key (user_id) references user_profile
alter table rating add constraint FKe9n7qj9xpab45d6qw2bfd48pc foreign key (trail_id) references trail
alter table rating add constraint FKg2kw5tch1ijfl5qsooegr5iuf foreign key (user_id) references user_profile
