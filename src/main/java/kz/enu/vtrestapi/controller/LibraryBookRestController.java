package kz.enu.vtrestapi.controller;

import jakarta.validation.Valid;
import kz.enu.vtrestapi.dto.BookRequest;
import kz.example.lms.model.Book;
import kz.example.lms.service.LmsDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class LibraryBookRestController {

    private final LmsDataService dataService;

    public LibraryBookRestController(LmsDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(dataService.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Book book = dataService.findBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookRequest request) {
        String genre = request.getGenre() == null || request.getGenre().isBlank() ? "General" : request.getGenre().trim();
        dataService.createBook(
                request.getTitle(),
                genre,
                request.getIsbn(),
                request.getAuthorId(),
                request.getLibraryId(),
                request.getPublishedYear()
        );
        Book created = findLastCreatedBook();
        if (created == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.created(URI.create("/api/books/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @Valid @RequestBody BookRequest request) {
        Book existing = dataService.findBookById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        String genre = request.getGenre() == null || request.getGenre().isBlank() ? "General" : request.getGenre().trim();
        dataService.updateBook(
                id,
                request.getTitle(),
                genre,
                request.getIsbn(),
                request.getAuthorId(),
                request.getLibraryId(),
                request.getPublishedYear()
        );
        return ResponseEntity.ok(dataService.findBookById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        Book existing = dataService.findBookById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        dataService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    private Book findLastCreatedBook() {
        List<Book> books = dataService.getBooks();
        if (books.isEmpty()) {
            return null;
        }
        Book result = books.get(0);
        for (Book book : books) {
            if (book.getId() > result.getId()) {
                result = book;
            }
        }
        return result;
    }
}
