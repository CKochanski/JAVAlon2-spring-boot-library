package pl.sda.spring.library.repository;

import org.springframework.stereotype.Repository;
import pl.sda.spring.library.model.Book;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private Set<Book> books = initialize();

    private Set<Book> initialize() {
        Set<Book> initialSet = new HashSet<>();
        initialSet.add(new Book(1L, "Kaczanowski", "Testy"));
        initialSet.add(new Book(2L, "Rowling", "Harry Potter"));
        initialSet.add(new Book(3L, "Tolkien", "Wladca piercieni"));
        initialSet.add(new Book(4L, "Dostojewski", "Zbrodnia i kara"));
        initialSet.add(new Book(5L, "Joshua Bloch", "Effective Java"));
        return initialSet;
    }

    public Set<Book> findAll() {
        return books;
    }

    public Set<Book> findAllByTitle(String title) {
        return books.stream()
            .filter(book -> title.equals(book.getTitle()))
            .collect(Collectors.toSet());
    }

    public Optional<Book> findById(Long id) {
        return books.stream()
            .filter(book -> book.getId().equals(id))
            .findAny();
    }

    public Book addBook(Book book) {
        book.setId(getNextId());
        books.add(book);
        return book;
    }

    public boolean delete(Book book) {
        return books.remove(book);
    }

    private Long getNextId() {
        return books.stream()
            .mapToLong(Book::getId)
            .max()
            .getAsLong() + 1;
    }
}
