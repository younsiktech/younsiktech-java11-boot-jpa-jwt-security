package com.tech.younsik.dto.object;

import com.tech.younsik.constant.UserConst.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterObject {
    private String name;
    private String nickname;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;
}
