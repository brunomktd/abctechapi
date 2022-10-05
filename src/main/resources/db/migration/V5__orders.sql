create table if not exists order_location
(
    id        bigint auto_increment
    primary key,
    date      datetime(6) null,
    latitude  double      null,
    longitude double      null
    );

create table if not exists orders
(
    id                      bigint auto_increment
    primary key,
    created_at              datetime(6) null,
    status                  int         null,
    client_id               bigint      not null,
    end_order_location_id   bigint      null,
    operator_id             bigint      not null,
    start_order_location_id bigint      null,
    constraint FK_client_id
    foreign key (client_id) references clients (id),
    constraint FK_end_order_id
    foreign key (end_order_location_id) references order_location (id),
    constraint FK_operator_id
    foreign key (operator_id) references operators (id),
    constraint FK_start_order_id
    foreign key (start_order_location_id) references order_location (id)
    );

create table if not exists orders_services
(
    order_id    bigint not null,
    services_id bigint not null,
    constraint FK_services_id_assistances_id
    foreign key (services_id) references assistances (id),
    constraint FK_order_id_orders_id
    foreign key (order_id) references orders (id)
    );
