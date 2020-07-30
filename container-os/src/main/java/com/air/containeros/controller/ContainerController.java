package com.air.containeros.controller;

import com.air.containeros.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/container/")
public class ContainerController {

    @Autowired
    private DockerService dockerService;

    @GetMapping("containers")
    public Map getImageList() {
        return Map.of("containerList", dockerService.getImagesList());
    }

    @PostMapping("createcontainer/{imageid}")
    public void createContainer(@PathVariable String imageid) {
        dockerService.createContainer(imageid);
    }

    @PostMapping("startcontainer/{containerID}")
    public void startContainer(@PathVariable String containerID) {
        dockerService.startContainer(containerID);
    }

    @PostMapping("stopcontainer/{containerID}")
    public void stopContainer(@PathVariable String containerID) {
        dockerService.stopContainer(containerID);
    }

    @PostMapping("restartcontainer/{containerID}")
    public void restartContainer(@PathVariable String containerID) {
        dockerService.restartContainer(containerID);
    }

    @PostMapping("removecontainer/{containerID}")
    public void removeContainer(@PathVariable String containerID) {
        dockerService.removeContainer(containerID);
    }


}
