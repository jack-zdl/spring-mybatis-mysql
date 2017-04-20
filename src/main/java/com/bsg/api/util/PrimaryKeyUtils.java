package com.bsg.api.util;

import java.util.UUID;


/**
 * @description 生成ID工具类 Created by zhang on 2017/3/29.
 */
public class PrimaryKeyUtils {

    /**
     * uuid
     *
     * @return
     */
    public static String uniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().trim().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(uniqueId());
        System.out.println(uniqueId());
        System.out.println(uniqueId());
        System.out.println(uniqueId());
        System.out.println(uniqueId());
        System.out.println(uniqueId());
    }
}
