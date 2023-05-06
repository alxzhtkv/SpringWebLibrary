package com.library.demo;


import com.library.demo.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class BookTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void TestBook() throws SQLException, ClassNotFoundException {
        Assertions.assertNotNull(bookRepository.getOne(Long.valueOf(125)));
        Assertions.assertEquals(125,bookRepository.findByName("1984").getId());


    }

}
