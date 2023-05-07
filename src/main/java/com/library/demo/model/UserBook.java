package com.library.demo.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "userbook")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Data
@EqualsAndHashCode(of = "id")
public class UserBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;


    @Lob
    @Column(name="content", length = 1043741)
    private byte[] content;

    @ManyToOne
    @JoinColumn
    private Genre genre;

    @Column(name="authorName")
    private String authorName;

    @Column(name="image")
    private String image;

    public UserBook() {

    }
}
