package com.rotana.springmvc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false,
            check = @CheckConstraint(constraint = "salary >= 0"))
    private BigDecimal salary;
    private LocalDate hireDate;
    private Boolean isActive;
    // define relationship :

    @ManyToOne
    private Department department;
}
