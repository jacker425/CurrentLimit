package com.hkl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.hkl.slide.window.SlideWindow;
import com.hkl.slide.window.SlideWindowLimit;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/*
 * @ClassName controller
 * @Description 标注自定义注解 @SlideWindowLimit的进行限流
 * @Author jacker425
 * @Date 2021/4/7 上午10:45
 */
@RestController
@Slf4j
public class MyController {

    @Autowired
    SlideWindow slideWindow;

    @SlideWindowLimit
    @RequestMapping(value = "/v1/cese", method = RequestMethod.GET)
    public String cese(){
        log.info("use /v1/cese");
        return "success";
    }

    @SlideWindowLimit
    @RequestMapping(value = "/v1/cese2", method = RequestMethod.GET)
    public String cese2(){
        log.info("use /v1/cese2");
        return "success";
    }
}
