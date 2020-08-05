package com.air.containeros.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
public class VMMessageServiceTest {
    @Autowired
    private VMMessageService vmMessageService;

    @Test
    public void test_getMemoryUtilization() {

        System.out.println(vmMessageService.getAvailMemory());
        System.out.println(vmMessageService.getTotalMemory());
        System.out.println(vmMessageService.getMemoryRate());

    }

    @Test
    public void test_getCPURate() {

//        for (double d : vmMessageService.getCPURate()) {
        double d = vmMessageService.getCPURate();
        String temp = String.format("%.2f", d * 100) + "%";
        System.out.println("temp:" + temp);
//        }

    }

    @Test
    public void test_getTotalDisk() {

        System.out.println("总磁盘大小：" + vmMessageService.getTotalDisk());
    }

    @Test
    public void test_getAvailDisk() {

        System.out.println("剩余磁盘大小：" + vmMessageService.getAvailDisk());
    }


}
