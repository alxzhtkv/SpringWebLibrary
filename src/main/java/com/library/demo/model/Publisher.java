package com.library.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import javax.persistence.*;
//import jakarta.persistence.*;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Table(name = "publisher")
@Getter @Setter
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nameP;

    //@Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

    @Override
    public String toString() {
        return nameP;
    }

}