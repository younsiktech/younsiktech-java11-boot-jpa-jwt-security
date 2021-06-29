create table user
(
    id           bigint       not null auto_increment,
    created_at   datetime     not null,
    email        varchar(100) not null,
    gender       varchar(255),
    name         varchar(20)  not null,
    nickname     varchar(30)  not null,
    password     varchar(100) not null,
    phone_number varchar(255) not null,
    role         varchar(255),
    status       varchar(255) not null,
    updated_at   datetime     not null,
    primary key (id)
        constraint UK_email
        unique (email)
) engine = InnoDB;

create table purchase
(
    id           varchar(12) not null,
    created_at   datetime,
    name         varchar(255),
    payment_date datetime,
    status       varchar(255),
    updated_at   datetime,
    user_id      bigint,
    primary key (id)
) engine = InnoDB;
