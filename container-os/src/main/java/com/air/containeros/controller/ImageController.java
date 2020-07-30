package com.air.containeros.controller;

import com.air.containeros.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("pushimages/{image}")
    public void pushImage(@PathVariable String image) {
        dockerService.pushImage(image);
    }
    @PostMapping("removeimage/{imageid}")
    public void removeImage(@PathVariable String imageID) {
        dockerService.removeImage(imageID);
    }
}
