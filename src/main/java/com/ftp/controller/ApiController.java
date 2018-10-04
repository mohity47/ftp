package com.ftp.controller;

import com.ftp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @GetMapping("/generateOTP")
    @ResponseBody
    public String generateOTP(HttpServletRequest request,
                              @RequestParam HashMap<String, String> params) {
        Cookie[] cookies = request.getCookies();
        String userId="";
        for(int i=0;i<cookies.length;i++) {
            if(cookies[i].getName().equals("id")) {
                userId = cookies[i].getValue();
            }
        }
        return userService.generateOTP(Long.parseLong(userId));
    }
}
