create table if not exists addresses
(
    id           bigint       not null
    primary key,
    city         varchar(255) null,
    neighborhood varchar(255) null,
    number       varchar(255) null,
    street       varchar(255) null,
    uf           varchar(255) null,
    zip_code     varchar(255) null
    );

