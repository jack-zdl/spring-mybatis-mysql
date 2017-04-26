package com.bsg.api.util;

import com.bsg.api.exception.HttpClientException;
import com.bsg.api.exception.HttpConnectionException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * Created by zhang on 2017/4/20.
 */
public class HttpClientUtils {

    private static Logger logger = Logger.getLogger(HttpClientUtils.class);

    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_DELETE = "delete";

    /**
     * @return
     * @static
     * @description 发送get请求
     */
    public static RespJson sendHttpGet(String httpUrl) throws HttpConnectionException, HttpClientException, IOException {
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }

    public static RespJson sendHttpPost(String httpUrl) throws HttpConnectionException, HttpClientException, IOException {
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost);
    }

    /**
     * @param httpPost
     * @return
     */
    private static RespJson sendHttpPost(HttpPost httpPost) {
        RespJson respJson = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpPost);
            String respStr = null;
            if (httpResponse.getStatusLine().getStatusCode() == SC_OK) {
                HttpEntity httpEntity = httpResponse.getEntity();
                respStr = EntityUtils.toString(httpEntity, "utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respJson;
    }

    /**
     * @param httpGet
     * @return
     * @description实时发送getHttp请求
     */
    private static RespJson sendHttpGet(HttpGet httpGet) throws HttpConnectionException, HttpClientException, IOException {
        RespJson respJson = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            //  HttpClient http = new DefaultHttpClient(); //不推荐使用的
            //   HttpResponse response = http.execute(httpGet);
            httpClient = HttpClients.createDefault();
            httpResponse = httpClient.execute(httpGet);

            String respStr = null;
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    respStr = EntityUtils.toString(entity, "UTF-8");
                }
                logger.info("getHttp连接通过" + respStr);
                // 释放资源
                EntityUtils.consume(entity);
                respJson = RespJsonFactory.buildSuccess("getHttp连接通过");
            }
        } catch (Exception e) {
            logger.error("getHttp连接失败");
            e.printStackTrace();
        }
        return respJson;
    }
}
