DROP TABLE if exists tbl_dict_type;
-- 字典类型表
CREATE TABLE tbl_dict_type
(
    -- 字典类型项代码
    code                          VARCHAR(32)                                            NOT NULL,
    -- 字典类型项名称
    name                          VARCHAR(16)                                            NOT NULL,
    -- 创建时间
    create_datetime               DATETIME                                               ,
    -- 创建者
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_dict_type_KEY PRIMARY KEY (code)
);

