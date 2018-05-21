package com.bsg.api.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 功能说明:   Shiro 学习 <br>
 * 系统版本: 2.0 <br>
 * 开发人员: zhangdl <br>
 * 开发时间:2018/5/21 22:06<br>
 * <br>
 */
public class HellloShiro {

    private static final Logger LOGGER= LoggerFactory.getLogger(HellloShiro.class);

    public static void main(String[] args){
        /**
         * 第一步  工厂类创建SecurityManager对象
         * 最终放入SecurityUtils 提供Shiro框架使用
         */
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("shiro","201314");
        try {
            subject.login(token);
        }catch (Exception e){
            LOGGER.info("登录失败");
            e.printStackTrace();
        }
        LOGGER.info("登陆成功! Hello "+subject.getPrincipal());
        // 注销
        subject.logout();
    }
}
