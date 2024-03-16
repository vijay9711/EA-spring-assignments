package domain;

import jakarta.persistence.*;

// Create an Optional Unidirectional ManyToOne association between Book and Publisher without using NULL fields in the database


@Entity
public class Book {
    @Id
    private int isbn;
    private String title;
    private String author;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = true)
    @JoinTable(name = "book_publisher", joinColumns = @JoinColumn(name = "isbn"), inverseJoinColumns = @JoinColumn(name = "publisher_id"))
    private Publisher publisher;

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public Book() {

    }

    // set Publisher
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    // All Getters and Setters
    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Publisher getPublisher() {
        return publisher;
    }
    // Override toString
    @Override
    public String toString() {
        return "Book --> " +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher=" + publisher;
    }
}
