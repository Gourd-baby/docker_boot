package com.atguigu.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2023/5/20 16:52
 * @注释
 */
@SpringBootApplication
@MapperScan("com.atguigu.docker.mapper")
public class DockerBootApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(DockerBootApplication.class, args);
    }

}