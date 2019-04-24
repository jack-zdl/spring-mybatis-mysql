package com.bsg.api.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * <p></p>
 *
 * @Author: dl.zhang
 * @Date: 2019/4/24
 */
public class SqlSesstionFactoryTest {

    public static void main(String[] args) throws Exception
    {
        String resouce="mybatis-config.xml";
        InputStream is= Resources.getResourceAsStream(resouce);
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
        System.out.println(sqlSessionFactory.getConfiguration());
    }
}
