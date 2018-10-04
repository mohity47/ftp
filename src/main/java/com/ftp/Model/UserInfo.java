package com.ftp.Model;

import javax.persistence.*;

@Entity(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    Long userId;

    @Column(name = "otp")
    Integer otp;

    @Column(name = "public_key")
    String publicKey;

    @Column(name = "private_key")
    String privateKey;

    @Column(name = "connected_to")
    Long connectedTo;

    @Column(name = "user_name")
    String userName = "";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(Long connectedTo) {
        this.connectedTo = connectedTo;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

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
