DROP TABLE if exists tbl_auth_user;
-- ��Ȩ�û���
CREATE TABLE tbl_auth_user
(
    -- ��ɫ����
    role_code                     VARCHAR(32)                                            NOT NULL,
    -- ��¼�û���
    username                      VARCHAR(32)                                            NOT NULL,
    -- ����ʱ��
    create_datetime               DATETIME                                               ,
    -- ������
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_auth_user_KEY PRIMARY KEY (role_code,username)
);

