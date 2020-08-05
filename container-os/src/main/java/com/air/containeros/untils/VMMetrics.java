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
    public static final String CPU = "node_cpu_seconds_total";//获取CPU有关参数
    public static final String MEMORY__MEMAVAILABLE = "node_memory_MemAvailable";//剩余可用物理内存
    public static final String MEMORY__MEMTOTAL = "node_memory_MemTotal";//总的物理内存
    public static final String DISK_TOTAL = "node_filesystem_size_bytes{";//总磁盘大小
    public static final String DISK_AVAIL = "node_filesystem_avail_bytes{";//剩余总磁盘大小
    public static final String DISK_FSTYPE1 = "fstype=\"xfs\"";//文件格式1
    public static final String DISK_FSTYPE2 = "fstype=\"ext4\"";//文件格式2


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
                resultList.add(line);

            }
        } catch (Exception e) {
            System.out.println("GET请求出现异常" + e);
            e.printStackTrace();
        }
        return resultList;

    }
}
