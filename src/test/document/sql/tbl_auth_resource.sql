DROP TABLE if exists tbl_auth_resource;
-- 授权资源表
CREATE TABLE tbl_auth_resource
(
    -- 角色代码
    role_code                     VARCHAR(32)                                            NOT NULL,
    -- 资源编码
    resource_id                   VARCHAR(32)                                            NOT NULL,
    -- 创建时间
    create_datetime               DATETIME                                               ,
    -- 创建者
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_auth_resource_KEY PRIMARY KEY (role_code,resource_id)
);

