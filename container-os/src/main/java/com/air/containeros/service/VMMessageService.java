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

    /**
     * 总的物理内存
     *
     * @return
     */
    public double getTotalMemory() {
        List<String> metricsList = vmMetrics.getVMMetrics();
        int i = 0;
        double total = 0;

        for (String line : metricsList) {
            if (line.contains(VMMetrics.MEMORY__MEMTOTAL)) { //依据特征字符串找到指定段落
                i++;
                if (i == 3) {//找到指定数据段落
                    String s1 = "\\+";  //记录隔离符号
                    String s2 = line.split(" ")[1];  //拆分获取数字部分（含有e和+符号）
                    String s3 = s2.split(s1)[1];  //拆分获取+号后面的数字部分
                    String s4 = s2.split("e")[0];//拆分获取e前面的数字部分
                    total = Double.parseDouble(s4) * Math.pow(10, Double.parseDouble(s3));
                }
            }
        }
        return total / 1024 / 1024 / 1024;
    }

    /**
     * 剩余可用物理内存
     *
     * @return
     */
    public double getAvailMemory() {
        List<String> metricsList = vmMetrics.getVMMetrics();
        int i = 0;
        double available = 0;
        for (String line : metricsList) {
            if (line.contains(VMMetrics.MEMORY__MEMAVAILABLE)) { //依据特征字符串找到指定段落
                i++;
                if (i == 3) {//找到指定数据段落
                    String s1 = "\\+";  //记录隔离符号
                    String s2 = line.split(" ")[1];  //拆分获取数字部分（含有e和+符号）
                    String s3 = s2.split(s1)[1];  //拆分获取+号后面的数字部分
                    String s4 = s2.split("e")[0];//拆分获取e前面的数字部分
                    available = Double.parseDouble(s4) * Math.pow(10, Double.parseDouble(s3));
                }
            }
        }
        return available / 1024 / 1024 / 1024;
    }

    /**
     * 物理内存使用率
     *
     * @return
     */
    public double getMemoryRate() {
        double total = getTotalMemory();
        double available = getAvailMemory();
        return 1 - (available / total);
    }

    /**
     * 总磁盘大小
     *
     * @return
     */
    public double getTotalDisk() {
        List<String> metricsList = vmMetrics.getVMMetrics();
        int i = 0;
        double total = 0;
        for (String line : metricsList) {
            if (line.contains(VMMetrics.DISK_TOTAL) && (line.contains(VMMetrics.DISK_FSTYPE1) || line.contains(VMMetrics.DISK_FSTYPE2))) { //依据特征字符串找到指定段落
                String s1 = "\\+";  //记录隔离符号
                String s2 = line.split(" ")[1];  //拆分获取数字部分（含有e和+符号）
                String s3 = s2.split(s1)[1];  //拆分获取+号后面的数字部分
                String s4 = s2.split("e")[0];//拆分获取e前面的数字部分
                total += Double.parseDouble(s4) * Math.pow(10, Double.parseDouble(s3));
            }
        }
        return total / 1024 / 1024 / 1024;

    }

    /**
     * 剩余可用磁盘大小
     *
     * @return
     */

    public double getAvailDisk() {
        List<String> metricsList = vmMetrics.getVMMetrics();
        int i = 0;
        double available = 0;
        for (String line : metricsList) {
            if (line.contains(VMMetrics.DISK_AVAIL) && (line.contains(VMMetrics.DISK_FSTYPE1) || line.contains(VMMetrics.DISK_FSTYPE2))) { //依据特征字符串找到指定段落
                String s1 = "\\+";  //记录隔离符号
                String s2 = line.split(" ")[1];  //拆分获取数字部分（含有e和+符号）
                String s3 = s2.split(s1)[1];  //拆分获取+号后面的数字部分
                String s4 = s2.split("e")[0];//拆分获取e前面的数字部分
                available += Double.parseDouble(s4) * Math.pow(10, Double.parseDouble(s3));
            }
        }
        return available / 1024 / 1024 / 1024;

    }

    /**
     * 得到磁盘使用率
     *
     * @return
     */
    public double getDiskRate() {
        return 1 - (getAvailDisk() / getTotalDisk());
    }


    /**
     * 获取CPU利用率；将多核CPU的使用率返回一个平均值
     *
     * @return
     */
    public double getCPURate() {
        List<String> metricsList1 = vmMetrics.getVMMetrics();
        List<String> metricsList2 = vmMetrics.getVMMetrics();
        List<String> seconds_total1 = new ArrayList<>();
        List<String> seconds_total2 = new ArrayList<>();
        int i = 0;
//获取CPU信息中的数字部分,向VM发两次请求，获取两次metric数据，这样做的目的是为了提高准确度；
        //第一次获取metric
        for (String line : metricsList1) {

            if (line.contains(VMMetrics.CPU)) { //依据特征字符串找到指定段落
                i++;
                if (i <= 2) {
                    continue;
                }
                seconds_total1.add(line.split(" ")[1]);
            }
        }
        i = 0;
        //第二次获取metric
        for (String line : metricsList2) {

            if (line.contains(VMMetrics.CPU)) { //依据特征字符串找到指定段落
                i++;
                if (i <= 2) {
                    continue;
                }
                seconds_total2.add(line.split(" ")[1]);
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
//获得每个CPU内核的使用率
        for (i = 0; i < numOfCore; i++) {
//            efficiency[i] = (nn[i][0][1] - nn[i][1][1]) / (nn[i][0][0] - nn[i][1][0]);
            efficiency[i] = (nn[i][0][1] / nn[i][0][0] + nn[i][1][1] / nn[i][1][0]) / 2;
        }
        double temp = 0;

        for (double z : efficiency) {
            temp += z;
        }
        return temp / efficiency.length;
//        return efficiency;//最后还是转成小数点；
    }

}
