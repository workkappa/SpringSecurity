package com.scsb.springsecurity01.repository;

import com.scsb.springsecurity01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User getUserByUsername(String username);
}
