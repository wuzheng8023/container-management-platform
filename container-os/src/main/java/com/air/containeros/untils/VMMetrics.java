package com.air.containeros.untils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class VMMetrics {
    public static final String TOTAL_MEMORY = "node_memory_Active_anon_bytes";
    public static final String CPU = "node_cpu_seconds_total";
//    public static final String TOTAL_CPU = "node_memory_Active_anon_bytes";
//    public static final String TOTAL_DISK = "node_memory_Active_anon_bytes";
//    public static final String RESIDUE_MEMORY = "node_memory_Active_anon_bytes";
//    public static final String RESIDUE_CPU = "node_memory_Active_anon_bytes";
//    public static final String RESIDUE_DISK = "node_memory_Active_anon_bytes";

    @Bean
    public List<String> getVMMetrics() {
        String url = "http://192.168.43.19:9100/metrics";
        String memoryString = "node_memory_Active_anon_bytes";
//        System.out.println("my lenthg:"+memoryString.length());
        String result = "";
        String urlName = url;
        List<String> resultList = new ArrayList<>();
        try {
            URL realUrl = new URL(urlName);
            //打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立实际的连接
            conn.connect();
            //获取所有的响应头字段
            Map<String, List<String>> map = conn.getHeaderFields();
            //遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "-->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            int i = 0;
            while ((line = in.readLine()) != null) {
                result += line;
//                System.out.println(line);
                resultList.add(line);
//                if (line.indexOf(memoryString) != -1) {
//                    i++;
//                    if (i == 3) {
//                        String[] strings = line.split(" ");
//
//                        String s = "\\+";
//                        System.out.println(s);
//                        String[] s1 = strings[1].split(s);
//                        System.out.println(s1[1]);
//                        System.out.println(Integer.valueOf(s1[1]));
//                        System.out.println(strings[1].indexOf(s));
//
////                        System.out.println(line);
//                        i = 0;
//                    }
//                }


            }
        } catch (Exception e) {
            System.out.println("GET请求出现异常" + e);
            e.printStackTrace();
        }
        return resultList;

    }
}
