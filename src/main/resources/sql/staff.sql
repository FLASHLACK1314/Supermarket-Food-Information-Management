-- auto-generated definition
create table staff
(
    staff_number bigint        not null
        constraint staff_pk
            primary key,
    staff_name   nvarchar(255) not null,
    staff_sex    nvarchar(255) not null,
    staff_phone  nvarchar(255) not null
)
go

exec sp_addextendedproperty 'MS_Description', N'员工表', 'SCHEMA', 'dbo', 'TABLE', 'staff'
go

exec sp_addextendedproperty 'MS_Description', N'员工编号', 'SCHEMA', 'dbo', 'TABLE', 'staff', 'COLUMN', 'staff_number'
go

exec sp_addextendedproperty 'MS_Description', N'员工姓名', 'SCHEMA', 'dbo', 'TABLE', 'staff', 'COLUMN', 'staff_name'
go

exec sp_addextendedproperty 'MS_Description', N'员工性别', 'SCHEMA', 'dbo', 'TABLE', 'staff', 'COLUMN', 'staff_sex'
go

exec sp_addextendedproperty 'MS_Description', N'员工联系方式', 'SCHEMA', 'dbo', 'TABLE', 'staff', 'COLUMN',
     'staff_phone'
go

