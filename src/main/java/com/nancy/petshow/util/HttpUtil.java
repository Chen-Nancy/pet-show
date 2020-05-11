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
     * 发送http请求
     *
     * @param url 请求路径
     * @param map 请求参数
     * @return
     * @throws IOException
     */
    public static String httpSend(String url, Map<String, Object> map) throws IOException {
        return http(url, map);
    }

    private static String http(String url, Map<String, Object> params) throws IOException {
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
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
        } catch (Exception e) {
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
