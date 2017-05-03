DROP TABLE if exists tbl_user_roles;
-- 角色表
CREATE TABLE tbl_user_roles
(
    -- 角色代码
    code                          VARCHAR(32)                                            NOT NULL,
    -- 角色名称
    name                          VARCHAR(16)                                            NOT NULL,
    -- 角色级别
    level                         INT                                                    NOT NULL,
    -- 角色描述
    description                   VARCHAR(128)                                           ,
    -- 角色是否可见
    visible                       BIT                 DEFAULT 0                          NOT NULL,
 CONSTRAINT tbl_user_roles_KEY PRIMARY KEY (code)
);

