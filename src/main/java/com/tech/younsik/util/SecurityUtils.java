package com.tech.younsik.util;

import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    
    public static Optional<String> getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
        
        if (authentication == null) {
            return Optional.empty();
        }
        
        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }
        
        return Optional.ofNullable(username);
    }
}
