package com.air.containeros.untils;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DockerConnection {

//新方法，单一对象
    @Bean
    public DockerClient getDockerClient(){return DockerClientBuilder.getInstance("tcp://192.168.43.19:2375").build();}
//原方法，反复创建
//    public DockerClient getDockerClient(){
//        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.43.19:2375").build();
//        return dockerClient;
//
//    }
   ;
}
