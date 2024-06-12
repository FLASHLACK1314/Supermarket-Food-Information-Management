-- auto-generated definition
create table food_sales
(
    food_sales_number bigint        not null
        primary key,
    food_number       bigint        not null
        constraint food_sales_food_inventory_food_number_fk
            references food_inventory
            on update cascade on delete cascade,
    sales_quantity    nvarchar(255) not null,
    sales_price       nvarchar(255) not null,
    staff_number      bigint        not null
        constraint food_sales_staff_staff_number_fk
            references staff
            on update cascade on delete cascade
)
go

