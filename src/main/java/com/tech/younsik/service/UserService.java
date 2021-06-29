package com.tech.younsik.service;

import com.tech.younsik.constant.AuthConst;
import com.tech.younsik.constant.UserConst.Status;
import com.tech.younsik.dto.object.RegisterObject;
import com.tech.younsik.dto.object.UserObject;
import com.tech.younsik.entity.User;
import com.tech.younsik.repository.UserRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public UserObject createUser(RegisterObject registerObject) {
        Optional<User> optionalUser = userRepository.findByEmail(registerObject.getEmail());
        
        if(optionalUser.isPresent()) {
            log.error("User exist");
        }

        User user = userRepository.saveAndFlush(
            User.builder()
                .email(registerObject.getEmail())
                .password(passwordEncoder.encode(registerObject.getPassword()))
                .name(registerObject.getName())
                .nickname(registerObject.getNickname())
                .gender(registerObject.getGender())
                .phoneNumber(registerObject.getPhoneNumber())
                .role(AuthConst.Role.USER)
                .status(Status.ACTIVE)
                .build());
        
        return user.toObject();
    }

    @Transactional(readOnly = true)
    public UserObject selectUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isEmpty()) {
            log.error("User not exist");
            throw new RuntimeException("");
        }
        
        User user = optionalUser.get();
        
        return user.toObject();
    }
}
