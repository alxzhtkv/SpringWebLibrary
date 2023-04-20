package com.library.demo.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import jakarta.persistence.*;
import java.util.List;

@EqualsAndHashCode(of = "id")
@Table(catalog = "webLibrary")
@DynamicUpdate
@DynamicInsert
@SelectBeforeUpdate
@Entity
@Getter @Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //используется встроенный в БД тип данных столбца -identity - для генерации значения первичного ключа.
    private Long id;

    private String name;

    //@Basic(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "genre")
    private List<Book> books;


    @Override
    public String toString() {
        return name;
    }

}