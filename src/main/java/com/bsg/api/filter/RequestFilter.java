package com.bsg.api.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by zhang on 2017/5/18.
 */
public class RequestFilter extends OncePerRequestFilter {

    public String filter(HttpServletRequest request, String input) {
        String ret = input;
        //ios客户端请求参数值可能为(null)服务端过滤掉当null处理即可
        if (input == null || input.trim().equals("(null)")) {
            ret = null;
            return ret;
        }
        final String userAgent = request.getHeader("User-Agent");
        final String method = request.getMethod();
        //该处可以实现各种业务的自定义的过滤机制
        if (method.equalsIgnoreCase("get")
                || userAgent.toLowerCase().indexOf("android") != -1) {
            try {
                ret = new String(input.getBytes("ISO8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("中文乱码过滤器---自定义为实现的");
        filterChain.doFilter(new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public String getParameter(String name) {
                String value = super.getParameter(name);
                return filter(this, value);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = super.getParameterValues(name);
                if (values == null) {
                    return null;
                }
                for (int i = 0; i < values.length; i++) {
                    values[i] = filter(this, values[i]);
                }
                return values;
            }
        }, httpServletResponse);
    }

}
