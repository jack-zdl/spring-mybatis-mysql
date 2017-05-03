DROP TABLE if exists tbl_auth_menu;
-- 授权菜单表
CREATE TABLE tbl_auth_menu
(
    -- 角色代码
    role_code                     VARCHAR(32)                                            NOT NULL,
    -- 菜单代码
    menu_code                     VARCHAR(32)                                            NOT NULL,
    -- 创建时间
    create_datetime               DATETIME                                               ,
    -- 创建者
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_auth_menu_KEY PRIMARY KEY (role_code,menu_code)
);

