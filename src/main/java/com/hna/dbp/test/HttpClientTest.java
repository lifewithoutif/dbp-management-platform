package com.hna.dbp.test;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* select * from job b where substr(b.month, 6, 1) = '3'
       and b.工号 = (select 工号
  from job a
  group by a.工号
  having count(1))
* */

public class HttpClientTest {
    public static void main(String[] args) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        // 访问的目标站点，端口和协议
        HttpHost targetHost = new HttpHost("www.baidu.com");
        // 代理的设置
        HttpHost proxy = new HttpHost("localhost", 8080);
        httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        // 目标地址
        HttpGet httpget = new HttpGet("/");
        System.out.println("目标: " + targetHost);
        System.out.println("请求: " + httpget.getRequestLine());
        // 执行
        HttpResponse response = httpclient.execute(targetHost, httpget);
        HttpEntity entity = response.getEntity();
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
        if (entity != null) {
            System.out.println("Response content length: " + entity.getContentLength());
        }
        // 显示结果
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        if (entity != null) {
            entity.consumeContent();
        }
    }
}



