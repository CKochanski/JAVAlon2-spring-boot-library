package pl.sda.spring.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.spring.library.model.Book;
import pl.sda.spring.library.service.OrderService;

import java.util.Optional;
import java.util.Set;

@RestController
public class BookController {

    private final OrderService orderService;

    public BookController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/books", produces = "application/json")
    public Set<Book> findAllBooks(@RequestParam(required = false) String title) {
        return orderService.findAll(title);
    }

    @GetMapping(value = "/book/order/{id}", produces = "application/json")
    public ResponseEntity<Book> borrowBook(@PathVariable Long id) {
        Optional<Book> book = orderService.borrowBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/book/add", consumes = "application/json")
    public ResponseEntity<Long> addBook(@RequestBody Book book) {
        Book addedBook = orderService.add(book);
        return new ResponseEntity<>(addedBook.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/book/remove/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        orderService.removeBook(id);
        return ResponseEntity.noContent().build();
    }
}
