package com.ftp.controller;

import com.ftp.Model.UserInfo;
import com.ftp.Service.UserService;
import com.ftp.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Optional;


@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserHelper userHelper;

    @GetMapping("/generateOTP")
    @ResponseBody
    public String generateOTP(HttpServletRequest request,
                              @RequestParam HashMap<String, String> params) {
        Long userId = userHelper.getUserIdFromCookies(request);
        return userService.generateOTP(userId);
    }

    @PostMapping("/connect")
    @ResponseBody String connect(HttpServletRequest request,
                                 @RequestBody UserInfo user1) {
        Long userId = userHelper.getUserIdFromCookies(request);
        user1.setUserId(userId);
        int otp = user1.getOtp();
        Optional<UserInfo>  user2 = userService.getUserByOTP(otp);
        String status;
        if(user2.isPresent()) {
            status = "true";
            userService.connectUsers(user1,user2.get());
            userService.connectUsers(user2.get(),user1);
        }
        else {
            status = "false";
        }
        return status;
    }
}
