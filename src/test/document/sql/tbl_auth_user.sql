DROP TABLE if exists tbl_auth_user;
-- 授权用户表
CREATE TABLE tbl_auth_user
(
    -- 角色代码
    role_code                     VARCHAR(32)                                            NOT NULL,
    -- 登录用户名
    username                      VARCHAR(32)                                            NOT NULL,
    -- 创建时间
    create_datetime               DATETIME                                               ,
    -- 创建者
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_auth_user_KEY PRIMARY KEY (role_code,username)
);

