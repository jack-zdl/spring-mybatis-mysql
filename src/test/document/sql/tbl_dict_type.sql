DROP TABLE if exists tbl_dict_type;
-- �ֵ����ͱ�
CREATE TABLE tbl_dict_type
(
    -- �ֵ����������
    code                          VARCHAR(32)                                            NOT NULL,
    -- �ֵ�����������
    name                          VARCHAR(16)                                            NOT NULL,
    -- ����ʱ��
    create_datetime               DATETIME                                               ,
    -- ������
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_dict_type_KEY PRIMARY KEY (code)
);

