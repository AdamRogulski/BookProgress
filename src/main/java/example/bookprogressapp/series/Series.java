package example.bookprogressapp.series;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import example.bookprogressapp.book.Book;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seriesId;

    @Column(unique = true, nullable = false)
    private String seriesTitle;

    @JsonIgnore
    @OneToMany(mappedBy = "series")
    private List<Book> booksList;

    public Long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }
}
