create table if not exists clients
(
    id         bigint auto_increment
    primary key,
    email      varchar(255) null,
    name       varchar(255) null,
    address_id bigint       null,
    constraint FK_address_id
    foreign key (address_id) references addresses (id)
    );

