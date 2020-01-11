package com.li.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-11 14:52
 */

@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView hello(String name){
        return new ModelAndView("hello","name",name);
    }
}
