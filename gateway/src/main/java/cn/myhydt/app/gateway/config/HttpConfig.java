package cn.myhydt.app.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
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

    @Bean
    RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(100, 200);
    }
}
