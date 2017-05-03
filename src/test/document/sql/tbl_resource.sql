DROP TABLE if exists tbl_resource;
-- 资源表
CREATE TABLE tbl_resource
(
    -- 资源编码
    id                            INT                                     AUTO_INCREMENT NOT NULL,
    -- 菜单编码
    menu_id                       VARCHAR(32)                                            NOT NULL,
    -- 控件类型
    control_type                  VARCHAR(16)                                            NOT NULL,
    -- 控件id
    control_id                    VARCHAR(32)                                            NOT NULL,
    -- 控件名
    control_name                  VARCHAR(16)                                            ,
    -- 创建时间
    create_datetime               DATETIME                                               ,
    -- 创建者
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_resource_KEY PRIMARY KEY (id)
);

