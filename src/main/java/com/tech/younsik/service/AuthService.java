package com.tech.younsik.service;

import com.tech.younsik.config.jwt.JwtAuthProvider;
import com.tech.younsik.config.jwt.JwtUserDetailsService;
import com.tech.younsik.dto.object.LoginObject;
import com.tech.younsik.exception.AuthException;
import com.tech.younsik.exception.AuthException.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtAuthProvider jwtAuthProvider;
    
    @Autowired
    private JwtUserDetailsService userDetailService;
    
    public LoginObject authenticateUser(LoginObject loginObject) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginObject.getEmail(), loginObject.getPassword()));
        } catch (DisabledException | BadCredentialsException e) {
            log.error(e.getMessage());
            throw new AuthException("Authenticate User Error", Type.UNAUTHOIRZED);
        }
        
        final UserDetails userDetails = userDetailService
            .loadUserByUsername(loginObject.getEmail());
        
        final String token = jwtAuthProvider.generateToken(userDetails);
        
        return LoginObject.builder()
            .token(token)
            .build();
    }
}
