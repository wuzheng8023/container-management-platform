package com.air.containeros.service;

import com.air.containeros.untils.VMMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

@Service
public class VMMessageService {
    @Autowired
    private VMMetrics vmMetrics;


    public Number getMemoryMessage() {
        List<String> metricsList = vmMetrics.getVMMetrics();
        int i = 0;
//        String memoryString = "node_memory_Active_anon_bytes";//指定数据的特征字符串
        for (String line : metricsList) {

            if (line.indexOf(VMMetrics.TOTAL_MEMORY) != -1) { //依据特征字符串找到指定段落
                i++;
                if (i == 3) {//找到指定数据段落
                    String s1 = "\\+";  //记录隔离符号
                    String s2 = line.split(" ")[1];  //拆分获取数字部分（含有e和+符号）
                    String s3 = s2.split(s1)[1];  //拆分获取+号后面的数字部分
                    String s4 = s2.split("e")[0];//拆分获取e前面的数字部分
//                    return String.valueOf(Math.pow(Double.valueOf(s4), Double.valueOf(s3)));
//                    System.out.println("s1:" + s1);
//                    System.out.println("s2:" + s2);
//                    System.out.println("s3:" + s3);
//                    System.out.println("s4:" + s4);
                    return Math.pow(Double.valueOf(s4), Double.valueOf(s3));


                }
            }
        }

        return null;
    }

    /**
     * 获取CPU利用率；多核CPU的利用率返回成为一个数组；
     *
     * @return
     */

    public double[] getCPUefficiency() {
        List<String> metricsList1 = vmMetrics.getVMMetrics();
        List<String> metricsList2 = vmMetrics.getVMMetrics();
        List<String> seconds_total1 = new ArrayList<>();
        List<String> seconds_total2 = new ArrayList<>();
        int i = 0;
//获取CPU信息中的数字部分
        for (String line : metricsList1) {

            if (line.contains(VMMetrics.CPU)) { //依据特征字符串找到指定段落
                i++;
                if (i <= 2) {
                    continue;
                }
                seconds_total1.add(line.split(" ")[1]);
//                System.out.println(line.split(" ")[1]);
            }
        }
        i = 0;
        for (String line : metricsList2) {

            if (line.contains(VMMetrics.CPU)) { //依据特征字符串找到指定段落
                i++;
                if (i <= 2) {
                    continue;
                }
                seconds_total2.add(line.split(" ")[1]);
//                System.out.println(line.split(" ")[1]);
            }
        }
        //计算总时长
        int numOfCore = (seconds_total1.size()) / 8; //得到CPU内核数；seconds_total1或者2都无所谓
        double[][] totalTime = new double[numOfCore][2];//第一个存储总时长，第二个存储不含idle的时长；（根据公式，二者都不含steal时长）
        double[] efficiency = new double[numOfCore]; //存储每个CPU的效率,也是最终返回的结果

        double idle = 0;//CPU其中的一个参数,是无效时间

        //一维是第几个CPU；二维是第几次获取的数据组，0是第一次，1是第二次；
        // 三维是CPU总的时长，其中第一个存储总时长，第二个存储不含idle的时长；（根据公式，二者都不含steal时长）
        double[][][] nn = new double[numOfCore][2][2];

        i = 0;
        int j = 0;
        for (String line : seconds_total1) {
            i++;
            if (i == 6) {
                continue;
            }
            if (i == 1) idle = Double.parseDouble(line);
            totalTime[j][0] += Double.parseDouble(line);

            if (i == 8) {
                totalTime[j][1] = totalTime[j][0] - idle;
                nn[j][0][0] = totalTime[j][0];
                nn[j][0][1] = totalTime[j][1];
                i = 0;
                j++;
            }


        }
        i = 0;
        j = 0;
        for (String line : seconds_total2) {
            i++;
            if (i == 6) {
                continue;
            }
            if (i == 1) idle = Double.parseDouble(line);
            totalTime[j][0] += Double.parseDouble(line);

            if (i == 8) {
                totalTime[j][1] = totalTime[j][0] - idle;
                nn[j][1][0] = totalTime[j][0];
                nn[j][1][1] = totalTime[j][1];
                i = 0;
                j++;
            }

        }

        for (i = 0; i < numOfCore; i++) {
//            efficiency[i] = (nn[i][0][1] - nn[i][1][1]) / (nn[i][0][0] - nn[i][1][0]);
            efficiency[i] = (nn[i][0][1] / nn[i][0][0] + nn[i][1][1] / nn[i][1][0]) / 2;
        }


        return efficiency;//最后还是转成小数点；
    }

//    private double[] getTotalTIme(List<String> seconds_total) {
//        double[] result = new double[2];
//        for (String line : seconds_total) {
//
//
//        }
//    }

}
