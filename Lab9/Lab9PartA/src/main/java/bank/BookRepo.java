package bank;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    public Book findByAuthor(String author);
}
