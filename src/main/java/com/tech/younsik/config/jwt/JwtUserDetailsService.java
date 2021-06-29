package com.tech.younsik.config.jwt;

import com.tech.younsik.constant.UserConst.Status;
import com.tech.younsik.service.UserService;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class JwtUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userService.findUserByEmail(email)
            .map(user -> createUser(email, user))
            .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습"));
    }

    private User createUser(String email, com.tech.younsik.entity.User user) {

        if (!Status.ACTIVE.getDbCode().equals(user.getStatus().getDbCode())) {
            throw new RuntimeException(email + " -> 활성화되어 있지 않습니다.");
        }

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().getJsonCode());

        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(simpleGrantedAuthority);

        return new User(user.getEmail(),
            user.getPassword(),
            grantedAuthorities);
    }
}
