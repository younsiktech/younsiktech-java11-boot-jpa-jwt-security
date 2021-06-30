package com.tech.younsik.controller;

import com.tech.younsik.dto.object.UserObject;
import com.tech.younsik.dto.request.RegisterRequest;
import com.tech.younsik.dto.response.RegisterResponse;
import com.tech.younsik.dto.response.UserResponse;
import com.tech.younsik.exception.UserException;
import com.tech.younsik.exception.UserException.Type;
import com.tech.younsik.service.UserService;
import com.tech.younsik.util.SecurityUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
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
    @ApiOperation(value = "유저 가입", notes = "회원 가입")
    public RegisterResponse createUserV1(@Valid @RequestBody RegisterRequest registerRequest) {
        return new RegisterResponse(register(registerRequest));
    }

    private UserObject register(RegisterRequest registerRequest) {
        return userService.createUser(registerRequest.toObject());
    }
    
    @GetMapping(path = "/v1/user/{userId}")
    @ApiOperation(value = "유저 정보 가져오기", notes = "주어진 userId의 정보를 가져온다.")
    @ApiImplicitParam(name = "userId", value = "유저 아이디",paramType = "path", required = true)
    public UserResponse selectUserV1(@PathVariable Long userId) {
    
        Optional<String> optionalCurrentUser = SecurityUtils.getCurrentUsername();
    
        if(optionalCurrentUser.isEmpty()) {
            throw new UserException("User not found", Type.USER_NOT_FOUND);
        }
        
        return new UserResponse(select(userId, optionalCurrentUser.get()));
    }
    
    private UserObject select(Long userId, String email) {
        return userService.selectUser(userId, email);
    }
}
