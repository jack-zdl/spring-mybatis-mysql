DROP TABLE if exists tbl_menu;
-- 菜单表
CREATE TABLE tbl_menu
(
    -- 菜单代码
    code                          VARCHAR(32)                                            NOT NULL,
    -- 菜单名称
    name                         VARCHAR(16)                                          NOT NULL,
    -- 菜单顺序
    order                         INT                                                    NOT NULL,
    -- 菜单级别
    level                         INT                                                    NOT NULL,
    -- 菜单图标
    icon                          VARCHAR(64)                                            ,
    -- 菜单动作
    action                        VARCHAR(255)                                           ,
    -- 父菜单编码
    parent_id                     VARCHAR(32)                             NOT NULL,
    -- 创建时间
    create_datetime               DATETIME                                               ,
    -- 创建者
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_menu_KEY PRIMARY KEY (code)
);

