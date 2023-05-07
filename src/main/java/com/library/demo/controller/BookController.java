package com.library.demo.controller;

import com.library.demo.model.*;
import com.library.demo.repository.*;
import com.library.demo.service.BookService;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import untils.ImageUploadHelper;
//import org.springframework.mock.web.MockMultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final UserBookRepository userBookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;

    private final BookService bookService;


//    @PostMapping("/addBook")
//    public String addBook(@ModelAttribute Book book,
//                          @RequestParam("content1") MultipartFile contentFile,
//                          @RequestParam("image1") MultipartFile imageFile) throws IOException {
//
//        byte[] content = contentFile.getBytes();
//        byte[] image = imageFile.getBytes();
//        book.setContent(content);
//        book.setImage(image);
//
//        bookRepository.save(book);
//        return "redirect:/addBook";
//    }


    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book, HttpServletRequest request,
                          @RequestParam("image1") MultipartFile imageFile) throws IOException {


        bookRepository.save(book);


        String imagePath = ImageUploadHelper.uploadImage(imageFile);


//            String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
//            String fileName = StringUtils.cleanPath(book.getId().toString() + ".jpg");
//            String uploadDir = "book-covers/";
//            String filePath = uploadDir + fileName;
//            try {
//                Path path = Paths.get(uploadDir);
//                if (!Files.exists(path)) {
//                    Files.createDirectories(path);
//                }
//                Files.copy(imageFile.getInputStream(), path.resolve(fileName));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


        // сохранение относительного пути файла в базу данных
        book.setImage(imagePath);
        bookRepository.save(book);

//        byte[] content = contentFile.getBytes();
//        byte[] image = imageFile.getBytes();
//        book.setContent(content);
//        book.setImage(image);
//
//        bookRepository.save(book);
        return "redirect:/addBook";
    }

    @GetMapping("/booksCatalog")
    public String getBooksCatalogPage(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);


        return "booksCatalogPage";
    }

    @GetMapping("/booksCatalogUser")
    public String getBooksCatalogUserPage(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);


        return "booksCatalogUserPage";
    }


    @GetMapping("/bookPage/{id}")
    public String getBook(Model model, @PathVariable(value = "id") Long id) {
        Book book = bookRepository.searchById(id);
        Long viewCount = book.getViewCount()+1;
        book.setViewCount(viewCount);
        bookRepository.save(book);
        model.addAttribute("book", book);

        return "bookPage";
    }

    @GetMapping("/deleteUserBook/{id}")
    public String deleteUserBook(HttpServletRequest request, @PathVariable(value = "id") Long id) {
        userBookRepository.deleteById(id);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
//         return "redirect:/userBookPage";

    }

    @GetMapping("/bookEdit/{id}")
    public String getEditBookPage(@PathVariable(value = "id") Long id, Model model) {

        Book book = bookRepository.searchById(id);
        model.addAttribute("book", book);

        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);

        Iterable<Publisher> publishers = publisherRepository.findAll();
        model.addAttribute("publishers", publishers);

        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);


        return "editBookPage";
//         return "redirect:/userBookPage";

    }


    //, @RequestParam("image1") MultipartFile imageFile
    @PostMapping("/bookEdit/{id}")
    public String editBookPage(HttpServletRequest request, @PathVariable(value = "id") Long id, @ModelAttribute Book newBook) {
        bookService.updateBookFields(newBook);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;

    }


    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        String imagePath = book.getImage();
        Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

//    @GetMapping("/image/{id}")
//    public void showImage(@PathVariable Long id, HttpServletResponse response) throws IOException {
//        Book book = bookRepository.findById(id).orElse(null);
//        if (book != null && book.getImage() != null) {
//            Resource resource = new ClassPathResource(book.getImage());
//            InputStream inputStream = resource.getInputStream();
//            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//            IOUtils.copy(inputStream, response.getOutputStream());
//        }
//    }

//    @GetMapping("/image/{id}")
//    @ResponseBody
//    public ResponseEntity<Resource> getImage(@PathVariable("id") Long id) throws IOException {
//        // получение объекта Book по id
//        Book book = bookRepository.findById(id).orElse(null);
//        if (book == null || book.getImage() == null) {
//            return ResponseEntity.notFound().build();
//        }
//        // получение относительного пути к картинке
//        String imagePath = book.getImage();
//        // создание объекта Resource для загружаемой картинки
//        Resource resource = new UrlResource("file:" + imagePath);
//        // проверка, существует ли файл на сервере
////        if (!resource.exists()) {
////            return ResponseEntity.notFound().build();
////        }
//        // настройка HTTP-заголовков для возврата изображения в браузер
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        headers.setContentLength(resource.contentLength());
//        // возврат объекта ResponseEntity с загружаемым изображением и настроенными заголовками
//        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//    }

}

