package cn.myhydt.app.config.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hy9902
 * @create 2018-08-15 16:35
 */
@RestController
@Slf4j
public class IndexController {

    @Value("${name:xx}")
    private String name;

    @RequestMapping("/")
    public String index(){
        return name;
    }
}
