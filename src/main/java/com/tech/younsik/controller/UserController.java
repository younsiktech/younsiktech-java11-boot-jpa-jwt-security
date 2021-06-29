package com.tech.younsik.controller;

import com.tech.younsik.dto.object.UserObject;
import com.tech.younsik.dto.request.RegisterRequest;
import com.tech.younsik.dto.response.RegisterResponse;
import com.tech.younsik.dto.response.UserResponse;
import com.tech.younsik.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("controller.UserController")
@RequestMapping("/user-api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/v1/register")
    public RegisterResponse createUserV1(@Valid @RequestBody RegisterRequest registerRequest) {
        return new RegisterResponse(register(registerRequest));
    }

    private UserObject register(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest.toObject());
    }
    
    @GetMapping(path = "/v1/user/{userId}")
    public UserResponse selectUserV1(@PathVariable Long userId) {
        return new UserResponse(select(userId));
    }
    
    private UserObject select(Long userId) {
        return userService.selectUser(userId);
    }
}
