package com.rotana.springmvc.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
public class Employee {

    private Integer id;
    private String name;
    private String email;
    private BigDecimal salary;
    private LocalDate hireDate;
    private Boolean isActive;
}
