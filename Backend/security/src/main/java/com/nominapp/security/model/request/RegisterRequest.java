package com.nominapp.security.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private String phoneNumber;

}
