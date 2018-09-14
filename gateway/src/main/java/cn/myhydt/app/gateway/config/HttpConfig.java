package cn.myhydt.app.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hy9902
 * @create 2018-08-16 15:00
 */
@Configuration
@Slf4j
public class HttpConfig {

    /*@Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }*/

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(100, 200);
    }

    @Bean
    public RouteLocator customRouteLocator(){
        RouteLocatorBuilder.Builder routeLocatorBuilder = new RouteLocatorBuilder(applicationContext).routes();
        routeLocatorBuilder.route("path_route", t -> t.path("/path").uri("http://"))
                .route("", t -> t.header("").uri(""));
        return routeLocatorBuilder.build();
    }

}
