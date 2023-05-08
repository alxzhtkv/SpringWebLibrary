package com.library.demo.controller;

import com.library.demo.model.*;
import com.library.demo.repository.*;
import com.library.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import untils.FileUploadHelper;
import untils.ImageUploadHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


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

    @GetMapping({"*/images/{name}", "/images/{name}"})
    public ResponseEntity<byte[]> getRequestManagementPage(HttpServletResponse response, @PathVariable(value = "name") String name) throws IOException {
        Path path = Paths.get(ImageUploadHelper.getPathToFile(name));
        byte[] bytes = Files.readAllBytes(path);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }


    @GetMapping("/read/content/{content}")
    public ResponseEntity<byte[]> openPdf(@PathVariable(value = "content") String content)throws IOException {
        System.out.println(content);
        Path path = Paths.get(FileUploadHelper.getPathToFile(content));
        byte[] bytes = Files.readAllBytes(path);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);


    }

    @GetMapping("/download/content/{content}")
    public ResponseEntity<Resource> downloadPDF(@PathVariable(value = "content") String content)throws IOException {
        System.out.println("may");
        System.out.println(content);

//        Book book = bookRepository.getBookById(id);
        Path path = Paths.get(FileUploadHelper.getPathToFile(content));

        byte[] bytes = Files.readAllBytes(path);
        ByteArrayResource resource = new ByteArrayResource(bytes);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=book.pdf")
                .contentType(MediaType.APPLICATION_PDF)

                .body(resource);


    }



    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book, HttpServletRequest request, @RequestParam("content1") MultipartFile contentFile, @RequestParam("image1") MultipartFile imageFile) throws IOException {
        bookRepository.save(book);
        String imagePath = ImageUploadHelper.uploadImage(imageFile);
        book.setImage(imagePath);

        String contentPath = FileUploadHelper.uploadImage(contentFile);
        book.setContent(contentPath);

        bookRepository.save(book);
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
        Long viewCount = book.getViewCount() + 1;
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


    @PostMapping("/bookEdit/{id}")
    public String editBookPage(HttpServletRequest request, @PathVariable(value = "id") Long id, @ModelAttribute Book newBook) {
        bookService.updateBookFields(newBook);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;

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

