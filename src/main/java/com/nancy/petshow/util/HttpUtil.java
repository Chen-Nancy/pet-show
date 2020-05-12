package com.nancy.petshow.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author chen
 * @date 2020/5/11 13:58
 */
public class HttpUtil {
    /**
     * 发送get请求
     *
     * @param url 请求路径
     * @return
     * @throws IOException
     */
    public static String getHttp(String url) throws IOException {
        BufferedReader br = null;
        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (br != null) {
                br.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 发送post请求
     *
     * @param url    请求路径
     * @param params 请求参数
     * @return
     * @throws IOException
     */
    public static String postHttp(String url, Map<String, Object> params) throws IOException {
        URL u = null;
        HttpURLConnection con = null;
        StringBuilder sb = new StringBuilder();
        if (params == null) {
            return null;
        }
        params.forEach((k, v) -> {
            sb.append(k);
            sb.append("=");
            sb.append(v);
            sb.append("&");
        });
        sb.substring(0, sb.length() - 1);
        BufferedWriter bw = null;
        BufferedReader br = null;
        try {
            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8));
            bw.write(sb.toString());
            bw.flush();
            bw.close();
            StringBuilder builder = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String s;
            while ((s = br.readLine()) != null) {
                builder.append(s);
                builder.append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            throw new IOException(e);
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (br != null) {
                br.close();
            }
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
