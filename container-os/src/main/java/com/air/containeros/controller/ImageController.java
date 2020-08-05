package com.air.containeros.controller;

import com.air.containeros.service.DockerService;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 镜像管理
 */

@RestController
@RequestMapping("/api/image/")
public class ImageController {

    @Autowired
    private DockerService dockerService;

    /**
     * 上传文件形成镜像
     *
     * @return
     */
    @PostMapping("loadimages")
    public void upload(MultipartFile file) {//获取upload文件夹的路径         

        try {
//            System.out.println( file.getInputStream());
            dockerService.loadImages(file.getInputStream());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "上传文件有问题！");
        }

    }

    /**
     * 获取所有镜像
     *
     * @return
     */
    @GetMapping("images")
    public Map getImageList() {
        return Map.of("imageList", dockerService.getImagesList());
    }

    /**
     * 上传镜像
     *
     * @param image
     */
    @PostMapping("pushimages/{image}")
    public void pushImage(@PathVariable String image) {
        dockerService.pushImage(image);
    }

    /**
     * 删除指定id的镜像
     *
     * @param imageID
     */
    @GetMapping("removeimage/{imageid}")
    public void removeImage(@PathVariable String imageID) {
        dockerService.removeImage(imageID);
    }

}
