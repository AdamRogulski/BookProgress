package example.bookprogressapp.series;

import example.bookprogressapp.book.Book;
import example.bookprogressapp.book.BookDTO;
import example.bookprogressapp.book.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SeriesService {

    private SeriesRepository seriesRepository;
    private BookService bookService;

    public SeriesService(SeriesRepository seriesRepository, BookService bookService) {
        this.seriesRepository = seriesRepository;
        this.bookService = bookService;
    }

    SeriesDTO getOneSeriesById(Long id){
        return objectToDTO(seriesRepository.getOne(id));
    }

    List<SeriesDTO> getAllSeries(){
        List<Series> seriesList = seriesRepository.findAll();
        seriesList.sort(Comparator.comparing(Series::getSeriesCreationDate).reversed());
        for(Series s: seriesList){
            s.getBooksList().sort(Comparator.comparing(Book::getAddedDate));
        }
        return listToDTO(seriesList);

    }

    List<Book> getSeriesBooksById(Long seriesId){
        return seriesRepository.getOne(seriesId).getBooksList();
    }

    void addSeriesByTitle(String title){
        Series series = new Series();
        series.setSeriesTitle(title);
        seriesRepository.save(series);
    }

    void addSeriesBySeriesBody(Series series){
        seriesRepository.save(series);
    }

    void deleteSeriesById(Long id){
        seriesRepository.deleteById(id);
    }

    void addBookToSeries(Long seriesId, Long bookId){
        Book book = bookService.getBookById(bookId);
        Series series = seriesRepository.getOne(seriesId);
        book.setSeries(series);
        bookService.addBook(book);
    }

    void removeBookFromSeries(Long id){
        Book book = bookService.getBookById(id);
        book.setSeries(null);
        bookService.addBook(book);
    }

    private static List<SeriesDTO> listToDTO(List<Series> seriesList){

        List<SeriesDTO> seriesDTOList = new ArrayList<>();
        for(Series s : seriesList){
            SeriesDTO seriesDTO = new SeriesDTO();
            seriesDTO.setSeriesId(s.getSeriesId());
            seriesDTO.setSeriesTitle(s.getSeriesTitle());
            seriesDTO.setSeriesDescription(s.getSeriesDescription());
            seriesDTO.setSeriesCreationTime(s.getSeriesCreationDate());
            List<BookDTO> bookDTOSList = new ArrayList<>();
            for (Book b : s.getBooksList()){
                BookDTO bookDTO = new BookDTO();
                bookDTO.setBookId(b.getBookId());
                bookDTO.setImage(b.getImageURL());
                bookDTO.setTitle(b.getTitle());
                bookDTO.setFavourite(b.isFavourite());
                bookDTO.setAllPages(b.getAllPages());
                bookDTO.setPagesRead(b.getPagesRead());
                bookDTOSList.add(bookDTO);
            }
            seriesDTO.setBooksList(bookDTOSList);
            seriesDTO.setSeriesPagesRead(bookDTOSList.stream().mapToInt(BookDTO::getPagesRead).sum());
            seriesDTO.setSeriesAllPages(bookDTOSList.stream().mapToInt(BookDTO::getAllPages).sum());
            seriesDTOList.add(seriesDTO);
        }
        return seriesDTOList;
    }

    private static SeriesDTO objectToDTO(Series series){
        SeriesDTO seriesDTO = new SeriesDTO();
        seriesDTO.setSeriesTitle(series.getSeriesTitle());
        seriesDTO.setSeriesId(series.getSeriesId());
        seriesDTO.setSeriesCreationTime(series.getSeriesCreationDate());
        seriesDTO.setSeriesDescription(series.getSeriesDescription());

        List<Book> bookList = series.getBooksList();
        List<BookDTO> bookDTOList = new ArrayList<>();
        for(Book b : bookList){
            BookDTO bookDTO = new BookDTO();
            bookDTO.setTitle(b.getTitle());
            bookDTO.setImage(b.getImageURL());
            bookDTO.setBookId(b.getBookId());
            bookDTO.setFavourite(b.isFavourite());
            bookDTO.setPagesRead(b.getPagesRead());
            bookDTO.setAllPages(b.getAllPages());
            bookDTOList.add(bookDTO);
        }
        seriesDTO.setSeriesPagesRead(bookDTOList.stream().mapToInt(BookDTO::getPagesRead).sum());
        seriesDTO.setSeriesAllPages(bookDTOList.stream().mapToInt(BookDTO::getAllPages).sum());
        seriesDTO.setBooksList(bookDTOList);
        return seriesDTO;
    }

}
