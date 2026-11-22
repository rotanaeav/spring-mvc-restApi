package com.rotana.springmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private Boolean isActive;
    private Integer qty;
    private boolean isBn;
    private String description;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "book")
    private List<Borrowing> borrowings;

    @ManyToMany
    // we can use joinTable to custom the generate table name or else...
    @JoinTable(name = "author_books")
    private List<Author> authors;

}
