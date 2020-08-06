package com.air.containeros.service;

import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@Slf4j
public class DockerServiceTest {
    @Autowired
    private DockerService dockerService;
//实际使用的时候，containerID还需要自己摘取，不是定值
    private final String containerID = "d1892a05ac1979d91c93cb9e450c987c6fbc9f493566479d08c4d1fcc81372bf ";


    @Test
    public void test_getImages() {

        for (Image image : dockerService.getImagesList()) {
            System.out.println(image);
        }

    }

    @Test
    public void test_getContainerList() {
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + Arrays.toString(container.getNames())
                    + "  容器描述：" + container.getCommand());
        }
    }

    @Test
    public void test_startContainer() {
        dockerService.startContainer(containerID);
        System.out.println("当前剩余容器");
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + Arrays.toString(container.getNames())
                    + "  容器描述：" + container.getCommand());
        }
    }

    @Test
    public void test_stopContainer() {

        dockerService.stopContainer(containerID);
        System.out.println("当前剩余容器");
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + Arrays.toString(container.getNames())
                    + "  容器描述：" + container.getCommand());
        }
    }

    @Test
    public void test_restartContainer() {
        dockerService.restartContainer(containerID);
        System.out.println("当前剩余容器");
        for (Container container : dockerService.getContainerList()) {
            System.out.println("容器id：" + container.getId()
                    + "  容器名称：" + Arrays.toString(container.getNames())
                    + "  容器描述：" + container.getCommand());
        }
    }

}
