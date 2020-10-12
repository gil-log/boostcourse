package com.noryangjin.boostcourse.repository;

import com.noryangjin.boostcourse.domain.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {

    public UserRole findByUser_id(Long user_id);

}
