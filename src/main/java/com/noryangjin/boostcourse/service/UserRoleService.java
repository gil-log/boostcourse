package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.UserRole;
import com.noryangjin.boostcourse.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRoleRepository userRoleRepo;

    public UserRole checkUserRole(Long user_id) {

        logger.info("UserRoleService > checkUserRole() 실행 ");

        UserRole userRole = userRoleRepo.findByUser_id(user_id);

        return userRole;
    }

}
