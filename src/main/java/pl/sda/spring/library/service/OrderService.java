package pl.sda.spring.library.service;

import org.springframework.stereotype.Service;
import pl.sda.spring.library.model.Book;
import pl.sda.spring.library.repository.BookRepository;

import java.time.LocalDate;
import java.util.HashSet;
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
        return new HashSet<>(bookRepository.findAll());
    }

    public Optional<Book> borrowBook(Long id) {
        Optional<Book> bookById = bookRepository.findById(id);
        if (bookById.isPresent()) {
            Book book = bookById.get();
            if (book.getDateOfReturn() == null) {
                book.setDateOfReturn(LocalDate.now().plusDays(30));
                return Optional.of(bookRepository.save(book));
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    public Book add(Book book) {
        return bookRepository.save(book);
    }

    public void removeBook(Long id) {
        bookRepository.deleteById(id);
    }
}
