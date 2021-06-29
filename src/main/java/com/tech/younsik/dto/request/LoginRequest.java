package com.tech.younsik.dto.request;

import com.tech.younsik.dto.object.LoginObject;
import com.tech.younsik.validator.Password;
import java.io.Serializable;
import javax.validation.constraints.Email;
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

    @NotNull(message = "Wrong e-mail : E-mail is empty")
    @Email(message = "Wrong e-mail : Invalid format")
    @Size(min = 4, max = 255, message = "Wrong e-mail : Min 4 to Max 255 required")
    private String email;

    @NotNull(message = "Wrong password : Password is empty")
    @Password(min = 10, message = "Wrong password : Alphabet, Number, Special exclude white space(Size 8 to 20)")
    private String password;

    public LoginObject toObject() {
        return LoginObject.builder()
            .email(email)
            .password(password)
            .build();
    }
}
