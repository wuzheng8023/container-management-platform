package com.air.containeros.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.zip.DeflaterOutputStream;

@SpringBootTest
@Slf4j
public class VMMessageServiceTest {
    @Autowired
    private VMMessageService vmMessageService;

    @Test
    public void test_getMemoryMessage() {
//        System.out.println("---------------");
//        System.out.println(vmMessageService.getMemoryMessage());
//        log.debug("{}",vmMessageService.getMemoryMessage());
//        System.out.println("------------------");
        double i = (double) vmMessageService.getMemoryMessage();
//       Long l = Long.valueOf(vmMessageService.getMemoryMessage());
//        System.out.println(l);
        System.out.println(i);
        BigDecimal bigDecimal = new BigDecimal(i / 1024);
        BigDecimal b = new BigDecimal(1945445.4597);
        System.out.println("b=" + b);
        System.out.println(bigDecimal);

        double d = 123.4564;
        System.out.println(d);

    }

    @Test
    public void test_getCPUefficiency() {

        for (double d : vmMessageService.getCPUefficiency()) {
//            System.out.println("CPU利用率：" + d);

            String temp = String.format("%.2f",d*100) + "%";
            System.out.println("temp:" + temp);
        }

    }
}
