package cn.myhydt.app.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 用户应用启动类
 *
 * @author hy9902
 * @create 2018-09-06 9:35
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@EnableHystrixDashboard
public class UserApplication {
}
