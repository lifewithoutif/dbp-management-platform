package com.hna.dbp.test.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * aop目标类
 */

@RestController
public class AopControllerDemo {
    @RequestMapping("/aop")
    public String test() {
        return "hello aop";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }

}
