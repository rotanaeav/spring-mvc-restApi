package com.rotana.springmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "members")
@Getter
@NoArgsConstructor
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String phone;
    String lastName;
    String firstName;
    String email;

    @OneToMany(mappedBy = "member")
    List<Borrowing> borrowings;
}
