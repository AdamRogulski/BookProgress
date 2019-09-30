package example.bookprogressapp.series;

import com.fasterxml.jackson.annotation.JsonFormat;
import example.bookprogressapp.book.BookDTO;

import java.time.LocalDateTime;
import java.util.List;

public class SeriesDTO {

    private Long seriesId;
    private String seriesTitle;
    private List<BookDTO> booksList;
    private int seriesPagesRead;
    private int seriesAllPages;

    @JsonFormat(pattern = "dd.MM.yyyy HH.mm")
    private LocalDateTime seriesCreationTime;

    private String seriesDescription;

    public String getSeriesDescription() {
        return seriesDescription;
    }

    public void setSeriesDescription(String seriesDescription) {
        this.seriesDescription = seriesDescription;
    }

    public LocalDateTime getSeriesCreationTime() {
        return seriesCreationTime;
    }

    public void setSeriesCreationTime(LocalDateTime seriesCreationTime) {
        this.seriesCreationTime = seriesCreationTime;
    }

    public int getSeriesAllPages() {
        return seriesAllPages;
    }

    public void setSeriesAllPages(int seriesAllPages) {
        this.seriesAllPages = seriesAllPages;
    }

    public int getSeriesPagesRead() {
        return seriesPagesRead;
    }

    public void setSeriesPagesRead(int seriesPagesRead) {
        this.seriesPagesRead = seriesPagesRead;
    }

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

    public List<BookDTO> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<BookDTO> booksList) {
        this.booksList = booksList;
    }
}
