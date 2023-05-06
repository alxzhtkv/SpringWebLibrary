package com.library.demo;


import com.library.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void TestUser() throws SQLException, ClassNotFoundException {
        Assertions.assertNotNull(userRepository.getOne((long)29));
        Assertions.assertEquals(true, userRepository.existsByUsername("user"));
    }

}
