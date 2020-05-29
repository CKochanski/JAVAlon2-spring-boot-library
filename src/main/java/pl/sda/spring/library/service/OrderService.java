package pl.sda.spring.library.service;

import org.springframework.stereotype.Service;
import pl.sda.spring.library.model.Book;
import pl.sda.spring.library.repository.BookRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {

    private final BookRepository bookRepository;

    public OrderService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Set<Book> findAll(String title) {
        if (title != null) {
            return bookRepository.findAllByTitle(title);
        }
        return bookRepository.findAll();
    }

    public Optional<Book> borrowBook(Long id) {
        Optional<Book> bookById = bookRepository.findById(id);
        bookById.ifPresent(book -> book.setDateOfReturn(LocalDate.now().plusDays(30)));
        return bookById;
    }

    public Book add(Book book) {
        return bookRepository.addBook(book);
    }

    public boolean removeBook(Long id) {
        Optional<Book> bookToRemove = bookRepository.findById(id);
        if (bookToRemove.isPresent()) {
            return bookRepository.delete(bookToRemove.get());
        }
        return false;
    }
}
