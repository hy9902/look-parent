package cn.myhydt.app.oauth.server;

import cn.myhydt.app.commonservice.utils.HyUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hy9902
 * @create 2018-09-25 15:51
 */
@SpringBootApplication
@EnableEurekaClient
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
        HyUtil.shutdownDelay(5);
    }
}
