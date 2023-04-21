package com.library.demo.repository;

import com.library.demo.model.Author;
import com.library.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User  findByUsername(String username);

    boolean existsByUsername(String username);

}
