create table assistances
(
    id          bigint auto_increment
        primary key,
    description varchar(300) not null,
    name        varchar(100) not null
);

insert assistances (id, description, name) values (1, 'Automação interna', 'Automação Residencial');
insert assistances (id, description, name) values (2, 'Automação externa', 'Automação Residencial');
insert assistances (id, description, name) values (3, 'Substituição de fiação interna da residência', 'Troca de fiação interna');