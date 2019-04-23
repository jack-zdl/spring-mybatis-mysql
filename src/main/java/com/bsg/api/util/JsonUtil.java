package com.bsg.api.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @Author: dl.zhang
 * @Date: 2019/4/17
 */
public class JsonUtil {
   public static final String jsonString =" [         {                  \"result\": 1,     \"code\": null,     \"msg\": \"书籍新增成功。\",     \"data\": null         },         {                            \"result\": 1,     \"code\": null,     \"msg\": \"书籍新增成功。\",     \"data\": null         },         {                            \"result\": 1,     \"code\": null,     \"msg\": \"书籍新增成功。\",     \"data\": null         },         {                            \"result\": 1,     \"code\": null,     \"msg\": \"书籍新增成功。\",     \"data\": null         }     ]";
    @Before
    public void before(){

    }
    @Test
    public void mapperTest(){
        final ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<RespJson> respJsonList = mapper.readValue(JsonUtil.jsonString,ArrayList.class);
            ArrayList<RespJson> list = mapper.readValue(JsonUtil.jsonString,mapper.getTypeFactory().constructCollectionType(List.class, RespJson.class));
            System.out.println("info1"+respJsonList.toString());
            System.out.println("info1"+list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after(){

    }
}
