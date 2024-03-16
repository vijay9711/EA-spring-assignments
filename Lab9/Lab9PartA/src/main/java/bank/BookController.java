package bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    @Autowired
    BookRepo bookRepo;

    @GetMapping
    public String getBook(){
        return "hello";
    }

    @PostMapping
    public Book addBook(@RequestBody Book data){
        return bookRepo.save(data);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book data){
        return  bookRepo.save(data);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") long id){
        Optional<Book> b = null;
        b = bookRepo.findById(id);
        if(b != null){
            bookRepo.deleteById(id);
            return "Book deleted";
        }else{
            return "Book not found";
        }
    }

    @GetMapping("/all")
    public List<Book> getAllBook(){
        return bookRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") long id){
        Optional<Book> b = null;
        b = bookRepo.findById(id);
        if(b != null){
            return ResponseEntity.of(b);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter/author/{author}")
    public ResponseEntity<?> getBookByAuthor(@PathVariable("author") String author){
        Optional<Book> b = null;
        b = Optional.ofNullable(bookRepo.findByAuthor(author));
        if(b != null){
            return ResponseEntity.of(b);
        }else{
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
//        return bookRepo.findByAuthor(author);
    }
}
