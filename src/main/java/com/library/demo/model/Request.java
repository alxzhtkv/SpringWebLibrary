package com.library.demo.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "request")
@EqualsAndHashCode(of = "id")
@Data
@Getter
@Setter
public class Request {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "descr")
    private String descr;



}
