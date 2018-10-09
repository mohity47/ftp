package com.ftp.dao;

import com.ftp.Model.UserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserInfo,Long> {
    Optional<UserInfo> findByOtp(int otp);
}
