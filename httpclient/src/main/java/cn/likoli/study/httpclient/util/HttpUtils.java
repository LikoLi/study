package cn.likoli.study.httpclient.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

/**
 * HttpUtils
 *
 * @author liko
 * @date 2021/3/4
 */
public class HttpUtils {

    public static void doGet(String uri) throws IOException {
        // 1. 创建一个HttpClient对象
        // HttpClients类的createDefault()方法返回一个CloseableHttpClient对象，该对象是HttpClient接口的基本实现。
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2. 创建一个HttpGet对象
        // HttpGet类表示HTTP GET请求，该请求使用URI检索给定服务器的信息。
        HttpGet httpGet = new HttpGet(uri);

        // 3. 执行获取请求
        // CloseableHttpClient类的execute()方法接受HttpUriRequest(接口)对象(即HttpGet，HttpPost，HttpPut，HttpHead等)并返回响应对象。
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        Scanner sc = new Scanner(httpResponse.getEntity().getContent());
        System.out.println(httpResponse.getStatusLine());
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }

    public static void doPost(String uri) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        System.out.println("Request Type: " + httpPost.getMethod());
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        Scanner sc = new Scanner(httpResponse.getEntity().getContent());
        System.out.println(httpResponse.getStatusLine());
        while (sc.hasNext()) {
            System.out.println(sc.nextLine());
        }
    }

    public static void main(String[] args) throws IOException {
//        doGet("http://www.iquantex.com/");
        doPost("http://www.baidu.com/");
    }
}
