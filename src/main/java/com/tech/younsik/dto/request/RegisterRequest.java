package com.tech.younsik.dto.request;

import com.tech.younsik.constant.UserConst.Gender;
import com.tech.younsik.dto.object.RegisterObject;
import com.tech.younsik.validator.Password;
import com.tech.younsik.validator.PhoneNumber;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    
    @NotNull(message = "Wrong name : Name is null")
    @NotEmpty(message = "Wrong name : Name is empty")
    @Size(max = 20, message = "Wrong name : 최대 길이 20")
    @ApiModelProperty(name = "유저 name", notes = "본명, 로그인 용도 아님, 최대 20자", required = true)
    private String name;
    
    @NotNull(message = "Wrong nickname : nickname is null")
    @NotEmpty(message = "Wrong nickname : nickname is empty")
    @Size(max = 30, message = "Wrong nickname : 최대 길이 30")
    @ApiModelProperty(name = "유저 nickName", notes = "별명, UI 표시 용도, 최대 30자", required = true)
    private String nickname;
    
    @NotNull(message = "Wrong e-mail : E-mail is null")
    @NotEmpty(message = "Wrong e-mail : E-mail is empty")
    @Size(max = 100, message = "Wrong e-mail : 최대 길이 100")
    @Email(message = "Wrong e-mail : 이메일 형식")
    @ApiModelProperty(name = "유저 email", notes = "로그인 이메일, 유니크, 이메일 형식, 최대 100자", required = true)
    private String email;
    
    @NotNull(message = "Wrong password : Password is null")
    @NotEmpty(message = "Wrong password : Password is empty")
    @Password(min = 10, max = 100, message = "Wrong password : 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함, 길이 최소 10 ~ 최대 100")
    @ApiModelProperty(name = "유저 email", notes = "로그인 비밀번호, 유니크, 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함, 최소 10자 ~ 최대 100자", required = true)
    private String password;
    
    @NotNull(message = "Wrong phone : phone is null")
    @NotEmpty(message = "Wrong phone : phone is empty")
    @PhoneNumber(max = 20, message = "Wrong phone : 숫자 형식, 최대 길이 20")
    @ApiModelProperty(name = "유저 phoneNumber", notes = "유저 전화번호, 숫자 형식, 최소 1자 ~ 최대 20자", required = true)
    private String phoneNumber;
    
    @ApiModelProperty(name = "유저 gender", notes = "Optional")
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
