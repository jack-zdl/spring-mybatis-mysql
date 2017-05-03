DROP TABLE if exists tbl_auth_menu;
-- ��Ȩ�˵���
CREATE TABLE tbl_auth_menu
(
    -- ��ɫ����
    role_code                     VARCHAR(32)                                            NOT NULL,
    -- �˵�����
    menu_code                     VARCHAR(32)                                            NOT NULL,
    -- ����ʱ��
    create_datetime               DATETIME                                               ,
    -- ������
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_auth_menu_KEY PRIMARY KEY (role_code,menu_code)
);

