package example.bookprogressapp.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/empty")
    public List<Book> getAllBooksWithoutSeries(){
        return bookService.getAllBooksWithoutSeries();
    }

    @GetMapping("/books/latest")
    public List<Book> getLatest8Books(){
        return bookService.getLatest8Books();
    }

    @GetMapping("/books/{id}")
    public Book getOneBook(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping("/books/favourite")
    public List<Book> getFavouritesBooks(){
        return bookService.getFavouritesBooks();
    }

    @PutMapping("/books/{id}/favourite")
    public ResponseEntity<String> addBookToFavourite(@PathVariable Long id){
        bookService.addBookToFavourites(id);
        return new ResponseEntity<>("Book status changed", HttpStatus.OK);
    }

    @PostMapping("/books/add")
    public ResponseEntity<String> addBook(@RequestBody @Valid Book book){
        bookService.addBook(book);
        return new ResponseEntity<>("Book added", HttpStatus.OK);
    }

    @PutMapping("/books/{id}/pages")
    public ResponseEntity<String> updatePageRead(@PathVariable Long id, @RequestParam int read){
        bookService.changePagesRead(id,read);
        return new ResponseEntity<>("Pages changed", HttpStatus.OK);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        if(bookService.getBookById(id)==null)
            return new ResponseEntity<>("Can't find book with this id",HttpStatus.CONFLICT);
        else {
            bookService.deleteBookById(id);
            return new ResponseEntity<>("Book deleted", HttpStatus.OK);
        }
    }
}
