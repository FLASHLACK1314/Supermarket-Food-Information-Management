-- auto-generated definition
create table food_inventory
(
    food_number     bigint        not null
        constraint food_inventory_pk
            primary key,
    food_name       nvarchar(255) not null,
    food_category   nvarchar(255) not null,
    food_price      nvarchar(255) not null,
    stock_quantity  nvarchar(255) not null,
    supplier_number bigint        not null
        constraint food_inventory_supplier_supplier_number_fk
            references supplier
            on update cascade on delete cascade
)
go

exec sp_addextendedproperty 'MS_Description', N'食物编号', 'SCHEMA', 'dbo', 'TABLE', 'food_inventory', 'COLUMN',
     'food_number'
go

exec sp_addextendedproperty 'MS_Description', N'食物姓名', 'SCHEMA', 'dbo', 'TABLE', 'food_inventory', 'COLUMN',
     'food_name'
go

exec sp_addextendedproperty 'MS_Description', N'食品类别', 'SCHEMA', 'dbo', 'TABLE', 'food_inventory', 'COLUMN',
     'food_category'
go

exec sp_addextendedproperty 'MS_Description', N'食物进价', 'SCHEMA', 'dbo', 'TABLE', 'food_inventory', 'COLUMN',
     'food_price'
go

exec sp_addextendedproperty 'MS_Description', N'库存数量', 'SCHEMA', 'dbo', 'TABLE', 'food_inventory', 'COLUMN',
     'stock_quantity'
go

exec sp_addextendedproperty 'MS_Description', N'供应商编号', 'SCHEMA', 'dbo', 'TABLE', 'food_inventory', 'COLUMN',
     'supplier_number'
go

