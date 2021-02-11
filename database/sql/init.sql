ALTER SYSTEM SET max_connections = 500;

-- Create roles
CREATE USER migrations WITH ENCRYPTED PASSWORD 'migrations';

-- Create databases
CREATE DATABASE migrations_snapshot OWNER migrations;
CREATE DATABASE migrations_prod OWNER migrations;

-- Create schema for databases

\c migrations_prod
SET ROLE migrations;

create sequence user_id_seq
    increment by 50;

create table tbl_country
(
    code varchar(255) not null
        constraint tbl_country_pkey
            primary key,
    country_name varchar(1024) not null
);

create index country_name_index
    on tbl_country (country_name);

create table tbl_language
(
    code varchar(255) not null
        constraint tbl_language_pkey
            primary key,
    name varchar(1024) not null
);

create index idx_language_name
    on tbl_language (name);

create table tbl_user
(
    id bigint not null
        constraint tbl_user_pkey
            primary key,
    status integer,
    bio text,
    last_name varchar(255) not null,
    login varchar(255) not null,
    name varchar(255) not null,
    country_id varchar(255) not null
        constraint fk_user_country_id
            references tbl_country
);

create index user_name_index
    on tbl_user (name);

create index idx_user_last_name
    on tbl_user (last_name);

create table tbl_user_language
(
    user_id bigint not null
        constraint fk_user_language_user_id
            references tbl_user,
    language_id varchar(255) not null
        constraint fk_user_language_language_id
            references tbl_language,
    constraint tbl_user_language_pkey
        primary key (user_id, language_id)
);

\copy tbl_country(country_name,code) FROM '/docker-entrypoint-initdb.d/countries.csv' DELIMITER ',' CSV;

\copy tbl_language(code,name) FROM '/docker-entrypoint-initdb.d/languages.csv' DELIMITER ',' CSV;

insert into tbl_user (id,status,bio,last_name,login,name,country_id) values (1, 1, 'bio', 'user1', 'user1', 'user1', 'AF');
insert into tbl_user (id,status,bio,last_name,login,name,country_id) values (2, 1, 'bio', 'user2', 'user2', 'user2', 'RU');
insert into tbl_user (id,status,bio,last_name,login,name,country_id) values (3, 1, 'bio', 'user3', 'user3', 'user3', 'MF');

insert into tbl_user_language(user_id,language_id) values (1,'aa');
insert into tbl_user_language(user_id,language_id) values (1,'ru');
insert into tbl_user_language(user_id,language_id) values (1,'ro');
insert into tbl_user_language(user_id,language_id) values (2,'ru');
insert into tbl_user_language(user_id,language_id) values (2,'en');
insert into tbl_user_language(user_id,language_id) values (3,'aa');