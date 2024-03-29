package example.bookprogressapp.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    private BookEmailService bookEmailService;

    public BookService(BookRepository bookRepository, BookEmailService bookEmailService) {
        this.bookRepository = bookRepository;
        this.bookEmailService = bookEmailService;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    List<Book> getFavouritesBooks(){
        return bookRepository.findAllByFavouriteIsTrue();
    }

    void addBookToFavourites(Long id){
        Book book = bookRepository.getOne(id);
        if (!book.isFavourite()){
            book.setFavourite(true);
        }
        else {
            book.setFavourite(false);
        }
        bookRepository.save(book);
    }

    public void addBook(Book book){
        if(book.getPagesRead() > book.getAllPages())
            book.setPagesRead(book.getAllPages());
        bookRepository.save(book);
    }

    public void addBookWithEmailNotification(Book book, String email){
        addBook(book);
        bookEmailService.sendEmail(book.getTitle(), book.getBookId(), email);
    }

    public Book getBookById(Long id){
        return bookRepository.getOne(id);
    }

    public List<Book> getAllBooksWithoutSeries(){
        return bookRepository.findAllBySeriesNull();
    }

    void changePagesRead(Long bookid,int pagesRead){
        Book book = bookRepository.getOne(bookid);
        if(pagesRead > book.getAllPages())
            pagesRead = book.getAllPages();
        book.setPagesRead(pagesRead);
        bookRepository.save(book);
    }

    List<Book> getLatest8Books(){
        return bookRepository.findTop8ByOrderByAddedDateDesc();
    }

    void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

}
