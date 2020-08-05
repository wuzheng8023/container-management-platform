package com.air.containeros.service;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

/**
 * 对于docker容器管理，含有功能：
 * 镜像部分：获取镜像、删除镜像、上传镜像；
 * 容器部分：获取所有容器、创建容器、启动容器、停止容器、重启容器、删除容器
 */
@Service
public class DockerService {

    @Autowired
    private DockerClient dockerClient;

    /**
     * 获取镜像列表
     *
     * @return
     */
    public List<Image> getImagesList() {

        return dockerClient.listImagesCmd().exec();
    }

    /**
     * 上传镜像
     *
     * @param image
     */
    public void pushImage(String image) {


        dockerClient.pushImageCmd(image);
    }


    /**
     * 删除镜像
     *
     * @param imageID 镜像ID
     */
    public void removeImage(String imageID) {
        dockerClient.removeImageCmd(imageID).exec();
    }

    /**
     * 上传文件形成镜像
     */
    public void loadImages(InputStream inputStream) {
        dockerClient.loadImageCmd(inputStream);

    }


    /**
     * 得到所有容器
     *
     * @return
     */


    public List<Container> getContainerList() {
        return dockerClient.listContainersCmd().exec();
    }


    /**
     * 创建容器
     *
     * @param imageID 镜像ID
     */
    public void createContainer(String imageID) {

        dockerClient.createContainerCmd(imageID).exec();
    }


    /**
     * 启动容器
     *
     * @param containerID 容器ID
     */
    public void startContainer(String containerID) {

        dockerClient.startContainerCmd(containerID).exec();
    }

    /**
     * 停止容器
     *
     * @param containerID 容器ID
     */
    public void stopContainer(String containerID) {
        dockerClient.stopContainerCmd(containerID).exec();
    }

    /**
     * 重启容器
     *
     * @param containerID 容器id
     */
    public void restartContainer(String containerID) {
        dockerClient.restartContainerCmd(containerID).exec();

    }


    /**
     * 删除容器
     *
     * @param containerID 容器ID
     */
    public void removeContainer(String containerID) {
        dockerClient.removeContainerCmd(containerID).exec();
    }


}


