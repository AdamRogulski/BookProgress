package example.bookprogressapp.series;

import example.bookprogressapp.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class SeriesController {

    private SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @GetMapping("/series")
    public List<SeriesDTO> getAllSeries(){
        return seriesService.getAllSeries();
    }

    @GetMapping("/series/{id}/books")
    public List<Book> getSeriesBooks(@PathVariable Long id){
        return seriesService.getSeriesBooksById(id);
    }

    @GetMapping("/series/{id}")
    public SeriesDTO getOneSeries(@PathVariable Long id){
        return seriesService.getOneSeriesById(id);
    }

    @GetMapping("/series123")
    public List<Series> getAllWithPages(@RequestParam int page){
        return seriesService.getAllSeriesWithPagination(page);
    }

    @PostMapping("/series/add/by")
    public ResponseEntity<String> addSeriesByTitle(@RequestParam String title){
        seriesService.addSeriesByTitle(title);
        return new ResponseEntity<>("Series added", HttpStatus.OK);
    }

    @PostMapping("/series/add")
    public ResponseEntity<String> addSeriesByBody(@RequestBody @Valid Series series){
        seriesService.addSeriesBySeriesBody(series);
        return new ResponseEntity<>("Series added", HttpStatus.OK);
    }

    @DeleteMapping("/series/delete/{id}")
    public ResponseEntity<String> deleteSeries(@PathVariable Long id){
        seriesService.deleteSeriesById(id);
        return new ResponseEntity<>("Series deleted", HttpStatus.OK);
    }

    @PostMapping("/series/{seriesId}/add")
    public ResponseEntity<String> addBookToSeries(@PathVariable Long seriesId, @RequestParam Long bookId) {
        seriesService.addBookToSeries(seriesId, bookId);
        return new ResponseEntity<>("Book added to series", HttpStatus.OK);
    }

    @DeleteMapping("/series/remove/{id}")
    public ResponseEntity<String> removeBookFromSeries(@PathVariable Long id){
        seriesService.removeBookFromSeries(id);
        return new ResponseEntity<>("Book removed from series",HttpStatus.OK);
    }


}
