package com.ftp.Model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "otp_list")
public class OTP {

    @Column(name = "user_id")
    Long userId;

    @Column(name = "otp")
    Integer otp;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
}
