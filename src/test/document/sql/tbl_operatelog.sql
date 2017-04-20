DROP TABLE if exists tbl_operatelog;
-- 操作日志表
CREATE TABLE tbl_operatelog
(
    -- 操作日志编码
    id                            INT                                     AUTO_INCREMENT NOT NULL,
    -- 操作页面
    operate_page                  VARCHAR(32)                                            NOT NULL,
    -- 操作类型
    operate_type                  VARCHAR(32)                                            NOT NULL,
    -- 操作者
    operator                      VARCHAR(32)                                            NOT NULL,
    -- 操作时间
    operate_time                  DATETIME                                               NOT NULL,
    -- 操作状态
    status                        INT                                                    NOT NULL,
    -- 操作描述
    operate_desc                  VARCHAR(128)                                           ,
 CONSTRAINT tbl_operatelog_KEY PRIMARY KEY (id)
);

