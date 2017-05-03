DROP TABLE if exists tbl_dict;
-- �ֵ��
CREATE TABLE tbl_dict
(
    -- �ֵ����������
    dict_type_code                VARCHAR(32)                                            NOT NULL,
    -- �ֵ����ʹ���
    code                          VARCHAR(32)                                            NOT NULL,
    -- �ֵ���������
    name                          VARCHAR(255)                                           NOT NULL,
    -- ����ʱ��
    create_datetime               DATETIME                                               ,
    -- ������
    create_user_id                VARCHAR(32)                                            ,
 CONSTRAINT tbl_dict_KEY PRIMARY KEY (dict_type_code,code)
);

