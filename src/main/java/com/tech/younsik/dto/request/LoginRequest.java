package com.tech.younsik.dto.request;

import com.tech.younsik.dto.object.LoginObject;
import com.tech.younsik.validator.Password;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
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
public class LoginRequest implements Serializable {
    
    private static final long serialVersionUID = 1000000000001001L;
    
    @NotNull(message = "Wrong e-mail : E-mail is null")
    @NotEmpty(message = "Wrong e-mail : E-mail is empty")
    @Email(message = "Wrong e-mail : Invalid format")
    @Size(max = 100, message = "Wrong e-mail : Max 100 required")
    @ApiModelProperty(name = "로그인 요청 Email", notes = "Email 형식, 최대 100자", required = true)
    private String email;
    
    @NotNull(message = "Wrong password : Password is null")
    @NotEmpty(message = "Wrong password : Password is empty")
    @Password(min = 10, max = 100, message = "Wrong password : Invalid format")
    @ApiModelProperty(name = "로그인 요청 Password", notes = "영어 대문자, 소문자, 숫자, 특수문자 1개 이상, 최소 10자 ~ 최대 100자", required = true)
    private String password;

    public LoginObject toObject() {
        return LoginObject.builder()
            .email(email)
            .password(password)
            .build();
    }
}
