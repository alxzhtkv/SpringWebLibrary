package com.library.demo.service;

import com.library.demo.model.Book;
import com.library.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
