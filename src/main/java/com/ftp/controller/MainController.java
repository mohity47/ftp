package com.ftp.controller;

import com.ftp.Model.UserInfo;
import com.ftp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping(path = "/ftp")
public class MainController {

    @Autowired
    private UserService userService;

    MainController(UserService userService) {
        this.userService = userService;
    }

    //cookie management when directly comes to another page
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView app(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam HashMap<String,String> param) {
        Cookie[] cookies = request.getCookies();
        if( cookies == null ) {
            UserInfo userInfo = userService.addNewUser();
            Cookie cookie = new Cookie("id",userInfo.getUserId().toString());
            cookie.setMaxAge(3600000);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return new ModelAndView("index");
    }

    @GetMapping("/connect")
    @ResponseBody void connect() {

    }

}
