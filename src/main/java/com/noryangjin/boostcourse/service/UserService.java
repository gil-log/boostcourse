package com.noryangjin.boostcourse.service;

import com.noryangjin.boostcourse.domain.User;
import com.noryangjin.boostcourse.dto.UserDTO;
import com.noryangjin.boostcourse.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public String checkEmail(String email){
        logger.info("checkEmail() 실행");

        String userResult = userRepository.findByEmail(email).get().getEmail();

        return userResult;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //https://victorydntmd.tistory.com/328
        Optional<User> userDtoWrapper = userRepository.findByEmail(email);




        return null;
    }
}
