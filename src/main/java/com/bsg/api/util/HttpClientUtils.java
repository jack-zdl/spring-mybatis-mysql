package com.bsg.api.util;

import com.bsg.api.exception.HttpClientException;
import com.bsg.api.exception.HttpConnectionException;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//import org.apache.log4j.Logger;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * Created by zhang on 2017/4/20.
 */
public class HttpClientUtils {

//    private static Logger logger = Logger.getLogger(HttpClientUtils.class);

    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    public static final String METHOD_PUT = "put";
    public static final String METHOD_DELETE = "delete";

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(15000)
            .setConnectTimeout(15000)
            .setConnectionRequestTimeout(15000)
            .build();

    /**
     * @return
     * @static
     * @description 发送get请求
     */
    public static RespJson sendHttpGet(String httpUrl) throws HttpConnectionException, HttpClientException, IOException {
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }

    public static RespJson sendHttpPost(String httpUrl, String stringParam) throws HttpConnectionException, HttpClientException, IOException {
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            if (stringParam != null) {
                StringEntity stringEntity = new StringEntity(stringParam, "UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-Type", "application/json");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    public static RespJson sendHttpPut(String httpUrl) throws HttpConnectionException, HttpClientException, IOException {
        HttpPut httpPut = new HttpPut(httpUrl);
        return sendHttpPut(httpPut);
    }

    public static RespJson sendHttpDelete(String httpUrl) throws HttpConnectionException, HttpClientException, IOException {
        HttpDelete httpDelete = new HttpDelete(httpUrl);
        return sendHttpDelete(httpDelete);
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
            httpPost.setConfig(requestConfig);//设置请求的一些超时时间连接时间设置
            httpResponse = httpClient.execute(httpPost);//执行请求，得到返回结果
            String respStr = null;
            HttpEntity httpEntity = httpResponse.getEntity();//得到返回结果的数据
            if (httpResponse.getStatusLine().getStatusCode() == SC_OK) {
                respStr = EntityUtils.toString(httpEntity, "utf-8");
                respJson = RespJsonFactory.buildSuccess("postHttp连接通过!", respStr);
            } else {
                respJson = RespJsonFactory.buildFailure("postHttp连接不通过!");
            }
            // 释放资源
            EntityUtils.consume(httpEntity);
            httpResponse.close();
            httpClient.close();
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
                // 释放资源
                EntityUtils.consume(entity);
                respJson = RespJsonFactory.buildSuccess("getHttp连接通过!", respStr);
            }
        } catch (Exception e) {
//            logger.error("getHttp连接失败");
            e.printStackTrace();
        }
        return respJson;
    }


    private static RespJson sendHttpPut(HttpPut httpPut) throws HttpConnectionException, HttpClientException, IOException {
        RespJson respJson = null;
        CloseableHttpClient closeableHttpClient = null;
        CloseableHttpResponse closeableHttpResponse = null;
        HttpEntity httpEntity = null;
        String responseContent = null;
        try {
            //生成默认的httpClient实例
            closeableHttpClient = HttpClients.createDefault();
            closeableHttpResponse = closeableHttpClient.execute(httpPut);
            httpEntity = closeableHttpResponse.getEntity();
            if (closeableHttpResponse.getStatusLine().getStatusCode() == SC_OK) {
                responseContent = EntityUtils.toString(httpEntity, "utf-8");
                respJson = RespJsonFactory.buildSuccess("putHttp连接通过!", responseContent);
            } else {
                respJson = RespJsonFactory.buildFailure("putHttp连接通过!");
            }
            EntityUtils.consume(httpEntity);
            closeableHttpResponse.close();
            closeableHttpResponse.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientException();
        }
        return respJson;
    }

    public static RespJson sendHttpDelete(HttpDelete httpDelete) throws HttpConnectionException, HttpClientException, IOException {
        RespJson respJson = null;
        CloseableHttpClient closeableHttpClient = null;
        CloseableHttpResponse closeableHttpResponse = null;
        HttpEntity httpEntity = null;
        String responseContent = null;
        try {
            closeableHttpClient = HttpClients.createDefault();
            closeableHttpResponse = closeableHttpClient.execute(httpDelete);
            httpEntity = closeableHttpResponse.getEntity();
            if (closeableHttpResponse.getStatusLine().getStatusCode() == SC_OK) {
                responseContent = EntityUtils.toString(httpEntity, "utf-8");
                respJson = RespJsonFactory.buildSuccess("deleteHttp连接通过!", responseContent);
            } else {
                respJson = RespJsonFactory.buildFailure("deleteHttp连接不通过!");
            }
            EntityUtils.consume(httpEntity);
            closeableHttpResponse.close();
            closeableHttpResponse.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientException();
        }
        return respJson;
    }
}
