package com.ftp.dao.service;


import com.ftp.Model.UserInfo;
import com.ftp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserInfo addNewUser() {
        UserInfo userInfo = new UserInfo();
        userRepository.save(userInfo);
        return userInfo;
    }

    public String generateOTP(Long userId) {
        Integer otp = 1000 + (int)(Math.random() * 9000);
        Optional<UserInfo> userInfo = userRepository.findById(userId);

        userInfo.ifPresent(uInfo -> {
            uInfo.setOtp(otp);
            userRepository.save(uInfo);
        });
        return otp.toString();
    }

    public Optional<UserInfo> getUserByOTP(int otp) {
        return userRepository.findByOtp(otp);
    }

    public void connectUsers(UserInfo user1,UserInfo user2) {
        user1.setConnectedTo(user2.getUserId());
        userRepository.save(user1);
    }

    public void addSessionId(Long userId,String sessionId) {
        Optional<UserInfo> user = userRepository.findById(userId);
        user.ifPresent(u -> {
            u.setSessionId(sessionId);
            userRepository.save(u);
        });
    }

    public String getRecepientSessionId(Long userId) {
        Optional<UserInfo> userInfo = userRepository.findById(userId);
        if( userInfo.isPresent() ){
            Long rId = userInfo.get().getConnectedTo();
            Optional<UserInfo> rInfo = userRepository.findById(rId);
            if(rInfo.isPresent()) {
                return rInfo.get().getSessionId();
            }
        }
        return "";
    }

    public Optional<UserInfo> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
}
