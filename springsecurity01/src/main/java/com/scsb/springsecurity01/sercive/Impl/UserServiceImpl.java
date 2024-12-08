package com.scsb.springsecurity01.sercive.Impl;

import com.scsb.springsecurity01.entity.User;
import com.scsb.springsecurity01.repository.UserRepository;
import com.scsb.springsecurity01.sercive.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findById(username).orElse(null);
    }

}
