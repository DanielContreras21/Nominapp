package com.nominapp.security.model.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String id;
    private String email;
    private String phoneNumber;
    private String roleName;
    private String created;
}
