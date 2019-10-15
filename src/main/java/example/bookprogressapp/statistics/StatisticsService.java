package example.bookprogressapp.statistics;

import example.bookprogressapp.book.Book;
import example.bookprogressapp.book.BookService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class StatisticsService {

    private BookService bookService;

    public StatisticsService(BookService bookService) {
        this.bookService = bookService;
    }

    Statistics getStatistics(){
        Statistics statistics = new Statistics();
        List<Book> bookList = bookService.getAllBooks();
        List<Book> bookList7days = bookList.stream().filter(book -> DAYS.between(book.getAddedDate(),LocalDateTime.now()) <=7).collect(Collectors.toList());
        List<Book> bookList30days = bookList.stream().filter(book -> DAYS.between(book.getAddedDate(),LocalDateTime.now()) <=30).collect(Collectors.toList());
        List<Book> bookList365days = bookList.stream().filter(book -> DAYS.between(book.getAddedDate(),LocalDateTime.now()) <=365).collect(Collectors.toList());

        statistics.setPagesReadIn7days(bookList7days.stream().mapToLong(Book::getPagesRead).sum());
        statistics.setPagesReadIn30days(bookList30days.stream().mapToLong(Book::getPagesRead).sum());
        statistics.setPagesReadIn365days(bookList365days.stream().mapToLong(Book::getPagesRead).sum());
        statistics.setPagesReadAllTime(bookList.stream().mapToLong(Book::getPagesRead).sum());

        statistics.setBookAddedIn7Days(bookList7days.size());
        statistics.setBookAddedIn30Days(bookList30days.size());
        statistics.setBookAddedIn365days(bookList365days.size());
        statistics.setBookAddedAllTime(bookList.size());

        statistics.setBookCompletedIn7days(bookList7days.stream().filter(book -> book.getAllPages() == book.getPagesRead()).count());
        statistics.setBookCompletedIn30days(bookList30days.stream().filter(book -> book.getAllPages() == book.getPagesRead()).count());
        statistics.setBookCompletedIn365days(bookList365days.stream().filter(book -> book.getAllPages() == book.getPagesRead()).count());
        statistics.setBookCompletedAllTime(bookList.stream().filter(book -> book.getAllPages() == book.getPagesRead()).count());

        return statistics;

    }
}
