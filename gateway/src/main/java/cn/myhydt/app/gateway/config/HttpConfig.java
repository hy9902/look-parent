package cn.myhydt.app.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.DispatcherHandler;

/**
 * @author hy9902
 * @create 2018-08-16 15:00
 */
@Configuration
@Slf4j
public class HttpConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
