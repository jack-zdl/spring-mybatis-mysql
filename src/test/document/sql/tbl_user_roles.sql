DROP TABLE if exists tbl_user_roles;
-- ��ɫ��
CREATE TABLE tbl_user_roles
(
    -- ��ɫ����
    code                          VARCHAR(32)                                            NOT NULL,
    -- ��ɫ����
    name                          VARCHAR(16)                                            NOT NULL,
    -- ��ɫ����
    level                         INT                                                    NOT NULL,
    -- ��ɫ����
    description                   VARCHAR(128)                                           ,
    -- ��ɫ�Ƿ�ɼ�
    visible                       BIT                 DEFAULT 0                          NOT NULL,
 CONSTRAINT tbl_user_roles_KEY PRIMARY KEY (code)
);

