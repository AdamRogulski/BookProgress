package example.bookprogressapp.book;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/books/{id}")
    public Book getOneBook(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping("/books/add")
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

}
