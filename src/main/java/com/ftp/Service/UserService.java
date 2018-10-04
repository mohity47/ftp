package com.ftp.Service;


import com.ftp.Model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
