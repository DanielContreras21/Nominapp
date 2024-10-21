package com.nominapp.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;
}
