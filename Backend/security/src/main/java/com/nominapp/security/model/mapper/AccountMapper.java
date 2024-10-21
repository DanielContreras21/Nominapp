package com.nominapp.security.model.mapper;

import com.nominapp.security.model.entity.Account;
import com.nominapp.security.model.enums.RoleEnum;
import com.nominapp.security.model.request.RegisterRequest;
import com.nominapp.security.model.response.RegisterResponse;
import com.nominapp.security.utils.DateFormatter;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountMapper {

    @Autowired
    private final DateFormatter dateFormatter;

    @Autowired
    private PasswordEncoder encoder;

    public Account dtoToRegister(RegisterRequest register){
        return Account.builder()
                .email(register.getEmail())
                .password(encoder.encode(register.getPassword()))
                .phoneNumber(register.getPhoneNumber())
                .build();
    }

    public RegisterResponse registerToDto(Account account){

        String formattedDate = dateFormatter.formatLocalDate(account.getCreated());

        return RegisterResponse.builder()
                .id(account.getId())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .roleName(account.getRole().toString())
                .created(formattedDate)
                .build();
    }
}
