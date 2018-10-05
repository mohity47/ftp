package com.ftp.utils;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserHelper {
    public Long getUserIdFromCookies(HttpServletRequest request) {
        String userId = "";
        Cookie[] cookies = request.getCookies();
        for(int i=0;i<cookies.length;i++) {
            if(cookies[i].getName().equals("id")) {
                userId = cookies[i].getValue();
            }
        }
        return Long.parseLong(userId);
    }
}
