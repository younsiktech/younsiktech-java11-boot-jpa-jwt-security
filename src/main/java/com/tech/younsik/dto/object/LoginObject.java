package com.tech.younsik.dto.object;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginObject {
    private String email;
    private String password;
    private String token;
}
