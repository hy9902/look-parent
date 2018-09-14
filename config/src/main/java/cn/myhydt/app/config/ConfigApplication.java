package cn.myhydt.app.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 *
 * #{…} 用于执行SpEl表达式，并将内容赋值给属性
 * ${…} 主要用于加载外部属性文件中的值
 * #{…} 和${…} 可以混合使用，但是必须#{}外面，${}在里面
 *
 * build-tools;28.0.2
 */


@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}