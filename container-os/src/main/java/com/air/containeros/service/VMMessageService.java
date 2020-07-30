package com.air.containeros.service;

import com.air.containeros.untils.VMMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class VMMessageService {
    @Autowired
    private VMMetrics vmMetrics;


    public Number getMemoryMessage() {
        List<String> metricsList = vmMetrics.getVMMetrics();
        int i = 0;
        String memoryString = "node_memory_Active_anon_bytes";//指定数据的特征字符串
        for (String line : metricsList) {

            if (line.indexOf(memoryString) != -1) { //依据特征字符串找到指定段落
                i++;
                if (i == 3) {//找到指定数据段落
                    String s1 = "\\+";  //记录隔离符号
                    String s2 = line.split(" ")[1];  //拆分获取数字部分（含有e和+符号）
                    String s3 = s2.split(s1)[1];  //拆分获取+号后面的数字部分
                    String s4 = s2.split("e")[0];//拆分获取e前面的数字部分
//                    return String.valueOf(Math.pow(Double.valueOf(s4), Double.valueOf(s3)));
                    System.out.println("s1:" + s1);
                    System.out.println("s2:" + s2);
                    System.out.println("s3:" + s3);
                    System.out.println("s4:" + s4);
                    return Math.pow(Double.valueOf(s4), Double.valueOf(s3));


                }
            }
        }


        return null;
    }


}
