package com.library.demo;

import com.library.demo.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class GenreTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void TestGenre() throws SQLException, ClassNotFoundException{
        Assertions.assertNotNull(genreRepository.getOne(Long.valueOf(1)));
        Assertions.assertEquals(1,genreRepository.
                findByNameContainingIgnoreCase("Фантастика").getId());

    }
}
