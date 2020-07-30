package com.air.containeros.service;


import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DockerService {

    @Autowired
//    private DockerConnection dockerConnection;
    private DockerClient dockerClient;

    /**
     * java调用docker api进行镜像的上传（docker load)
     * 容器的启动（docker run）、停止（docker stop）、删除（docker rm）、重启（docker restart）
     */

    /**
     * 获取镜像列表
     *
     * @return
     */
    public List<Image> getImagesList() {

        return dockerClient.listImagesCmd().exec();
//        return dockerConnection.getDockerClient().listImagesCmd().exec();
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
     * @param containerID 容器ID
     */
    public void removeContainer(String containerID) {
        dockerClient.removeContainerCmd(containerID).exec();
    }


}


