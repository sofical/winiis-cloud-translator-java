package com.winiis.cloud.translator.demo.controller;

import com.winiis.cloud.translator.core.annotation.Sign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWorld.
 *
 * @author zj.
 *         Created on 2019/4/1 0001.
 */
@RestController
@RequestMapping("/demo")
public class HelloWorld {
    @Sign
    @GetMapping("")
    public String hello() {
        return "hello world!";
    }
}
