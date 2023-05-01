package com.library.demo.repository;

import com.library.demo.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {



    @Modifying
    @Query("select b from UserBook b where b.username = :username")
    ArrayList<UserBook> getListOfUserBooksByUsername(@Param("username") String username);
}
