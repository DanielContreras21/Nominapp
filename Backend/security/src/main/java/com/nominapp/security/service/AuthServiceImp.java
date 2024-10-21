package com.nominapp.security.service;

import com.nominapp.security.config.JwtUtils;
import com.nominapp.security.model.entity.Account;
import com.nominapp.security.model.enums.RoleEnum;
import com.nominapp.security.model.mapper.AccountMapper;
import com.nominapp.security.model.request.LoginRequest;
import com.nominapp.security.model.request.RegisterRequest;
import com.nominapp.security.model.response.RegisterResponse;
import com.nominapp.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountMapper mapper;

    @Override
    public String login(LoginRequest login) {
        String email = login.getEmail();
        String password = login.getPassword();
        boolean isUserExist = repository.findByEmail(email).isPresent();

        if (!isUserExist){
            throw new NoSuchElementException("Ingrese un nombre de usuario válido");
        }
        if (email.isBlank()) {
            throw new NoSuchElementException("Ingrese un nombre de usuario válido");
        }
        if (password.isBlank()) {
            throw new NoSuchElementException("Ingrese una contraseña válida");
        }
        Authentication authentication = this.authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.createToken(authentication);
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (userDetails == null){
            throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new IllegalArgumentException("Nombre de usuario o contraseña incorrectos");
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public RegisterResponse register(RegisterRequest register) {
        String id = UUID.randomUUID().toString();
        boolean isAccountExist = repository.findById(id).isPresent();
        boolean isEmailExist = repository.findByEmail(register.getEmail()).isPresent();

        while (isAccountExist){
            id = UUID.randomUUID().toString();
        }

        if(isEmailExist) {
            throw new DuplicateKeyException("El correo electrónico no se encuentra disponible");
        }
        if (!register.getPassword().equals(register.getConfirmPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }
        if (!register.getEmail().equals(register.getConfirmEmail())){
            throw new IllegalArgumentException("Los correo electrónicos no coinciden");
        }

        Account account = mapper.dtoToRegister(register);
        account.setId(id);
        account.setRole(RoleEnum.USER);
        account.setCreated(LocalDate.now());

        repository.save(account);

        return mapper.registerToDto(account);
    }
}
