package com.library.demo.repository;

import com.library.demo.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

//    List<Publisher> findByNameContainingIgnoreCaseOrderByName(String name);
//
//    Page<Publisher> findByNameContainingIgnoreCaseOrderByName(String name, Pageable pageable);

}
