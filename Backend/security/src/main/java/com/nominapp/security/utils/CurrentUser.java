package com.nominapp.security.utils;

import com.nominapp.security.model.entity.Account;
import com.nominapp.security.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CurrentUser {

    @Autowired
    private final AccountRepository repository;

    public Account getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = null;

        if(authentication != null && authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails){
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }

        if (username == null){
            throw new NoSuchElementException("Usuario no encontrado");
        }

        Account account = repository.findByEmail(username).orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

        return account;
    }
}
