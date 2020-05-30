package pl.sda.spring.library.model;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Book {

    @Id
    @GeneratedValue(generator = "bookSeq")
    @SequenceGenerator(name = "bookSeq", sequenceName = "book_seq", allocationSize = 1)
    private Long id;
    @NonNull
    private String author;
    @NonNull
    private String title;
    private LocalDate dateOfReturn;

    public Book(){}

    public Book(Long id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(author, book.author) && Objects
            .equals(title, book.title) && Objects.equals(dateOfReturn, book.dateOfReturn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, dateOfReturn);
    }
}
