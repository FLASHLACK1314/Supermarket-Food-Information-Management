-- auto-generated definition
create table supplier
(
    supplier_number bigint        not null
        constraint supplier_pk
            primary key,
    supplier_name   nvarchar(255) not null,
    supplier_phone  nvarchar(255) not null
)
go

exec sp_addextendedproperty 'MS_Description', N'供应商表', 'SCHEMA', 'dbo', 'TABLE', 'supplier'
go

exec sp_addextendedproperty 'MS_Description', N'供应商编号', 'SCHEMA', 'dbo', 'TABLE', 'supplier', 'COLUMN',
     'supplier_number'
go

exec sp_addextendedproperty 'MS_Description', N'供应商名称', 'SCHEMA', 'dbo', 'TABLE', 'supplier', 'COLUMN',
     'supplier_name'
go

exec sp_addextendedproperty 'MS_Description', N'供应商联系方式', 'SCHEMA', 'dbo', 'TABLE', 'supplier', 'COLUMN',
     'supplier_phone'
go

