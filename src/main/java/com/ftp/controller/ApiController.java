package com.ftp.controller;

import com.ftp.Model.OTP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping("/generateOTP")
    @ResponseBody
    public String generateOTP(@RequestBody OTP request) {
        Long userID = request.getUserId();
        return userID.toString();
    }
}
