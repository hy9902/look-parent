package cn.myhydt.app.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author hy9902
 * @create 2018-08-16 17:28
 */
@RestController
@Slf4j
public class IndexController {

    @Value("${name: gateway}")
    private String name;

    @RequestMapping("/")
    public String index(){
        return name;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    //@PreAuthorize("authenticated")
    public String hello(Principal principal){
        if(principal == null){
            return "hello " + name;
        } else {
            return "Hello "+principal.getName();
        }

    }
}
