package com.rotana.springmvc.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "borrowings")
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    LocalDate dueDate;
    LocalDate borrowedAt;
    LocalDate returnedAt;

    @ManyToOne
    Book book;

    @ManyToOne
    Member member;
}
