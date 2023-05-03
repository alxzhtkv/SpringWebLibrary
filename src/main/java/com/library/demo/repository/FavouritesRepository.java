package com.library.demo.repository;

import com.library.demo.model.Favourites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface FavouritesRepository extends JpaRepository<Favourites,Long > {
    boolean existsByUsernameAndBookId(String username, Long bookId);

    @Query("SELECT f.bookId FROM Favourites f WHERE f.username = :username")
    ArrayList<Long> findBookIdsByUsername(@Param("username") String username);
}
