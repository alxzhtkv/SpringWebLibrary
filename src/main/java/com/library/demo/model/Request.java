package com.library.demo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "request")
@EqualsAndHashCode(of = "id")
@Data
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "descr")
    private String descr;



}
