package com.nominapp.security.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nominapp.security.model.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Builder
public class Account {

    @Id
    private String id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Ingrese un correo electrónico válido")
    @Email(message = "Ingrese un correo electrónico válido")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Ingrese una contraseña válida")
    private String password;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Type a valid phone number")
    private String phoneNumber;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate created;

    private LocalDate lastSession;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(name = "account_non_expired", nullable = false)
    private boolean isAccountNonExpired;

    @Column(name = "account_non_locked", nullable = false)
    private boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired", nullable = false)
    private boolean isCredentialsNonExpired;

    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @Column(name = "activation_token")
    private String activationToken;

    @Column(name = "reset_token")
    private String ResetToken;
}
