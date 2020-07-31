package com.air.containeros.controller;

import com.air.containeros.service.VMMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/metrics/")
public class VMMetricsController {
    @Autowired
    private VMMessageService vmMessageService;

    @GetMapping("cpuefficiency")
    public Map getCPUefficiency() {

        List<String> efficiency = new ArrayList<>();
        for (double d : vmMessageService.getCPUefficiency())

            efficiency.add(String.format("%.2f", d * 100).concat("%"));

        return Map.of("CPUefficiency", efficiency);
    }
}
