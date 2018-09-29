package cn.myhydt.app.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页
 *
 * @author hy9902
 * @create 2018-09-19 14:14
 */
@RestController
@Slf4j
public class IndexController {

    @Value("${name:123}")
    private String name;

    @ApiOperation("欢迎界面")
    @RequestMapping({"", "/"})
    public ResponseEntity index(HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.ok("hello!" + name);
    }
}
