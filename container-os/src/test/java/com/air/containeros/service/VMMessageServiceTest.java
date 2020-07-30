package com.air.containeros.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

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
        BigDecimal bigDecimal = new BigDecimal(i/1024);
        System.out.println(bigDecimal);

    }
}
