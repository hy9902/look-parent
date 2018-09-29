package cn.myhydt.app.user.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author hy9902
 * @create 2018-09-19 16:15
 */
@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //requestMatcher(EndpointRequest.toAnyEndpoint())
        http.csrf().disable().authorizeRequests()
                .anyRequest().authenticated().and().httpBasic();
    }
}
