package com.noryangjin.boostcourse.repository.user;

import com.noryangjin.boostcourse.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public String findByEmail(String email);

    public User findById(long id);
}
