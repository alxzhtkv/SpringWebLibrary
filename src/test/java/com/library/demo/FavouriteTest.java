package com.library.demo;

import com.library.demo.repository.FavouritesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class FavouriteTest {
    @Autowired
    private FavouritesRepository favouritesRepository;

    @Test
    void TestFavourite() throws SQLException,
            ClassNotFoundException {
        Assertions.assertNotNull(favouritesRepository.findAll());
    }
}
