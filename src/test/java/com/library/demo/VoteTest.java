package com.library.demo;


import com.library.demo.repository.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class VoteTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void TestVoid ()throws SQLException,
            ClassNotFoundException {
        Assertions.assertNotNull(voteRepository.findAll());
    }
}
