package com.scsb.springsecurity01.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private String username;

    private String password;

    private String permission;

    private LocalDate accountValidityPeriod;

    public User() {
    }

    public User(String username, String password, String permission, LocalDate accountValidityPeriod) {
        this.username = username;
        this.password = password;
        this.permission = permission;
        this.accountValidityPeriod = accountValidityPeriod;
    }
}
