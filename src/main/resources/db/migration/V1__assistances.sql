create table if not exists assistances
(
    id          bigint auto_increment
    primary key,
    description varchar(300) not null,
    name        varchar(100) not null
    );

