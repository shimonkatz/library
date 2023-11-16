package com.example.library.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.ISBN;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ISBN
    @NotNull
    @NotBlank
    private String isbn;
    @NotNull
    @NotBlank
    private String author;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Integer available;

//    @JsonManagedReference
    @Builder.Default
    @OneToMany(mappedBy = "book")
    private Set<Lending> lendingSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return isbn != null && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
