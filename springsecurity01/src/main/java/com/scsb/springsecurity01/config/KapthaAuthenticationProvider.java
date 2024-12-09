package com.scsb.springsecurity01.config;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

public class KapthaAuthenticationProvider  extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String vcode = request.getParameter("vcode");
        String kaptcha = (String) request.getSession().getAttribute("kaptcha");
        if(!vcode.equals(kaptcha)){
            throw new RuntimeException("Wrong Kaptcha Code");
        }
        return super.authenticate(authentication);
    }
}
