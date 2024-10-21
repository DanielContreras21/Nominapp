package com.nominapp.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(name = "second_name", length = 20)
    private String secondName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "second_last_name", length = 20)
    private String secondLastName;

    @Transient
    private List<Paysheet> paysheets = new ArrayList<>();
}
