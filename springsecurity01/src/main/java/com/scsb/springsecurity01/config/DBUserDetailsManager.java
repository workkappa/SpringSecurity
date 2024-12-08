package com.scsb.springsecurity01.config;

import com.scsb.springsecurity01.entity.User;
import com.scsb.springsecurity01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find User Data
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Check if account has expired
        if (user.getAccountValidityPeriod().isBefore(LocalDate.now())) {
            throw new UsernameNotFoundException("Account expired");
        }


        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // Return UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true, // enabled user
                !user.getAccountValidityPeriod().isBefore(LocalDate.now()), // account not expired
                true, // credentials not expired
                true, // account not Locked
                authorities
        );
    }
}
