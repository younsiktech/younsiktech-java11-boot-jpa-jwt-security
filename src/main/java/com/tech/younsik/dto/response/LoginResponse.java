package com.tech.younsik.dto.response;

import com.tech.younsik.dto.object.LoginObject;
import java.io.Serializable;
import lombok.Data;

@Data
public class LoginResponse implements Serializable {
    private static final long serialVersionUID = -1000000000001001L;

    private String token;

    public LoginResponse(LoginObject loginObject) {
        this.token = loginObject.getToken();
    }
}
