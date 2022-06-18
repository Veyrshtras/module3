-- -----------------------------------------------------
-- Table module3test.tags
-- -----------------------------------------------------
create table if not exists tags
(
    id   bigint auto_increment,
    name varchar(20) unique not null,
    primary key (id)
    );

-- -----------------------------------------------------
-- Table module3test.gift_certificates
-- -----------------------------------------------------
create table if not exists gift_certificates
(
    id               bigint auto_increment,
    name             varchar(45)  unique not null,
    description      text(300),
    price            decimal(8, 2)  not null,
    duration         smallint  not null,
    create_date      datetime               not null,
    last_update_date datetime               not null,
    primary key(id)
    );

-- -----------------------------------------------------
-- Table module3test.gift_certificates_tags
-- -----------------------------------------------------
create table if not exists gift_certificates_has_tags
(
    id                  bigint  auto_increment,
    gift_certificate_id bigint ,
    tag_id              bigint ,
    primary key (id),
    foreign key (gift_certificate_id) references gift_certificates (id),
    foreign key (tag_id) references tags (id)
    );

-- -----------------------------------------------------
-- Table module3test.users
-- -----------------------------------------------------
create table if not exists users
(
    id   bigint  auto_increment,
    name varchar(100) not null,
    primary key(id)
    );

-- -----------------------------------------------------
-- Table module3test.order
-- -----------------------------------------------------
create table if not exists order
(
    id                  bigint  auto_increment,
    price               decimal(8, 2)  not null,
    purchase_time       datetime               not null ,
    user_id             bigint ,
    gift_certificate_id bigint ,
    primary key(id),
    foreign key(gift_certificate_id) references gift_certificates (id),
    foreign key(user_id) references users (id)
    );