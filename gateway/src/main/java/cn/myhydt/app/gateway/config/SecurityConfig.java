package cn.myhydt.app.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置类
 *
 * @author hy9902
 * @create 2018-08-16 11:36
 */
@Configuration
@Slf4j
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void init(WebSecurity web) throws Exception {

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.addSecurityFilterChainBuilder(getHttp());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    /*    http//.csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/resources/**", "/login", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().permitAll()
                .and()
                // ...
                .formLogin();*/


    }
}
