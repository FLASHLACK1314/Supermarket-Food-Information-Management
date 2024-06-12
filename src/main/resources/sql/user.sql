-- auto-generated definition
create table [user]
(
    user_id  varchar(255) not null
        constraint user_pk
            primary key,
    password varchar(255)
)
go

exec sp_addextendedproperty 'MS_Description', N'用户名', 'SCHEMA', 'dbo', 'TABLE', 'user', 'COLUMN', 'user_id'
go

exec sp_addextendedproperty 'MS_Description', N'密码', 'SCHEMA', 'dbo', 'TABLE', 'user', 'COLUMN', 'password'
go

