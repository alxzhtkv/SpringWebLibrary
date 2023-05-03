package com.library.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

//import jakarta.persistence.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "book")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Setter @Getter
@EqualsAndHashCode(of = "id")
public class Book {

    public Book() {
    }


       public Book(String content, String image) {
        this.content = content;
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Lob
//    @Column(updatable = false)
//    private byte[] content;

    @Column(name = "content")
    private String content;


    @Column(name = "page_count")
    private Integer pageCount;

    private String isbn;



    @ManyToOne
    @JoinColumn
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Publisher publisher;



    @Column(name = "publish_year")
    private Integer publishYear;


//    private byte[] image;

    @Column(name = "image")
    private String image;

    private String descr;

    @Column(name = "view_count")
    private long viewCount;

    @Column(name = "total_rating")
    private long totalRating;

    @Column(name = "total_vote_count")
    private long totalVoteCount;

    @Column(name = "avg_rating")
    private int avgRating;

    @Override
    public String toString() {
        return name;
    }

}
