DROP TABLE if exists tbl_menu;
-- �˵���
CREATE TABLE tbl_menu
(
    -- �˵�����
    code                          VARCHAR(32)                                            NOT NULL,
    -- �˵�����
    name                         VARCHAR(16)                                          NOT NULL,
    -- �˵�˳��
    order                         INT                                                    NOT NULL,
    -- �˵�����
    level                         INT                                                    NOT NULL,
    -- �˵�ͼ��
    icon                          VARCHAR(64)                                            ,
    -- �˵�����
    action                        VARCHAR(255)                                           ,
    -- ���˵�����
    parent_id                     VARCHAR(32)                             NOT NULL,
    -- ����ʱ��
    create_datetime               DATETIME                                               ,
    -- ������
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_menu_KEY PRIMARY KEY (code)
);

