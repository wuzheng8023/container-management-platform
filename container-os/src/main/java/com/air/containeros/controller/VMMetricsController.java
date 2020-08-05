package com.air.containeros.controller;

import com.air.containeros.service.VMMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/metrics/")
public class VMMetricsController {
    @Autowired
    private VMMessageService vmMessageService;

    /**
     * 得到总的物理内存
     *
     * @return
     */
    @GetMapping("memorytotal")
    public Map getTotalMemory() {
        String total = String.format("%.2f", vmMessageService.getTotalMemory());
        return Map.of("memorytotal", total);
    }

    /**
     * 剩余可用物理内存
     *
     * @return
     */
    @GetMapping("memoryavail")
    public Map getAvailMemory() {
        String avail = String.format("%.2f", vmMessageService.getAvailMemory());
        return Map.of("memoryavail", avail);
    }

    /**
     * 内存使用率
     *
     * @return
     */
    @GetMapping("memoryrate")
    public Map getMemoryRate() {
        String rate = String.format("%.2f", vmMessageService.getMemoryRate() * 100).concat("%");
        return Map.of("memoryrate", rate);

    }

    /**
     * 磁盘总容量
     *
     * @return
     */
    @GetMapping("disktotal")
    public Map getTotalDisk() {
        String total = String.format("%.2f", vmMessageService.getTotalDisk());
        return Map.of("disktotal", total);

    }

    /**
     * 磁盘可用容量
     *
     * @return
     */
    @GetMapping("diskavail")
    public Map getAvailDisk() {
        String avail = String.format("%.2f", vmMessageService.getAvailDisk());
        return Map.of("diskavail", avail);
    }

    /**
     * 磁盘使用率
     *
     * @return
     */
    @GetMapping("diskrate")
    public Map getDiskRate() {
        String rate = String.format("%.2f", vmMessageService.getDiskRate() * 100).concat("%");
        return Map.of("diskrate", rate);

    }

    /**
     * cpu使用率
     *
     * @return
     */
    @GetMapping("cpurate")
    public Map getCPUefficiency() {

        double d = vmMessageService.getCPURate();
        System.out.println(d);
        return Map.of("CPURate", String.format("%.2f", d * 100).concat("%"));

    }


}