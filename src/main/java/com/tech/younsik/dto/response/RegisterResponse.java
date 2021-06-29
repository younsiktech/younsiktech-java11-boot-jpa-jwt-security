package com.tech.younsik.dto.response;

import com.tech.younsik.constant.AuthConst;
import com.tech.younsik.dto.object.UserObject;
import lombok.Data;

@Data
public class RegisterResponse {
    private Long id;
    private String email;
    private String nickname;
    private AuthConst.Role role;

    public RegisterResponse(UserObject userObject) {
        this.id = userObject.getId();
        this.email = userObject.getEmail();
        this.nickname = userObject.getNickname();
        this.role = userObject.getRole();
    }
}
