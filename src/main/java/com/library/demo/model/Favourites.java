package com.library.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "favourites")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Data
@EqualsAndHashCode(of = "id")
public class Favourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;


    @Column(name = "book_id")
    private Long bookId;

    public Favourites() {

    }

    public Favourites(String username, Long bookId) {
        this.username = username;
        this.bookId = bookId;
    }
}
