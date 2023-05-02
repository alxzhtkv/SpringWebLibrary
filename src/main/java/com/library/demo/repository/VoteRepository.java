package com.library.demo.repository;

import com.library.demo.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    boolean existsByUsernameAndBookId(String username, Long bookId);

    @Modifying
    @Query("update Vote r set r.value = :value where r.username = :username and r.bookId = :bookId")
    void updateRatingValueByUsernameAndBookId(@Param("value") int value, @Param("username") String username, @Param("bookId") Long bookId);
}
