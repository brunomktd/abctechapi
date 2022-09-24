create table order_location
(
    id        bigint auto_increment
        primary key,
    date      datetime(6) null,
    latitude  double      null,
    longitude double      null
);

CREATE TABLE `orders` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `operation_id` bigint NOT NULL,
                          `status` int DEFAULT NULL,
                          `end_order_location_id` bigint DEFAULT NULL,
                          `operator_id` bigint DEFAULT NULL,
                          `start_order_location_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FK_end_order_id` (`end_order_location_id`),
                          KEY `FK_operator_id` (`operator_id`),
                          KEY `FK_start_order_id` (`start_order_location_id`),
                          CONSTRAINT `FK_end_order_id` FOREIGN KEY (`end_order_location_id`) REFERENCES `order_location` (`id`),
                          CONSTRAINT `FK_start_order_id` FOREIGN KEY (`start_order_location_id`) REFERENCES `order_location` (`id`),
                          CONSTRAINT `FK_operator_id` FOREIGN KEY (`operator_id`) REFERENCES `operators` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

create table orders_services
(
    order_id    bigint not null,
    services_id bigint not null,
    constraint FK_order_id_services_id
        foreign key (services_id) references assistances (id),
    constraint Fk_order_id_orders
        foreign key (order_id) references orders (id)
);





