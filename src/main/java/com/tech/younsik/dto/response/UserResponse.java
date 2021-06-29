package com.tech.younsik.dto.response;

import com.tech.younsik.constant.AuthConst;
import com.tech.younsik.constant.UserConst;
import com.tech.younsik.dto.object.UserObject;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserResponse {
    
    private Long id;
    
    private String email;
    
    private String name;
    
    private String nickname;
    
    private String phoneNumber;
    
    private AuthConst.Role role;

    private UserConst.Gender gender;
    
    private LocalDateTime updatedAt;

    public UserResponse(UserObject userObject) {
        this.id = userObject.getId();
        this.email = userObject.getEmail();
        this.name = userObject.getName();
        this.nickname = userObject.getNickname();
        this.phoneNumber = userObject.getPhoneNumber();
        this.gender = userObject.getGender();
        this.role = userObject.getRole();
        this.updatedAt = userObject.getUpdatedAt();
    }
}
