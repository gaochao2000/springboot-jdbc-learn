package com.yanggaochao.springboot.learn.springbootjdbclearn;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

/**
 * @author 杨高超
 * @since 2018-03-09
 */
public class HttpClientHelper {


    /**
     * 用 get 方法发起一个http请求
     *
     * @param url 要访问的 http 的 url
     * @return 访问 http 后得到的回应文本
     */
    public String httpGetRequest(String url, Map<String, String> headers) {
        try {
            HttpClient httpclient = new HttpClient();
            GetMethod method = new GetMethod(url);
            method.setRequestHeader("Content-Type", "application/json; charset=utf-8");
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler(3, false));
            if (headers != null) {
                for (String key : headers.keySet()) {
                    method.setRequestHeader(key, headers.get(key));
                }
            }

            int statusCode = httpclient.executeMethod(method);
            if (statusCode == 200) {
                return parseInputStream(method.getResponseBodyAsStream());
            } else {
                System.out.println(url + " status = " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用 post 方法发起一个 http 请求
     *
     * @param url  要访问的 http 的 url
     * @param data post 请求中的 data 数据
     * @return 访问 http 后得到的回应文本
     */

    public String httpPostRequest(String url, String data, Map<String, String> headers) {
        try {
            HttpClient httpclient = new HttpClient();
            PostMethod method = new PostMethod(url);
            method.setRequestHeader("Content-Type",
                    "application/json;charset=UTF-8");
            method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36");
            if (headers != null) {
                for (String key : headers.keySet()) {
                    method.setRequestHeader(key, headers.get(key));
                }
            }

            method.setRequestEntity(new StringRequestEntity(data, "json", "utf-8"));
            int statusCode = httpclient.executeMethod(method);
            if (statusCode == 200) {
                return parseInputStream(method.getResponseBodyAsStream());
            } else {
                System.out.println(url + " status = " + statusCode + parseInputStream(method.getResponseBodyAsStream()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 从 java.io.Reader 中解析文本数据
     *
     * @param rd java.io.Reader 对象
     * @throws Exception 发生错误时抛出异常
     */
    private String parseReader(Reader rd) throws Exception {
        BufferedReader brd = new BufferedReader(rd);
        String line;
        StringBuilder respongseContext = new StringBuilder();

        while ((line = brd.readLine()) != null) {
            respongseContext.append(line).append("\n");
        }
        //rd.close();
        if (respongseContext.length() > 0) {
            respongseContext.deleteCharAt(respongseContext.length() - 1);
        }
        return respongseContext.toString();
    }

    /**
     * 从输入流中解析文本数据
     *
     * @param is 输入流
     * @throws Exception 发生错误时抛出异常
     */
    private String parseInputStream(InputStream is) throws Exception {
        return parseReader(new BufferedReader(new InputStreamReader(is)));
    }

}
