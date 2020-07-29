package com.air.containeros.controller;

import com.air.containeros.serivrice.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/image/")



public class ImageController {

    @Autowired
    private DockerService dockerService;

    @GetMapping("images")
    public Map getImageList() {
        return Map.of("imageList",dockerService.getImagesList());
    }
}
