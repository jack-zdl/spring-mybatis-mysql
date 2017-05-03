DROP TABLE if exists tbl_resource;
-- ��Դ��
CREATE TABLE tbl_resource
(
    -- ��Դ����
    id                            INT                                     AUTO_INCREMENT NOT NULL,
    -- �˵�����
    menu_id                       VARCHAR(32)                                            NOT NULL,
    -- �ؼ�����
    control_type                  VARCHAR(16)                                            NOT NULL,
    -- �ؼ�id
    control_id                    VARCHAR(32)                                            NOT NULL,
    -- �ؼ���
    control_name                  VARCHAR(16)                                            ,
    -- ����ʱ��
    create_datetime               DATETIME                                               ,
    -- ������
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_resource_KEY PRIMARY KEY (id)
);

