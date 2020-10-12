package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.User;
import com.noryangjin.boostcourse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepo;

    public User checkEmail(String email){
        logger.info("checkEmail() 실행");

        User userResult = userRepo.findByEmail(email);

        return userResult;
    }
}
