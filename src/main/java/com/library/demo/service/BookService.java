package com.library.demo.service;

import com.library.demo.model.Book;
import com.library.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
//Long BookId,String name,int pageCount,String isbn,Long idGenre,Long idAuthor, Long idPublisher, int publishYear, String descr
    public void updateBookFields(Book newBook){
        Book book = bookRepository.findById(newBook.getId()).orElseThrow(() -> new RuntimeException("Book not found"));
        if(!book.getName().equals(newBook.getName())){
            book.setName(newBook.getName());
        }
        if (!book.getIsbn().equals(newBook.getIsbn()))
            book.setIsbn(newBook.getIsbn());

        if (!book.getGenre().equals(newBook.getGenre()))
            book.setGenre(newBook.getGenre());

        if (!book.getAuthor().equals(newBook.getAuthor()))
            book.setAuthor(newBook.getAuthor());

        if (!book.getPublisher().equals(newBook.getPublisher()))
            book.setPublisher(newBook.getPublisher());

        if (book.getPublishYear()!= newBook.getPublishYear())
            book.setPublishYear(newBook.getPublishYear());

        if (book.getPageCount()!= newBook.getPageCount())
            book.setPageCount(newBook.getPageCount());

//        if (newBook.getDescr()!=null)
//            book.setDescr(newBook.getDescr());


        if (!newBook.getDescr().equals(book.getDescr()))
            book.setDescr(newBook.getDescr());



//        if(newBook.getContent()!=null)
//            book.setContent(" ");
//        if(newBook.getImage()!=null)
//            book.setContent(" ");


        bookRepository.save(book);


    }

    public List<Book> findThreeFavouriteBooks(){
        List<Book> books = bookRepository.findAll();
        Book max1 = null;
        Book max2 = null;
        Book max3 = null;

        for (Book book : books) {
            if (max1 == null || book.getAvgRating() > max1.getAvgRating()) {
                max3 = max2;
                max2 = max1;
                max1 = book;
            } else if (max2 == null || book.getAvgRating() > max2.getAvgRating()) {
                max3 = max2;
                max2 = book;
            } else if (max3 == null || book.getAvgRating() > max3.getAvgRating()) {
                max3 = book;
            }
        }

        List<Book> result = new ArrayList<>();
        if (max1 != null) {
            result.add(max1);
        }
        if (max2 != null) {
            result.add(max2);
        }
        if (max3 != null) {
            result.add(max3);
        }

        return result;
    }




}
