package com.air.containeros.controller;

import com.air.containeros.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 容器管理
 */
@RestController
@RequestMapping("/api/container/")
public class ContainerController {

    @Autowired
    private DockerService dockerService;

    /**
     * 获取容器列表
     * @return
     */
    @GetMapping("containers")
    public Map getImageList() {
        return Map.of("containerList", dockerService.getContainerList());
    }

    /**
     * 创建一个容器
     * @param imageid
     */
    @GetMapping("createcontainer/{imageid}")
    public void createContainer(@PathVariable String imageid) {
        dockerService.createContainer(imageid);
    }

    /**
     * 启动一个容器
     * @param containerID
     */
    @GetMapping("startcontainer/{containerID}")
    public void startContainer(@PathVariable String containerID) {
        dockerService.startContainer(containerID);
    }

    /**
     * 停止容器
     * @param containerID
     */
    @GetMapping("stopcontainer/{containerID}")
    public void stopContainer(@PathVariable String containerID) {
        dockerService.stopContainer(containerID);
    }

    /**
     * 重启容器
     * @param containerID
     */
    @GetMapping("restartcontainer/{containerID}")
    public void restartContainer(@PathVariable String containerID) {
        dockerService.restartContainer(containerID);
    }

    /**
     * 移除容器
     * @param containerID
     */
    @GetMapping("removecontainer/{containerID}")
    public void removeContainer(@PathVariable String containerID) {
        dockerService.removeContainer(containerID);
    }


}
