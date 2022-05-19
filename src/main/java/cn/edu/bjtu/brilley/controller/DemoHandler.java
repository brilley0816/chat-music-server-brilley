package cn.edu.bjtu.brilley.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Brilley
 * @date 2022/5/18
 */
@RestController
public class DemoHandler {


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
