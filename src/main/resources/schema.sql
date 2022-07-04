create table if not exists Taco_Order (
    id identity,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(50) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(50) not null,
    cvv varchar(30) not null,
    created_time timestamp not null
    );

create table if not exists Taco (
    id identity,
    order_id bigint,
    ordinal bigint,
    name varchar(50) not null,
    created_time timestamp not null
    );

create table if not exists Ingredient_Ref (
    id identity,
    ingredient varchar(4) not null,
    taco_id bigint,
    ordinal bigint
    );


create table if not exists Ingredient (
    id varchar(4) primary key,
    name varchar(25) not null,
    type varchar(10) not null
    );


alter table Taco
    add foreign key (order_id) references Taco_Order(id);
alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);
alter table Ingredient_Ref
    add foreign key (taco_id) references Taco(id);