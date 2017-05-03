DROP TABLE if exists tbl_auth_resource;
-- ��Ȩ��Դ��
CREATE TABLE tbl_auth_resource
(
    -- ��ɫ����
    role_code                     VARCHAR(32)                                            NOT NULL,
    -- ��Դ����
    resource_id                   VARCHAR(32)                                            NOT NULL,
    -- ����ʱ��
    create_datetime               DATETIME                                               ,
    -- ������
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_auth_resource_KEY PRIMARY KEY (role_code,resource_id)
);

