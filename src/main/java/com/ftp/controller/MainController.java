package com.ftp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/home")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView app() {
        System.out.println("Request coming here");
        return new ModelAndView("index");
    }

}
