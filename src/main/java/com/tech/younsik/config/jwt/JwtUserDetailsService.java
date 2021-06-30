package com.tech.younsik.config.jwt;

import com.tech.younsik.constant.UserConst.Status;
import com.tech.younsik.exception.UserException;
import com.tech.younsik.exception.UserException.Type;
import com.tech.younsik.service.UserService;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component("userDetailsService")
public class JwtUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserService userService;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findUserByEmail(email)
            .map(user -> createUser(email, user))
            .orElseThrow(() -> new UserException(String.format("User %s not found", email),
                Type.USER_NOT_FOUND));
    }
    
    private User createUser(String email, com.tech.younsik.entity.User user) {
        
        if (!Status.ACTIVE.getDbCode().equals(user.getStatus().getDbCode())) {
            log.error(String.format("User %s status wrong", user.getEmail()));
            throw new UserException(String.format("User %s status wrong", user.getEmail()),
                Type.INVALID_STATUS);
        }
        
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(
            user.getRole().getJsonCode());
        
        List<GrantedAuthority> grantedAuthorities = Collections
            .singletonList(simpleGrantedAuthority);
        
        return new User(user.getEmail(),
            user.getPassword(),
            grantedAuthorities);
    }
}
