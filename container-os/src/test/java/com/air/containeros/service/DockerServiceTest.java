package com.air.containeros.service;

import com.air.containeros.serivrice.DockerService;

import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DockerServiceTest {
    @Autowired
    private DockerService dockerService;

    private final String containerID = "a11b8a3fb0b22cbd2bb6e6d429a754328f0b8205675a80d000aa4aed894362d1";


    @Test
    public void test_getImages() {

        for (Image image : dockerService.getImagesList()) {
            System.out.println(image);

//            System.out.println(image.getCreated());

        }

    }

    @Test
    public void test_getContainerList() {
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + container.getNames()
                    + "  容器描述：" + container.getCommand());
        }
    }

    @Test
    public void test_startContainer() {
        dockerService.startContainer(containerID);
        System.out.println("当前剩余容器");
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + container.getNames()
                    + "  容器描述：" + container.getCommand());
        }
    }

    @Test
    public void test_stopContainer() {

        dockerService.stopContainer(containerID);
        System.out.println("当前剩余容器");
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + container.getNames()
                    + "  容器描述：" + container.getCommand());
        }
    }

    @Test
    public void test_restartContainer() {
        dockerService.restartContainer(containerID);
        System.out.println("当前剩余容器");
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + container.getNames()
                    + "  容器描述：" + container.getCommand());
        }
    }


}
