package com.scsb.springsecurity01.sercive;

import com.scsb.springsecurity01.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserByUsername(String username);
}
