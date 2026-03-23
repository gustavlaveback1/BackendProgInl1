package se.yrgo.jpa.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    @ManyToMany
    @JoinTable(name = "reader_book", joinColumns = @JoinColumn(name = "reader_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books = new ArrayList<>();

    public Reader() {
    }

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.getReaders().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBooks() {
        return books;
    }

    public String toString() {
        return "Reader:" + name + ", email: " + email;
    }
}