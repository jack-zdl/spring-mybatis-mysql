DROP TABLE if exists tbl_dict;
-- 字典表
CREATE TABLE tbl_dict
(
    -- 字典类型项代码
    dict_type_code                VARCHAR(32)                                            NOT NULL,
    -- 字典类型代码
    code                          VARCHAR(32)                                            NOT NULL,
    -- 字典类型名称
    name                          VARCHAR(255)                                           NOT NULL,
    -- 创建时间
    create_datetime               DATETIME                                               ,
    -- 创建者
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_dict_KEY PRIMARY KEY (dict_type_code,code)
);

