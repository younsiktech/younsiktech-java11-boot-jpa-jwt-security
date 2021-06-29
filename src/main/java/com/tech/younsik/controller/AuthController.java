package com.tech.younsik.controller;

import com.tech.younsik.dto.object.LoginObject;
import com.tech.younsik.dto.request.LoginRequest;
import com.tech.younsik.dto.response.LoginResponse;
import com.tech.younsik.service.AuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("controller.AuthController")
@RequestMapping("/auth-api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/v1/login")
    public LoginResponse loginUserV1(@Valid @RequestBody LoginRequest loginRequest) {
        return new LoginResponse(login(loginRequest));
    }

    private LoginObject login(LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest.toObject());
    }

}
