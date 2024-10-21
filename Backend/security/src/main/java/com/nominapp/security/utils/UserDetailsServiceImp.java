package com.nominapp.security.utils;

import com.nominapp.security.model.entity.Account;
import com.nominapp.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("El usuario con correo " + email + " no existe."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(account.getRole().toString())));


        return new User(account.getEmail(),
        account.getPassword(),
        account.isEnabled(),
        account.isAccountNonExpired(),
        account.isCredentialsNonExpired(),
        account.isAccountNonLocked(),
        authorityList
        );
    }
}