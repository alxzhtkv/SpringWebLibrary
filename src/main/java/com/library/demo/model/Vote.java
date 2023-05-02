package com.library.demo.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

//import jakarta.persistence.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vote")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
public class Vote {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "value")
    private int value;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "username")
    private String username;

}