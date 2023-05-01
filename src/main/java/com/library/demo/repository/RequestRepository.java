package com.library.demo.repository;

import com.library.demo.model.Request;
import com.library.demo.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional <Request> findById(Long id);

    @Modifying
    @Query("select r from Request r where r.username = :username")
    ArrayList<Request> getListOfUserRequestsByUsername(@Param("username") String username);
}
