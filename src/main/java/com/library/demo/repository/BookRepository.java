package com.library.demo.repository;

import com.library.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book searchById(Long id);

    Book findByName(String name);
    Book getBookById(Long id);

    @Query("SELECT b FROM Book b ORDER BY b.viewCount DESC")
    List<Book> findAllByViewCountDescr();


    @Modifying
    @Query("select b.name from Book b where b.id  in :idList")
    List<String> getListOfBookNames(@Param("idList") ArrayList idList);

    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.isbn) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.author.fio) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.genre.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.publisher.nameP) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBooks(@Param("keyword") String keyword);


    @Modifying
    @Query("select b from Book b where b.id  in :idList")
    List<Book> getBooksByIds(@Param("idList") ArrayList idList);







}
