package example.bookprogressapp.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public Book getBookById(Long id){
        return bookRepository.getOne(id);
    }

    public List<Book> getAllBooksWithoutSeries(){
        return bookRepository.findAllBySeriesNull();
    }

}
