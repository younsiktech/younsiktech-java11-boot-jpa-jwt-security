package com.tech.younsik.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tech.younsik.constant.UserConst.Gender;
import com.tech.younsik.dto.object.RegisterObject;
import com.tech.younsik.validator.Password;
import com.tech.younsik.validator.PhoneNumber;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterRequest {
    @NotNull(message = "Wrong name : Name is empty")
    @Size(max = 20, message = "Wrong name : 최대 길이 20")
    private String name;

    @NotNull(message = "Wrong nickname : nickname is empty")
    @Size(max = 30, message = "Wrong nickname : 최대 길이 30")
    private String nickname;

    @NotNull(message = "Wrong e-mail : E-mail is empty")
    @Size(max = 100, message = "Wrong e-mail : 최대 길이 100")
    @Email(message = "Wrong e-mail : 이메일 형식")
    private String email;

    @NotNull(message = "Wrong password : Password is empty")
    @Password(min = 10, message = "Wrong password : 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함, 최소 길이 10")
    private String password;

    @NotNull(message = "Wrong phone : phone is empty")
    @PhoneNumber(message = "Wrong phone : 전화, 핸드폰 번호 형식")
    private String phoneNumber;

    private Gender gender;

    public RegisterObject toObject() {
        if (gender == null) {
            gender = Gender.UNDEFINED;
        }
        return RegisterObject.builder()
            .email(email)
            .password(password)
            .name(name)
            .nickname(nickname)
            .phoneNumber(phoneNumber)
            .gender(gender)
            .build();
    }
}
