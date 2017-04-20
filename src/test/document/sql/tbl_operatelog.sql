DROP TABLE if exists tbl_operatelog;
-- ������־��
CREATE TABLE tbl_operatelog
(
    -- ������־����
    id                            INT                                     AUTO_INCREMENT NOT NULL,
    -- ����ҳ��
    operate_page                  VARCHAR(32)                                            NOT NULL,
    -- ��������
    operate_type                  VARCHAR(32)                                            NOT NULL,
    -- ������
    operator                      VARCHAR(32)                                            NOT NULL,
    -- ����ʱ��
    operate_time                  DATETIME                                               NOT NULL,
    -- ����״̬
    status                        INT                                                    NOT NULL,
    -- ��������
    operate_desc                  VARCHAR(128)                                           ,
 CONSTRAINT tbl_operatelog_KEY PRIMARY KEY (id)
);

