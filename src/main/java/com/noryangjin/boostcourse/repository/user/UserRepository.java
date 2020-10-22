package com.noryangjin.boostcourse.repository.user;

import com.noryangjin.boostcourse.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public String findByEmail(String email);
}
